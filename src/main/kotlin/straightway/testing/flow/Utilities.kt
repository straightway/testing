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

import org.opentest4j.AssertionFailedError

internal val Any?.asIterable get() =
        when (this) {
            is Iterable<*> -> this
            is Array<*> -> this.asList()
            is CharSequence -> this.toList()
            is Map<*, *> -> this.toList()
            else -> throw AssertionFailedError("Cannot convert $this to_ iterable")
        }

@Suppress("UNCHECKED_CAST")
internal fun Any.untypedCompareTo(other: Any): Int =
        (this as Comparable<Any>).compareTo(other)
