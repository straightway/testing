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

import org.junit.jupiter.api.Test
import java.lang.NullPointerException
import java.security.InvalidKeyException
import java.security.InvalidParameterException

class AssertTestAssertThrowsWithExpectedMessage {

    @Test
    fun `passes with expected exception and specified message`() =
            assertDoesNotThrow {
                assertThrows<InvalidKeyException>("Expected") {
                    throw InvalidKeyException("Expected")
                }
            }

    @Test
    fun `passes with expected exception and matching message`() =
            assertDoesNotThrow {
                assertThrows<InvalidKeyException>(Regex("Expecte.")) {
                    throw InvalidKeyException("Expected")
                }
            }

    @Test
    fun `fails with unexpected exception and matching message`() =
            assertFails {
                assertThrows<InvalidKeyException>(Regex("Expecte.")) {
                    throw InvalidParameterException("Expected")
                }
            }

    @Test
    fun `fails with expected exception and not matching message`() =
            assertFails {
                assertThrows<InvalidKeyException>(Regex("Expecte.")) {
                    throw InvalidKeyException("Expected.")
                }
            }

    @Test
    fun `fails without exception and message regex`() =
            assertFails {
                assertThrows<InvalidKeyException>(Regex("Expecte.")) {}
            }

    @Test
    fun `fails with unexpected exception message`() =
            assertFails {
                assertThrows<InvalidKeyException>("Expected") {
                    throw InvalidKeyException("Unexpected")
                }
            }

    @Test
    fun `fails with exception of wrong type`() =
            assertFails {
                assertThrows<NullPointerException>("Expected") {
                    throw InvalidKeyException("Expected")
                }
            }

    @Test
    fun `fails without exception`() =
            assertFails {
                assertThrows<NullPointerException>("Expected") {}
            }
}
