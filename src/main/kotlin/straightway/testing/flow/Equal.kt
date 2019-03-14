/*
 * Copyright 2016 github.com/straightway
 *
 *  Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package straightway.testing.flow

import straightway.numbers.compareTo

/**
 * Relation checking if two objects are equal.
 */
object Equal : EqualBase({ a, b ->
    AssertionResult("${a.formatted()} == ${b.formatted()}", areEqual(a, b))
})

internal fun areEqual(a: Any?, b: Any?): Boolean {
    val aa = a.toArray()
    val ba = b.toArray()
    return if (aa === null || ba === null) areSingleElementsEqual(a, b)
           else areArraysEqual(aa, ba)
}

@Suppress("ComplexMethod")
private fun areSingleElementsEqual(a: Any?, b: Any?): Boolean {
    return when {
        a is Pair<*, *> && b is Pair<*, *> ->
            areEqual(a.first, b.first) && areEqual(a.second, b.second)
        a is Pair<*, *> && b is Map.Entry<*, *> ->
            areEqual(a.first, b.key) && areEqual(a.second, b.value)
        a is Map.Entry<*, *> && b is Pair<*, *> ->
            areEqual(a.key, b.first) && areEqual(a.key, b.first)
        a is Set<*> && b !is Set<*> ->
            isSetEqualToCollection(a, b)
        a !is Set<*> && b is Set<*> ->
            isSetEqualToCollection(b, a)
        a is Set<*> && b is Set<*> ->
            areSetsEqual(a, b)
        a is Map<*, *> && b is Map<*, *> ->
            areSetsEqual(a.entries, b.entries)
        a is Map<*, *> && b !is Map<*, *> ->
            isSetEqualToCollection(a.entries, b)
        a !is Map<*, *> && b is Map<*, *> ->
            isSetEqualToCollection(b.entries, a)
        a is Number && b is Number ->
            a.compareTo(b) == 0
        else -> a == b
    }
}

private fun isSetEqualToCollection(set: Set<*>, other: Any?): Boolean {
    val otherArray = other.toArray()
    return otherArray != null && areSetsEqual(set, otherArray.toList())
}

private fun areSetsEqual(a: Set<*>, b: Collection<*>) =
        a.size == b.size && a.all { aItem -> b.any { bItem -> areEqual(aItem, bItem) } }

private fun areArraysEqual(a: Array<*>, b: Array<*>) =
        a.size == b.size && a.indices.all { areEqual(a[it], b[it]) }

@Suppress("ComplexMethod")
private fun Any?.toArray() =
        when (this) {
            is Values -> this.elements
            is Array<*> -> this
            is ByteArray -> this.toList().toTypedArray()
            is CharArray -> this.toList().toTypedArray()
            is ShortArray -> this.toList().toTypedArray()
            is IntArray -> this.toList().toTypedArray()
            is LongArray -> this.toList().toTypedArray()
            is FloatArray -> this.toList().toTypedArray()
            is DoubleArray -> this.toList().toTypedArray()
            is BooleanArray -> this.toList().toTypedArray()
            is Map<*, *> -> null
            is Set<*> -> null
            is ClosedRange<*> -> null
            is Iterable<*> -> this.toList().toTypedArray()
            else -> null
        }