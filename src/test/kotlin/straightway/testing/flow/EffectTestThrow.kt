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
import java.io.InvalidClassException

class EffectTestThrow {
    @Test
    fun passes() =
            assertDoesNotThrow { expect(::panic does Throw.exception) }

    @Test
    fun passes_withSpecificException() =
            assertDoesNotThrow { expect(::panic does Throw.type<Panic>()) }

    @Test
    fun negation_passes() =
            assertDoesNotThrow { expect(::throwNothing does Not - Throw.exception) }

    @Test
    fun negation_passes_withSpecificException() =
            assertDoesNotThrow { expect(::panic does Not - Throw.type<NullPointerException>()) }

    @Test
    fun fails() =
            assertFails { expect(::throwNothing does Throw.exception) }

    @Test
    fun fails_withSpecificException() =
            assertFails { expect(::panic does Throw.type<NullPointerException>()) }

    @Test
    fun negation_fails() =
            assertFails { expect(::panic does Not - Throw.exception) }

    @Test
    fun fails_withMeaningfulMessage() =
            assertFails(
                    Regex("Expectation class kotlin.Throwable thrown, but nothing thrown.*")) {
                expect(::throwNothing does Throw.exception)
            }

    @Test
    fun `fails with different exception and stack trace`() =
            assertFails(Regex(
                    ".*stack trace:.*straightway.testing.flow.EffectTestThrowKt.panic.*",
                    RegexOption.DOT_MATCHES_ALL)) {
                expect(::panic does Throw.type<InvalidClassException>())
            }

    @Test
    fun negated_fails_withMeaningfulMessage() =
            assertFails(
                    Regex("Expectation not exception class kotlin.Throwable thrown failed.*")) {
                expect(::panic does Not - Throw.exception)
            }
}

private fun throwNothing() {}
private fun panic() {
    throw Panic("panic!")
}