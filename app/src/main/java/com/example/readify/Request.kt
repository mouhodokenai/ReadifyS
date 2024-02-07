package com.example.readify


class Answer(val mapAttributes: Map<String, String>)


class Request(val request: String, val mapAttributes: Map<String, String>)
    /*
    constructor(request: String, vararg keyValuePairs: Pair<Int, Int>) : this(
        request,
        keyValuePairs.associate { (key, value) -> key.toString() to value.toString() }
    )
     */

class RequestI(val request: String, val mapAttributes: Map<String, Int>)
