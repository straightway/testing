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

import kotlin.collections.contentEquals

/**
 * Relation checking if two objects are equal.
 */
object Equal : EqualBase({ a, b -> AssertionResult("$a == $b", areEqual(a, b)) })

@Suppress("ComplexMethod")
private fun areEqual(a: Any?, b: Any?) =
        when {
            a is Array<*> && b is Array<*> -> a contentEquals b
            a is Array<*> && b is Values -> a contentEquals b.elements
            a is Iterable<*> && b is Values -> a.toList() == b.elements.toList()
            a is Map<*, *> && b is Values -> a.toList() == b.elements.toList()
            else -> a == b
        }
