package com.drake.logging.inspect

internal class Handler<T>(private val clazz: Class<T>, private val handler: (Printer, T) -> (Unit)) {

    @Throws(Exception::class)
    fun handle(printer: Printer, o: Any): Boolean {
        if (clazz.isInstance(o)) {
            handler.invoke(printer, clazz.cast(o))
            return true
        }
        return false
    }
}