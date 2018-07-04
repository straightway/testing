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
package straightway.testing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError

class AssertTestAssertFailsWithExpectedMessage {

    @Test
    fun `passes if action fails with correct message`() =
            assertDoesNotThrow {
                assertFails("Expected message") {
                    Assertions.fail("Expected message")
                }
            }

    @Test
    fun `fails with expected message, if action does not fail`() =
            assertThrows<AssertionFailedError> {
                assertFails("Expected message") {}
            }

    @Test
    fun `fails if action fails with wrong message`() =
            assertThrows<AssertionFailedError> {
                assertFails("Expected message") { Assertions.fail("Wrong message") }
            }

    @Test
    fun `passes if action fails with matching message`() =
            assertDoesNotThrow {
                assertFails(Regex("Expected.*")) {
                    Assertions.fail("Expected message")
                }
            }

    @Test
    fun `fails with matching message, if action does not fail`() =
            assertThrows<AssertionFailedError> {
                assertFails(Regex("Expected.*")) {}
            }

    @Test
    fun `fails if action fails with not matching message`() =
            assertThrows<AssertionFailedError> {
                assertFails(Regex("Expected.*")) { Assertions.fail("Wrong message") }
            }
}