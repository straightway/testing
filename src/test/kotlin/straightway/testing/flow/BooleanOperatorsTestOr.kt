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

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import straightway.expr.FunExpr
import straightway.expr.Value

class BooleanOperatorsTestOr {

    @Test
    fun isTrueIfBothArgumentsAreTrue() =
            Assertions.assertEquals(
                    AssertionResult("[Success: A or Success: B]", true),
                    (Value(AssertionResult("A", true)) or
                            Value(AssertionResult("B", true)))())

    @Test
    fun isTrueIfOnlyFirstArgumentIsFalse() =
            Assertions.assertEquals(
                    AssertionResult("[Failure: A or Success: B]", true),
                    (Value(AssertionResult("A", false)) or
                            Value(AssertionResult("B", true)))())

    @Test
    fun isTrueIfOnlySecondArgumentIsFalse() =
            Assertions.assertEquals(
                    AssertionResult("[Success: A or Failure: B]", true),
                    (Value(AssertionResult("A", true)) or
                            Value(AssertionResult("B", false)))())

    @Test
    fun isFalseIfBothArgumentsAreFalse() =
            Assertions.assertEquals(
                    AssertionResult("[Failure: A or Failure: B]", false),
                    (Value(AssertionResult("A", false)) or
                            Value(AssertionResult("B", false)))())

    @Test
    fun passesArgumentsToBothSubExpressions() {
        val left = FunExpr("left") { a ->
            expect(a is_ Equal to_ 83)
            AssertionResult.success("left") }
        val right = FunExpr("right") { a ->
            expect(a is_ Equal to_ 83)
            AssertionResult.success("right") }
        Assertions.assertEquals(
                AssertionResult("[Success: left or Success: right]", true),
                (left or right)(83)
        )
    }

    @Test
    fun toString_yieldsProperString() =
            Assertions.assertEquals(
                    "Success: SuccessExplanation or Failure: FailureExplanation",
                    (Value(AssertionResult("SuccessExplanation", true)) or
                            Value(AssertionResult("FailureExplanation", false))).toString())
}