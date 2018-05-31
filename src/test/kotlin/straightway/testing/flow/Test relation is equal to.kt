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
import java.time.Duration
import java.time.LocalDateTime

class `Test relation is equal to` {

    @Test
    fun passes() = assertDoesNotThrow { expect(1 is_ Equal to_ 1) }

    @Test
    fun fails() = assertFails { expect(1 is_ Equal to_ 2) }

    @Test
    fun negation_passes() = assertDoesNotThrow { expect(1 is_ Not - Equal to_ 2) }

    @Test
    fun number_range_smallerFirst_passes() =
            assertDoesNotThrow { expect(0.9 is_ EqualWithin(0.2) to_ 1.0) }

    @Test
    fun number_range_biggerFirst_passes() =
            assertDoesNotThrow { expect(1.1 is_ EqualWithin(0.2) to_ 1.0) }

    @Test
    fun number_range_fails() =
            assertFails { expect(1.3 is_ EqualWithin(0.2) to_ 1.0) }

    @Test
    fun number_range_negation_passes() =
            assertDoesNotThrow { expect(1.3 is_ Not - EqualWithin(0.2) to_ 1.0) }

    @Test
    fun duration_range_smallerFirst_passes() = assertDoesNotThrow {
        expect(
                LocalDateTime.of(0, 1, 1, 0, 0, 0)
                        is_ EqualWithin(Duration.ofDays(2)) to_ LocalDateTime.of(0, 1, 2, 0, 0, 0))
    }

    @Test
    fun duration_range_biggerFirst_passes() = assertDoesNotThrow {
        expect(
                LocalDateTime.of(0, 1, 2, 0, 0, 0)
                        is_ EqualWithin(Duration.ofDays(2)) to_ LocalDateTime.of(0, 1, 1, 0, 0, 0))
    }

    @Test
    fun duration_range_fails() = assertFails {
        expect(
                LocalDateTime.of(0, 1, 1, 0, 0, 0)
                        is_ EqualWithin(Duration.ofDays(2)) to_ LocalDateTime.of(0, 1, 3, 0, 0, 0))
    }

    @Test
    fun duration_range_negation_passes() = assertDoesNotThrow {
        expect(
                LocalDateTime.of(0, 1, 1, 0, 0, 0)
                        is_ Not - EqualWithin(Duration.ofDays(2))
                        to_ LocalDateTime.of(0, 1, 3, 0, 0, 0))
    }

    @Test
    fun `equal arrays`() = assertDoesNotThrow {
        expect(arrayOf<Int>() is_ Equal to_ arrayOf<Int>())
    }

    @Test
    fun `not equal iterables of different collection types`() = assertDoesNotThrow {
        expect(listOf<Int>() is_ Not - Equal to_ setOf<Int>())
    }

    @Test
    fun `not equal iterable and array`() = assertDoesNotThrow {
        expect(listOf<Int>() is_ Not - Equal to_ arrayOf<Int>())
    }

    @Test
    fun `not equal array and iterable`() = assertDoesNotThrow {
        expect(arrayOf<Int>() is_ Not - Equal to_ listOf<Int>())
    }

    @Test
    fun `array and Values item`() = assertDoesNotThrow {
        expect(arrayOf(1, 2, 3) is_ Equal to_ Values(1, 2, 3))
    }

    @Test
    fun `iterable and Values item`() = assertDoesNotThrow {
        expect(listOf(1, 2, 3) is_ Equal to_ Values(1, 2, 3))
    }

    @Test
    fun `map and Values item`() = assertDoesNotThrow {
        expect(mapOf(Pair("A", 1), Pair("B", 2)) is_ Equal to_
                       Values(Pair("A", 1), Pair("B", 2)))
    }
}