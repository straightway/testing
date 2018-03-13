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

class TestSize {

    @Test
    fun `success for List`() =
            assertDoesNotThrow { expect(listOf(1, 2, 3) has Size of 3) }

    @Test
    fun `failure for List`() =
            assertFails { expect(listOf(1, 2, 3) has Size of 1) }

    @Test
    fun `negated success for List`() =
            assertDoesNotThrow { expect(listOf(1, 2, 3) has Size of 3) }

    @Test
    fun `negated failure for List`() =
            assertFails { expect(listOf(1, 2, 3) has Size of 1) }

    @Test
    fun `success for Array`() =
            assertDoesNotThrow { expect(arrayOf(1, 2, 3) has Size of 3) }

    @Test
    fun `failure for Array`() =
            assertFails { expect(arrayOf(1, 2, 3) has Size of 1) }

    @Test
    fun `negated success for Array`() =
            assertDoesNotThrow { expect(arrayOf(1, 2, 3) has Size of 3) }

    @Test
    fun `negated failure for Array`() =
            assertFails { expect(arrayOf(1, 2, 3) has Size of 1) }

    @Test
    fun `success for String`() =
            assertDoesNotThrow { expect("123" has Size of 3) }

    @Test
    fun `failure for String`() =
            assertFails { expect("123" has Size of 1) }

    @Test
    fun `negated success for String`() =
            assertDoesNotThrow { expect("123" has Size of 3) }

    @Test
    fun `negated failure for String`() =
            assertFails { expect("123" has Size of 1) }
}