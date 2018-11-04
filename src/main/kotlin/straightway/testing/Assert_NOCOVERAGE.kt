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
import org.junit.jupiter.api.fail

inline fun <reified TException : Throwable> assertThrows(noinline action: () -> Unit) {
    Assertions.assertThrows<TException>(TException::class.java, action)
}

@Suppress("TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <reified TException : Throwable> assertThrows(
        expectedMessageRegex: Regex,
        noinline action: () -> Unit
) {
    try {
        action()
        fail("Action $action did not throw an exception")
    } catch (e: Throwable) {
        if (!(e is TException))
            fail("Action $action threw unexpected exception $e")
        if (!(expectedMessageRegex.matches(e.message!!)))
            fail("Unexpected failure message: ${e.message} " +
                    "(expected pattern: $expectedMessageRegex)")
    }
}

@Suppress("TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <reified TException : Throwable> assertThrows(
        expectedMessage: String,
        noinline action: () -> Unit
) {
    try {
        action()
        fail("Action $action did not throw an exception")
    } catch (e: Throwable) {
        if (!(e is TException))
            fail("Action $action threw unexpected exception $e")
        Assertions.assertEquals(expectedMessage, e.message)
    }
}
