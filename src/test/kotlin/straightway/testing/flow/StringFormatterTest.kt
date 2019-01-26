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

class StringFormatterTest {

    @Test
    fun `for normal object, toString is called`() =
            expect(3.formatted() is_ Equal to_ "3")

    @Test
    fun `null value yields null string representation`() =
            expect(null.formatted() is_ Equal to_ "<null>")

    @Test
    fun `collection yields string with elements in bracked`() =
            expect(listOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `array yields string with elements in bracked`() =
            expect(arrayOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `ByteArray yields string with elements in bracked`() =
            expect(byteArrayOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `CharArray yields string with elements in bracked`() =
            expect(charArrayOf('1', '2', '3').formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `ShortArray yields string with elements in bracked`() =
            expect(shortArrayOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `IntArray yields string with elements in bracked`() =
            expect(intArrayOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `LongArray yields string with elements in bracked`() =
            expect(longArrayOf(1, 2, 3).formatted() is_ Equal to_ "[1, 2, 3]")

    @Test
    fun `FloatArray yields string with elements in bracked`() =
            expect(floatArrayOf(1.0F, 2.0F, 3.0F).formatted() is_ Equal to_ "[1.0, 2.0, 3.0]")

    @Test
    fun `DoubleArray yields string with elements in bracked`() =
            expect(doubleArrayOf(1.0, 2.0, 3.0).formatted() is_ Equal to_ "[1.0, 2.0, 3.0]")

    @Test
    fun `BooleanArray yields string with elements in bracked`() =
            expect(booleanArrayOf(true, false).formatted() is_ Equal to_ "[true, false]")

    @Test
    fun `collection of arrays formats its elements properly`() =
            expect(listOf(arrayOf(1)).formatted() is_ Equal to_ "[[1]]")

    @Test
    fun `map with array as key formats its elements properly`() =
            expect(mapOf(arrayOf(1) to 2, arrayOf(3) to 4).formatted()
                    is_ Equal to_ "{[1]=2, [3]=4}")

    @Test
    fun `map with array as value formats its elements properly`() =
            expect(mapOf(2 to arrayOf(1), 4 to arrayOf(3)).formatted()
                    is_ Equal to_ "{2=[1], 4=[3]}")

    @Test
    fun `string is formatted with quotes`() =
            expect("Hello".formatted() is_ Equal to_ "\"Hello\"")
}