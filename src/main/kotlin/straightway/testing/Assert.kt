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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.opentest4j.AssertionFailedError
import straightway.error.Panic
import java.io.PrintWriter
import java.io.StringWriter

fun assertPanics(action: () -> Unit) {
    assertThrows<Panic>(action)
}

@Suppress("TooGenericExceptionCaught")
fun assertPanics(expectedState: Any, action: () -> Unit) {
    try {
        action()
        fail<Unit>("Action $action did not cause panic")
    } catch (panic: Panic) {
        assertEquals(expectedState, panic.state)
    }
}

inline fun <reified TException : Throwable> assertThrows(noinline action: () -> Unit) {
    Assertions.assertThrows<TException>(TException::class.java, action)
}

@Suppress("TooGenericExceptionCaught", "InstanceOfCheckForException")
inline fun <reified TException : Throwable> assertThrows(
        expectedMessage: String,
        noinline action: () -> Unit
) {
    try {
        action()
        fail<Unit>("Action $action did not throw an exception")
    } catch (e: Throwable) {
        assertTrue(e is TException, "Action $action threw unexpected exception $e")
        assertEquals(expectedMessage, e.message)
    }
}

fun assertFails(action: () -> Unit) {
    assertThrows<AssertionFailedError>(action)
}

fun assertFails(expectedMessage: String, action: () -> Unit) {
    assertThrows<AssertionFailedError>(expectedMessage, action)
}

@Suppress("TooGenericExceptionCaught")
fun assertDoesNotThrow(action: () -> Unit) {
    try {
        action()
    } catch (e: Throwable) {
        val s = StringWriter()
        val w = PrintWriter(s)
        e.printStackTrace(w)
        fail<Unit>("Action $action threw unexpected exception $e\n${s.buffer}")
    }
}
