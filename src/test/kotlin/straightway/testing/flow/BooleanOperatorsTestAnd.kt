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
import org.junit.jupiter.api.Test
import straightway.expr.FunExpr
import straightway.expr.Value

class BooleanOperatorsTestAnd {

    @Test
    fun `is true if both arguments are boolean true`() =
            assertEquals(
                    AssertionResult("[Success: A and Success: B]", true),
                    (Value(AssertionResult("A", true)) and
                            Value(AssertionResult("B", true)))())

    @Test
    fun isFalseIfFirstArgumentIsFalse() =
            assertEquals(
                    AssertionResult("[Failure: A and Success: B]", false),
                    (Value(AssertionResult("A", false)) and
                            Value(AssertionResult("B", true)))())

    @Test
    fun isFalseIfSecondArgumentIsFalse() =
            assertEquals(
                    AssertionResult("[Success: A and Failure: B]", false),
                    (Value(AssertionResult("A", true)) and
                            Value(AssertionResult("B", false)))())

    @Test
    fun isFalseIfBothArgumentsAreFalse() =
            assertEquals(
                    AssertionResult("[Failure: A and Failure: B]", false),
            (Value(AssertionResult("A", false)) and
                    Value(AssertionResult("B", false)))())

    @Test
    fun passesArgumentsToBothSubExpressions() {
        val left = FunExpr("left") { a ->
            expect(a is_ Equal to_ 83)
            AssertionResult.success("left")
        }
        val right = FunExpr("right") { a ->
            expect(a is_ Equal to_ 83)
            AssertionResult.success("right")
        }
        assertEquals(
                AssertionResult("[Success: left and Success: right]", true),
                (left and right)(83)
        )
    }

    @Test
    fun toString_yieldsProperString() =
            assertEquals(
                    "Success: SuccessExplanation and Failure: FailureExplanation",
                    (Value(AssertionResult("SuccessExplanation", true)) and
                            Value(AssertionResult("FailureExplanation", false))).toString())
}