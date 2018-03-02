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
import straightway.error.Panic
import straightway.expr.minus
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class EffectTestThrow {
    @Test
    fun passes() =
            assertDoesNotThrow { expect(::panic does throw_<Exception>()) }

    @Test
    fun passes_withSpecificException() =
            assertDoesNotThrow { expect(::panic does throw_<Panic>()) }

    @Test
    fun negation_passes() =
            assertDoesNotThrow { expect(::throwNothing does not - throw_<Exception>()) }

    @Test
    fun negation_passes_withSpecificException() =
            assertDoesNotThrow { expect(::panic does not - throw_<NullPointerException>()) }

    @Test
    fun fails() =
            assertFails { expect(::throwNothing does throw_<Exception>()) }

    @Test
    fun fails_withSpecificException() =
            assertFails { expect(::panic does throw_<NullPointerException>()) }

    @Test
    fun negation_fails() =
            assertFails { expect(::panic does not - throw_<Exception>()) }

    @Test
    fun fails_withMeaningfulMessage() =
            assertFails(
                    "Expectation <class java.lang.Exception thrown by fun throwNothing(): " +
                            "kotlin.Unit> failed") {
                expect(::throwNothing does throw_<Exception>())
            }

    @Test
    fun negated_fails_withMeaningfulMessage() =
            assertFails(
                    "Expectation <class java.lang.Exception not-thrown by fun panic(): " +
                            "kotlin.Unit> failed") {
                expect(::panic does not - throw_<Exception>())
            }
}

private fun throwNothing() {}
private fun panic() {
    throw Panic("panic!")
}