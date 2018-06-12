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

import org.junit.jupiter.api.Test
import straightway.expr.minus
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class TestEmpty {

    @Test
    fun `succeeds on empty collection`() =
            assertDoesNotThrow { expect(listOf<Int>() is_ Empty) }

    @Test
    fun `fails on non empty collection`() =
            assertFails("Expectation <Empty([1])> failed") { expect(listOf(1) is_ Empty) }

    @Test
    fun `negation succeeds on non empty collection`() =
            assertDoesNotThrow { expect(listOf(1) is_ Not - Empty) }

    @Test
    fun `negation fails on empty collection`() =
            assertFails("Expectation <Not-Empty([])> failed") {
                expect(listOf<Int>() is_ Not - Empty)
            }

    @Test
    fun `succeeds on empty array`() =
            assertDoesNotThrow { expect(arrayOf<Int>() is_ Empty) }

    @Test
    fun `fails on non empty array`() =
            assertFails("Expectation <Empty([1])> failed") { expect(arrayOf(1) is_ Empty) }

    @Test
    fun `negation succeeds on non empty array`() =
            assertDoesNotThrow { expect(arrayOf(1) is_ Not - Empty) }

    @Test
    fun `negation fails on empty array`() =
            assertFails("Expectation <Not-Empty([])> failed") {
                expect(arrayOf<Int>() is_ Not - Empty)
            }

    @Test
    fun `succeeds on empty string`() =
            assertDoesNotThrow { expect("" is_ Empty) }

    @Test
    fun `fails on non empty string`() =
            assertFails("Expectation <Empty(Hello)> failed") { expect("Hello" is_ Empty) }

    @Test
    fun `negation succeeds on non empty string`() =
            assertDoesNotThrow { expect("Hello" is_ Not - Empty) }

    @Test
    fun `negation fails on empty string`() =
            assertFails("Expectation <Not-Empty()> failed") { expect("" is_ Not - Empty) }

    @Test
    fun `succeeds on empty map`() =
            assertDoesNotThrow { expect(mapOf<String, Any>() is_ Empty) }

    @Test
    fun `fails on non-empty map`() =
            assertFails("Expectation <Empty({1=A})> failed") {
                expect(mapOf(1 to "A") is_ Empty)
            }

    @Test
    fun `fails for non collections`() =
            assertFails { expect(2 is_ Empty) }

    @Test
    fun `negation fails for non collections`() =
            assertFails { expect(2 is_ Not - Empty) }

    @Test
    fun `fails for null`() =
            assertFails { expect(null is_ Empty) }

    @Test
    fun `negation fails for null`() =
            assertFails { expect(null is_ Not - Empty) }
}