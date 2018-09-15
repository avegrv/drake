package com.drake.logging

data class LogLine(
        val timestamp: Long,
        val level: Int,
        val tag: String,
        val message: String,
        val throwable: Throwable?,
        val formatArgs: List<Any>?
)