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
        Assertions.fail<Unit>("Action $action did not throw an exception")
    } catch (e: Throwable) {
        Assertions.assertTrue(e is TException, "Action $action threw unexpected exception $e")
        Assertions.assertTrue(expectedMessageRegex.matches(e.message!!))
    }
}

@Suppress("TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <reified TException : Throwable> assertThrows(
        expectedMessage: String,
        noinline action: () -> Unit
) {
    try {
        action()
        Assertions.fail<Unit>("Action $action did not throw an exception")
    } catch (e: Throwable) {
        Assertions.assertTrue(e is TException, "Action $action threw unexpected exception $e")
        Assertions.assertEquals(expectedMessage, e.message)
    }
}
