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
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails
import straightway.testing.bdd.Given

class TestValues {

    @Test
    fun `empty collection has not elements`() =
            assertFails { expect(arrayOf<Int>() has Values(2)) }

    @Test
    fun `single element collection with searched element succeeds`() =
            assertDoesNotThrow { expect(arrayOf(2) has Values(2)) }

    @Test
    fun `single element collection without searched element fails`() =
            assertFails { expect(arrayOf(2) has Values(3)) }

    @Test
    fun `single element collection with one of two searched elements fails`() =
            assertFails { expect(arrayOf(2) has Values(2, 3)) }

    @Test
    fun `two element collection with both searched elements succeeds`() =
            assertDoesNotThrow { expect(arrayOf(3, 2) has Values(2, 3)) }

    @Test
    fun `three element collection with both searched elements succeeds`() =
            assertDoesNotThrow { expect(arrayOf(5, 3, 2) has Values(2, 3)) }

    @Test
    fun `works with List`() =
            assertDoesNotThrow { expect(listOf(2) has Values(2)) }

    @Test
    fun `works with String`() =
            assertDoesNotThrow { expect("Hello World" has Values('l', 'W')) }

    @Test
    fun `convert from collection`() =
            assertDoesNotThrow { expect("Hello World" has listOf('l', 'W').values()) }

    @Test
    fun `toString contains checked references`() =
            Given { Values(1, 2, 3) } when_ { toString() } then {
                expect(it.result is_ Equal to_ "Values[1, 2, 3]")
            }
}