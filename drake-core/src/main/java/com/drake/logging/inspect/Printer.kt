package com.drake.logging.inspect

import java.lang.reflect.Array
import java.util.*

internal class Printer(private val source: Any?) {

    private val s = StringBuilder()

    fun print(): String {
        printAny(source)
        return s.toString()
    }

    private fun printAny(obj: Any?): Printer {
        try {
            printAuto(obj)
        } catch (e: Exception) {
            printException(e)
        }
        return this
    }

    private fun printAuto(obj: Any?): Printer {
        when {
            obj == null -> printNull()
            obj is String -> printString(obj)
            obj is Map<*, *> -> printMap(obj.entries.iterator())
            obj is Iterable<*> -> printList(obj)
            obj is Number -> printNumber(obj)
            obj is Boolean -> printBoolean(obj)
            obj.javaClass.isArray -> printArray(obj)
            else -> printOther(obj)
        }
        return this
    }

    private fun printException(e: Exception) {
        s.append("<")
        s.append(e.javaClass.simpleName)
        s.append(">")
    }

    private fun printNull(): Printer {
        s.append("null")
        return this
    }

    private fun printBoolean(value: Boolean): Printer {
        s.append(value)
        return this
    }

    private fun printNumber(value: Number): Printer {
        s.append(value)
        return this
    }

    private fun printString(value: String): Printer {
        s.append('\'')
        s.append(value)
        s.append('\'')
        return this
    }

    private fun printList(it: Iterable<*>): Printer {
        s.append("List[")
        var first = true
        try {
            for (item in it) {
                if (first) {
                    first = false
                } else {
                    s.append(", ")
                }
                printAny(item)
            }
        } catch (e: Exception) {
            printException(e)
        }

        s.append(']')
        return this
    }

    private fun <T : Map.Entry<*, *>> printMap(it: Iterator<T>): Printer {
        s.append("Map[")
        var first = true
        try {
            while (it.hasNext()) {
                val entry = it.next()
                if (first) {
                    first = false
                } else {
                    s.append(", ")
                }
                s.append(entry.key)
                s.append(" => ")
                printAny(entry.value)
            }
        } catch (e: Exception) {
            printException(e)
        }

        s.append(']')
        return this
    }

    private fun printArray(obj: Any): Printer {
        val length = Array.getLength(obj)
        s.append("Array[")
        var first = true
        try {
            for (i in 0 until length) {
                val item = Array.get(obj, i)
                if (first) {
                    first = false
                } else {
                    s.append(", ")
                }
                printAny(item)
            }
        } catch (e: Exception) {
            printException(e)
        }

        s.append(']')
        return this
    }

    private fun printOther(obj: Any) {
        try {
            for (handler in ObjectInspector.handlers) {
                if (handler.handle(this, obj)) {
                    return
                }
            }
        } catch (e: Exception) {
            printException(e)
            return
        }
        s.append(obj.toString())
    }
}