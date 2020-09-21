package com.danieljameskay.sqs.integration.models

class MSKEvent {
    lateinit var records: Map<String, Array<Metadata>>
}
