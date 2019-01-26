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

class RelationTestIsSameAs {

    @Test
    fun passes() = assertDoesNotThrow { expect(a is_ Same as_ a) }

    @Test
    fun negation_passes() = assertDoesNotThrow { expect(a is_ Not - Same as_ b) }

    @Test
    fun isSameAs_fails() = assertFails { expect(a is_ Same as_ b) }

    @Test
    fun isNotSameAs_fails() = assertFails { expect(a is_ Not - Same as_ a) }

    @Test
    fun `explanation items are formatted`() =
            expect(((arrayOf(1) is_ Same as_ arrayOf(1))() as AssertionResult).explanation
                    is_ Equal to_ "[1] === [1]")

    private data class EqualButNotSame(val value: Int)
    private companion object {
        val a = EqualButNotSame(1)
        val b = EqualButNotSame(1)
    }
}