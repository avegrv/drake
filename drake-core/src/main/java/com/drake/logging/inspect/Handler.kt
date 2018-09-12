package com.drake.logging.inspect

import java.util.function.BiConsumer

internal class Handler<T>(private val clazz: Class<T>, private val handler: BiConsumer<Printer, T>) {

    @Throws(Exception::class)
    fun handle(printer: Printer, o: Any): Boolean {
        if (clazz.isInstance(o)) {
            handler.accept(printer, clazz.cast(o))
            return true
        }
        return false
    }
}