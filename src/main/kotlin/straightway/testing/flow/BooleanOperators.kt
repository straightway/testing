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

import straightway.expr.DistributedExpr
import straightway.expr.Expr

/*
 * Operator which distributes its arguments to the left and right expressions and yields
 * the logical conjunction of their boolean results.
 */
infix fun Expr.and(other: Expr): Expr =
        DistributedExpr("and", this, other) {
            val leftResult = left(*it) as AssertionResult
            val rightResult = right(*it) as AssertionResult
            AssertionResult(
                    "[$leftResult and $rightResult]",
                    leftResult.isSuccessful && rightResult.isSuccessful)
        }

/**
 * Operator which distributes its arguments to the left and right expressions and yields
 * the logical disjunction of their boolean results.
 */
infix fun Expr.or(other: Expr): Expr =
        DistributedExpr("or", this, other) {
            val leftResult = left(*it) as AssertionResult
            val rightResult = right(*it) as AssertionResult
            AssertionResult(
                    "[$leftResult or $rightResult]",
                    leftResult.isSuccessful || rightResult.isSuccessful)
        }
