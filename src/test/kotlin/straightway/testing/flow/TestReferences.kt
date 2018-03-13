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
import straightway.testing.bdd.Given

class TestReferences {

    private data class Item(val x: Int)

    @Test
    fun `empty collection has not references`() =
            Given { listOf<Item>() } when_ { Item(2) } then {
                expect(this has Not - References(it.result))
            }

    @Test
    fun `single element collection with searched reference`() =
            Given { listOf(Item(2)) } when_ { first() } then {
                expect(this has References(it.result))
            }

    @Test
    fun `single element collection with searched equal but not same reference`() =
            Given { listOf(Item(2)) } when_ { Item(2) } then {
                expect(this has Not - References(it.result))
            }

    @Test
    fun `multi element collection with single searched reference`() =
            Given { listOf(Item(3), Item(2)) } when_ { last() } then {
                expect(this has References(it.result))
            }

    @Test
    fun `multi element collection with multiple searched references`() =
            Given { listOf(Item(3), Item(2)) } when_ { toTypedArray() } then {
                expect(this has References(*it.result))
            }

    @Test
    fun `multi element collection with multiple searched references, but one missing`() =
            Given {
                listOf(Item(3), Item(2))
            } when_ {
                (this + Item(5) + last()).toTypedArray()
            } then {
                expect(this has Not - References(*it.result))
            }

    @Test
    fun `toString contains checked references`() =
            Given { References(1, 2, 3) } when_ { toString() } then {
                expect(it.result is_ Equal to_ "References[1, 2, 3]")
            }
}