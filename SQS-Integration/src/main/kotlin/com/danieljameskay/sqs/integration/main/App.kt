package com.danieljameskay.sqs.integration.main

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.danieljameskay.sqs.integration.models.ExchangeVolumeUpdate
import com.danieljameskay.sqs.integration.models.MSKEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*

class App(var gson: Gson = GsonBuilder().setPrettyPrinting().create(),
          var sqs: AmazonSQS = AmazonSQSClientBuilder.defaultClient()) : RequestHandler<MSKEvent, String> {

    private val queueUrl: String = sqs.getQueueUrl("poloniex_24hr_exchange_volume").queueUrl

    override fun handleRequest(event: MSKEvent, context: Context): String {
        val logger = context.logger
        event.records.forEach { (_, r) ->
            r.forEach {
                logger.log("Processing: $it")
                val exchangeVolumeUpdate = ExchangeVolumeUpdate(String(Base64.getDecoder().decode(it.value)), it.timestamp)
                val sendMsgRequest = SendMessageRequest()
                        .withQueueUrl(queueUrl)
                        .withMessageBody(gson.toJson(exchangeVolumeUpdate))
                val messageId = sqs.sendMessage(sendMsgRequest).messageId
                logger.log(messageId)
            }
        }
        return "OK"
    }
}
