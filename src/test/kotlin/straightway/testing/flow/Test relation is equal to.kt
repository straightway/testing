// ktlint-disable filename
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

    @Test
    fun `array in array`() =
            expect(arrayOf(arrayOf(1)) is_ Equal to_ arrayOf(arrayOf(1)))

    @Test
    fun `array in list with values`() =
            expect(listOf(arrayOf(1)) is_ Equal to_ Values(arrayOf(1)))

    @Test
    fun `array in map with values`() =
            expect(mapOf("a" to arrayOf(1)) is_ Equal to_ Values("a" to arrayOf(1)))

    @Test
    fun `compare map with values not containing pairs`() =
            expect(mapOf("a" to 1) is_ Not - Equal to_ Values("a"))

    @Test
    fun `compare array to list`() =
            expect(arrayOf(1, 2, 3) is_ Equal to_ listOf(1, 2, 3))

    @Test
    fun `compare list to array`() =
            expect(listOf(1, 2, 3) is_ Equal to_ arrayOf(1, 2, 3))

    @Test
    fun `compare ByteArray to unspecific array`() =
            expect(byteArrayOf(1) is_ Equal to_ arrayOf<Byte>(1))

    @Test
    fun `compare CharArray to unspecific array`() =
            expect(charArrayOf('a') is_ Equal to_ arrayOf('a'))

    @Test
    fun `compare ShortArray to unspecific array`() =
            expect(shortArrayOf(1) is_ Equal to_ arrayOf<Short>(1))

    @Test
    fun `compare IntArray to unspecific array`() =
            expect(intArrayOf(1) is_ Equal to_ arrayOf(1))

    @Test
    fun `compare LongArray to unspecific array`() =
            expect(longArrayOf(1) is_ Equal to_ arrayOf<Long>(1))

    @Test
    fun `compare FloatArray to unspecific array`() =
            expect(floatArrayOf(1.0F) is_ Equal to_ arrayOf(1.0F))

    @Test
    fun `compare DoubleArray to unspecific array`() =
            expect(doubleArrayOf(1.0) is_ Equal to_ arrayOf(1.0))

    @Test
    fun `compare BooleanArray to unspecific array`() =
            expect(booleanArrayOf(true) is_ Equal to_ arrayOf(true))

    @Test
    fun `compare set to set`() =
            expect(setOf(1, 2, 3) is_ Equal to_ setOf(3, 2, 1))

    @Test
    fun `compare set to collection`() =
            expect(setOf(1, 2, 3) is_ Equal to_ listOf(3, 2, 1))

    @Test
    fun `compare set to array`() =
            expect(setOf(1, 2, 3) is_ Equal to_ arrayOf(3, 2, 1))

    @Test
    fun `compare set to number`() =
            expect(setOf(1, 2, 3) is_ Not - Equal to_ 1)

    @Test
    fun `compare collection to set`() =
            expect(listOf(1, 2, 3) is_ Equal to_ setOf(3, 2, 1))

    @Test
    fun `compare sets deeply`() =
            expect(setOf(arrayOf(1)) is_ Equal to_ setOf(arrayOf(1)))

    @Test
    fun `compare map to map`() =
            expect(mapOf("a" to 1, "b" to 2) is_ Equal to_ mapOf("b" to 2, "a" to 1))

    @Test
    fun `compare array to map`() =
            expect(arrayOf("a" to 1, "b" to 2) is_ Equal to_ mapOf("b" to 2, "a" to 1))

    @Test
    fun `compare a Pair to a Map Entry`() =
            expect(("a" to 1) is_ Equal to_ mapOf("a" to 1).entries.single())

    @Test
    fun `string representation of first Equal item is formatted`() =
            expect((arrayOf(1) is_ Equal to_ 2)().formatted() is_ Equal to_ "Failure: [1] == 2")

    @Test
    fun `string representation of second Equal item is formatted`() =
            expect((1 is_ Equal to_ arrayOf(2))().formatted() is_ Equal to_ "Failure: 1 == [2]")

    @Test
    fun `string representation of first EqualWithin item is formatted`() =
            expect((arrayOf(1) is_ EqualWithin(3) to_ 2)().formatted()
                    is_ Equal to_ "Failure: [1] == 2 [+/- 3]")

    @Test
    fun `string representation of second EqualWithin item is formatted`() =
            expect((1 is_ EqualWithin(3) to_ arrayOf(2))().formatted()
                    is_ Equal to_ "Failure: 1 == [2] [+/- 3]")

    @Test
    fun `string representation of range of EqualWithin item is formatted`() =
            expect((1 is_ EqualWithin(arrayOf(2)) to_ 3)().formatted()
                    is_ Equal to_ "Failure: 1 == 3 [+/- [2]]") }