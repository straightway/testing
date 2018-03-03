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

class `Test ContainerCheck is Empty` {

    @Test
    fun `succeeds on empty collection`() =
            assertDoesNotThrow { expect(listOf<Int>() is_ empty) }

    @Test
    fun `fails on non empty collection`() =
            assertFails("Expectation <empty([1])> failed") { expect(listOf(1) is_ empty) }

    @Test
    fun `negation succeeds on non empty collection`() =
            assertDoesNotThrow { expect(listOf(1) is_ not - empty) }

    @Test
    fun `negation fails on empty collection`() =
            assertFails("Expectation <not-empty([])> failed") {
                expect(listOf<Int>() is_ not - empty)
            }

    @Test
    fun `succeeds on empty array`() =
            assertDoesNotThrow { expect(arrayOf<Int>() is_ empty) }

    @Test
    fun `fails on non empty array`() =
            assertFails("Expectation <empty([1])> failed") { expect(arrayOf(1) is_ empty) }

    @Test
    fun `negation succeeds on non empty array`() =
            assertDoesNotThrow { expect(arrayOf(1) is_ not - empty) }

    @Test
    fun `negation fails on empty array`() =
            assertFails("Expectation <not-empty([])> failed") {
                expect(arrayOf<Int>() is_ not - empty)
            }

    @Test
    fun `succeeds on empty string`() =
            assertDoesNotThrow { expect("" is_ empty) }

    @Test
    fun `fails on non empty string`() =
            assertFails("Expectation <empty(Hello)> failed") { expect("Hello" is_ empty) }

    @Test
    fun `negation succeeds on non empty string`() =
            assertDoesNotThrow { expect("Hello" is_ not - empty) }

    @Test
    fun `negation fails on empty string`() =
            assertFails("Expectation <not-empty()> failed") { expect("" is_ not - empty) }

    @Test
    fun `fails for non collections`() =
            assertFails { expect(2 is_ empty) }

    @Test
    fun `negation fails for non collections`() =
            assertFails { expect(2 is_ not - empty) }

    @Test
    fun `fails for null`() =
            assertFails { expect(null is_ empty) }

    @Test
    fun `negation fails for null`() =
            assertFails { expect(null is_ not - empty) }
}