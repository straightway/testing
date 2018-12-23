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
import straightway.expr.Value
import straightway.expr.minus
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class ExpectTest {

    @Test
    fun assertionResultExpression_notSuccessful_isFailure() =
            assertFails { expect(Value(AssertionResult("Explanation", false))) }

    @Test
    fun nonAssertionResultExpression_isFailure() =
            assertFails(
                    Regex(".*Expectation <1> failed.*java.lang.ClassCastException.*" +
                            "java\\.lang\\.Integer cannot be cast to .*" +
                            "straightway\\.testing\\.flow\\.AssertionResult.*")) {
                expect(Value(1))
            }

    @Test
    fun assertionResultExpression_notSuccessful_isSuccess() =
            assertDoesNotThrow { expect(Value(AssertionResult("Explanation", true))) }

    @Test
    fun failure_singleDyadicOp_withMeaningfulExplanation() =
            assertFails(Regex("Expectation 1 > 2 failed.*")) {
                expect(1 is_ Greater than 2)
            }

    @Test
    fun failure_monadicWithDyadicOp_withMeaningfulExplanation() =
            assertFails(Regex(".*Expectation not 1 == 1 failed.*")) {
                expect(1 is_ Not - Equal to_ 1)
            }

    @Test
    fun failure_notFullyBoundExpression_withMeaningfulExplanation() =
            assertFails(
                    "Expectation <1 Greater ?> failed " +
                            "(Invalid number of parameters. Expected: 2, got: 1)") {
                expect(1 is_ Greater)
            }

    @Test
    fun `failure with AssertionResult expression does not show Expected and Actual in message`() =
            assertFails("Expectation Explanation failed.") {
                expect(Value(AssertionResult("Explanation", false)))
            }

    @Test
    fun success_directlyUsingBoolean() =
            assertDoesNotThrow { expect(true) }

    @Test
    fun failure_directlyUsingBoolean() =
            assertFails { expect(false) }

    @Test
    fun success_directlyUsingBoolean_withExplanation() =
            assertDoesNotThrow { expect(true) { "Explanation" } }

    @Test
    fun failure_directlyUsingBoolean_withExplanation() =
            assertFails(Regex("Explanation.*")) { expect(false) { "Explanation" } }
}