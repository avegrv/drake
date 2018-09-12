package com.drake.logging

internal data class LogLine(
        private val timestamp: Long,
        private val level: Int,
        private val tag: String,
        private val message: String,
        private val throwable: Throwable?,
        private val formatArgs: List<Any>
)