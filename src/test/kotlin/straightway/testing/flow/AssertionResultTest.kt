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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AssertionResultTest {

    @Test
    fun `success is successful`() =
            assertTrue { AssertionResult.success("Explanation").isSuccessful }

    @Test
    fun `failureExplanation is accessible for success`() =
            assertEquals("Explanation", AssertionResult.success("Explanation").explanation)

    @Test
    fun `failure is not successful`() =
            assertFalse { AssertionResult.failure("Explanation").isSuccessful }

    @Test
    fun `failureExplanation is accessible for failure`() =
            assertEquals("Explanation", AssertionResult.failure("Explanation").explanation)

    @Test
    fun `toString includes explanation and success state`() =
            assertEquals("Success: Explanation", AssertionResult.success("Explanation").toString())

    @Test
    fun `toString includes explanation and failure state`() =
            assertEquals("Failure: Explanation", AssertionResult.failure("Explanation").toString())

    @Test
    fun `equal fields values mean equal AssertionResults`() =
            assertTrue(AssertionResult("Explanation", true) ==
                    AssertionResult("Explanation", true))
}