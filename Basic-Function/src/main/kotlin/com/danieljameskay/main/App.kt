package com.danieljameskay.main

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.danieljameskay.models.MSKEvent
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class App(var gson: Gson = GsonBuilder().setPrettyPrinting().create()) : RequestHandler<MSKEvent, String> {
    override fun handleRequest(event: MSKEvent, context: Context): String {
        val logger = context.logger
        logger.log(gson.toJson(event))
        return "OK"
    }
}
