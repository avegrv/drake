package com.drake.logging

import java.util.*

object Log {

    private const val VERBOSE = 2
    private const val DEBUG = 3
    private const val INFO = 4
    private const val WARN = 5
    private const val ERROR = 6

    private val lines = Observable()

    fun v(tag: String, msg: String, vararg formatArgs: Any) {
        print(VERBOSE, tag, msg, null, *formatArgs)
    }

    fun v(tag: String, msg: String, th: Throwable, vararg formatArgs: Any) {
        print(VERBOSE, tag, msg, th, *formatArgs)
    }

    fun d(tag: String, msg: String, vararg formatArgs: Any) {
        print(DEBUG, tag, msg, null, *formatArgs)
    }

    fun d(tag: String, msg: String, th: Throwable, vararg formatArgs: Any) {
        print(DEBUG, tag, msg, th, *formatArgs)
    }

    fun i(tag: String, msg: String, vararg formatArgs: Any) {
        print(INFO, tag, msg, null, *formatArgs)
    }

    fun i(tag: String, msg: String, th: Throwable, vararg formatArgs: Any) {
        print(INFO, tag, msg, th, *formatArgs)
    }

    fun w(tag: String, msg: String, vararg formatArgs: Any) {
        print(WARN, tag, msg, null, *formatArgs)
    }

    fun w(tag: String, msg: String, th: Throwable, vararg formatArgs: Any) {
        print(WARN, tag, msg, th, *formatArgs)
    }

    fun e(tag: String, msg: String, vararg formatArgs: Any) {
        print(ERROR, tag, msg, null, *formatArgs)
    }

    fun e(tag: String, msg: String, th: Throwable, vararg formatArgs: Any) {
        print(ERROR, tag, msg, th, *formatArgs)
    }

    private fun print(
            level: Int,
            tag: String,
            message: String,
            throwable: Throwable?,
            vararg formatArgs: Any
    ) {
        lines.notifyObservers(
                LogLine(
                        timestamp = System.currentTimeMillis(),
                        level = level,
                        tag = tag,
                        message = message,
                        throwable = throwable,
                        formatArgs = listOf(formatArgs)
                )
        )
    }
}