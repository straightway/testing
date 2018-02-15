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

class RelationTestHasSize {

    @Test
    fun success_forList() =
            assertDoesNotThrow { expect(listOf(1, 2, 3) has Size of 3) }

    @Test
    fun failure_forList() =
            assertFails { expect(listOf(1, 2, 3) has Size of 1) }

    @Test
    fun negatedSuccess_forList() =
            assertDoesNotThrow { expect(listOf(1, 2, 3) has Size of 3) }

    @Test
    fun negatedFailure_forList() =
            assertFails { expect(listOf(1, 2, 3) has Size of 1) }

    @Test
    fun success_forArray() =
            assertDoesNotThrow { expect(arrayOf(1, 2, 3) has Size of 3) }

    @Test
    fun failure_forArray() =
            assertFails { expect(arrayOf(1, 2, 3) has Size of 1) }

    @Test
    fun negatedSuccess_forArray() =
            assertDoesNotThrow { expect(arrayOf(1, 2, 3) has Size of 3) }

    @Test
    fun negatedFailure_forArray() =
            assertFails { expect(arrayOf(1, 2, 3) has Size of 1) }

    @Test
    fun success_forString() =
            assertDoesNotThrow { expect("123" has Size of 3) }

    @Test
    fun failure_forString() =
            assertFails { expect("123" has Size of 1) }

    @Test
    fun negatedSuccess_forString() =
            assertDoesNotThrow { expect("123" has Size of 3) }

    @Test
    fun negatedFailure_forString() =
            assertFails { expect("123" has Size of 1) }
}