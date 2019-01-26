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

class RelationTestLessThan {

    @Test
    fun succeeds_int() = assertDoesNotThrow { expect(1 is_ Less than 2) }

    @Test
    fun fails() = assertFails { expect(1 is_ Less than 1) }

    @Test
    fun negated_succeeds() = assertDoesNotThrow { expect(1 is_ Not - Less than 1) }

    @Test
    fun negated_fails() = assertFails { expect(1 is_ Not - Less than 2) }

    @Test
    fun `comparison with uncomparable first item is false`() =
            assertFails { expect(arrayOf(2) is_ Less than 3) }

    @Test
    fun `comparison with uncomparable second item is false`() =
            assertFails { expect(2 is_ Less than arrayOf(3)) }

    @Test
    fun `items are formatted`() =
            expect(((arrayOf(1) is_ Less than arrayOf(2))() as AssertionResult).explanation
                    is_ Equal to_ "[1] < [2]")
}