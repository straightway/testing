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

class RelationTestEmpty {

    @Test
    fun succeeds_onEmptyCollection() =
            assertDoesNotThrow { expect(listOf<Int>() is_ Empty) }

    @Test
    fun fails_onNonEmptyCollection() =
            assertFails("Expectation <Empty([1])> failed") { expect(listOf(1) is_ Empty) }

    @Test
    fun negationSucceeds_onNonEmptyCollection() =
            assertDoesNotThrow { expect(listOf(1) is_ not - Empty) }

    @Test
    fun negationFails_onEmptyCollection() =
            assertFails("Expectation <not-Empty([])> failed") {
                expect(listOf<Int>() is_ not - Empty)
            }

    @Test
    fun succeeds_onEmptyArray() =
            assertDoesNotThrow { expect(arrayOf<Int>() is_ Empty) }

    @Test
    fun fails_onNonEmptyArray() =
            assertFails("Expectation <Empty([1])> failed") { expect(arrayOf(1) is_ Empty) }

    @Test
    fun negationSucceeds_onNonEmptyArray() =
            assertDoesNotThrow { expect(arrayOf(1) is_ not - Empty) }

    @Test
    fun negationFails_onEmptyArray() =
            assertFails("Expectation <not-Empty([])> failed") {
                expect(arrayOf<Int>() is_ not - Empty)
            }

    @Test
    fun succeeds_onEmptyString() =
            assertDoesNotThrow { expect("" is_ Empty) }

    @Test
    fun fails_onNonEmptyString() =
            assertFails("Expectation <Empty(Hello)> failed") { expect("Hello" is_ Empty) }

    @Test
    fun negationSucceeds_onNonEmptyString() =
            assertDoesNotThrow { expect("Hello" is_ not - Empty) }

    @Test
    fun negationFails_onEmptyString() =
            assertFails("Expectation <not-Empty()> failed") { expect("" is_ not - Empty) }

    @Test
    fun fails_forNonCollections() =
            assertFails { expect(2 is_ Empty) }

    @Test
    fun negationFails_forNonCollections() =
            assertFails { expect(2 is_ not - Empty) }
}