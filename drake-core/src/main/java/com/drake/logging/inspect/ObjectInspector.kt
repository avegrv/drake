package com.drake.logging.inspect

import java.util.*

internal object ObjectInspector {

    internal val handlers = ArrayList<Handler<*>>()

    fun <T> register(clazz: Class<T>, mapper: (Printer, T) -> (Unit)) {
        handlers.add(Handler(clazz, mapper))
    }

     fun print(source: Any?): String {
        return Printer(source).print()
    }
}