package com.drake.logging.inspect

import java.util.*
import java.util.function.BiConsumer

internal object ObjectInspector {

    internal val handlers = ArrayList<Handler<*>>()

    fun <T> register(clazz: Class<T>, mapper: BiConsumer<Printer, T>) {
        handlers.add(Handler(clazz, mapper))
    }

     fun print(source: Any?): String {
        return Printer(source).print()
    }
}