package com.drake.logging.inspect

import junit.framework.Assert
import org.junit.Test
import java.util.*

class ObjectInspectorTest : Assert() {

    @Test
    fun inspectNull() {
        assertEquals("null", ObjectInspector.print(null).toString())
    }

    @Test
    fun inspectBoolean() {
        assertEquals("true", ObjectInspector.print(true).toString())
        assertEquals("false", ObjectInspector.print(false).toString())
    }

    @Test
    fun inspectNumber() {
        assertEquals("1", ObjectInspector.print(1L).toString())
        assertEquals("-1000", ObjectInspector.print(-1000).toString())
        assertEquals(2.2.toString(), ObjectInspector.print(2.2).toString())
    }

    @Test
    fun inspectCharacter() {
        assertEquals("z", ObjectInspector.print('z').toString())
    }

    @Test
    fun inspectString() {
        assertEquals("'sample string'", ObjectInspector.print("sample string").toString())
    }

    @Test
    fun inspectIterable() {
        assertEquals("List[]", ObjectInspector.print(emptyList<Any>()).toString())
        assertEquals("List[1, 2, 3]", ObjectInspector.print(Arrays.asList(1, 2, 3)).toString())
    }

    @Test
    fun inspectNestedIterable() {
        assertEquals("List[List['abc'], List['def']]",
                ObjectInspector.print(Arrays.asList(
                        setOf("abc"),
                        setOf("def")))
                        .toString())
    }

    @Test
    fun inspectMap() {
        assertEquals("Map[]", ObjectInspector.print(HashMap<Any, Any>()).toString())
        assertEquals("Map[a => 1, b => 2]", ObjectInspector.print(
                object : LinkedHashMap<String, Int>() {
                    init {
                        put("a", 1)
                        put("b", 2)
                    }
                }).toString())
    }

    @Test
    fun inspectNestedMap() {
        assertEquals("Map[a => Map[b => 2], c => List[]]", ObjectInspector.print(
                object : LinkedHashMap<Any, Any>() {
                    init {
                        put("a", Collections.singletonMap("b", 2))
                        put("c", emptyList<Any>())
                    }
                }).toString())
    }

    @Test
    fun inspectArbitraryObject() {
        assertEquals("toString()", ObjectInspector.print(object : Any() {
            override fun toString(): String {
                return "toString()"
            }
        }).toString())
    }

    @Test
    fun inspectArray() {
        assertEquals("Array[1, 1, 2]", ObjectInspector.print(longArrayOf(1, 1, 2)).toString())
        assertEquals("Array[1, 3, 5]", ObjectInspector.print(intArrayOf(1, 3, 5)).toString())
        assertEquals("Array['a', 'b', 'c']",
                ObjectInspector.print(arrayOf("a", "b", "c")).toString())
    }
}