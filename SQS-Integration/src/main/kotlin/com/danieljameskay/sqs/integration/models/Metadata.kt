package com.danieljameskay.sqs.integration.models

import com.fasterxml.jackson.annotation.JsonInclude

class Metadata {
    lateinit var topic: String
    var partition: Int = 0
    var offset: Int = 0
    var timestamp: Long = 0
    lateinit var timestampType: String
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var key: String = "0"
    lateinit var value: String

    override fun toString(): String {
        return "Metadata(topic='$topic', partition=$partition, offset=$offset, timestamp=$timestamp, timestampType='$timestampType', key='$key', value='$value')"
    }
}