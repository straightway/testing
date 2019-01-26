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

@Suppress("ComplexMethod")
fun Any?.formatted(): String = when (this) {
    is String -> "\"$this\""
    is Array<*> -> this.toList().formatted()
    is ByteArray -> this.toList().formatted()
    is CharArray -> this.toList().formatted()
    is ShortArray -> this.toList().formatted()
    is IntArray -> this.toList().formatted()
    is LongArray -> this.toList().formatted()
    is FloatArray -> this.toList().formatted()
    is DoubleArray -> this.toList().formatted()
    is BooleanArray -> this.toList().formatted()
    is Iterable<*> -> this.map { it.formatted() }.toString()
    is Map<*, *> ->
        "{" +
            this.entries.map { it.key.formatted() + "=" + it.value.formatted() }
                    .joinToString(", ") +
        "}"
    else -> if (this === null) "<null>" else toString()
}