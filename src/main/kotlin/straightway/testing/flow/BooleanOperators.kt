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

import straightway.dsl.CombinableExpr
import straightway.dsl.DistributedExpr
import straightway.dsl.Expr
import straightway.dsl.FunExpr
import straightway.dsl.untyped

/**
 * Operator which logically negates its argument.
 */
object Not : CombinableExpr, FunExpr("Not", untyped<Boolean, Boolean> { !it })

/**
 * Operator which distributes its arguments to_ the left and right expressions and yields
 * the logical conjunction of their boolean results.
 */
infix fun Expr.and(other: Expr): Expr =
    DistributedExpr("and", this, other) { left(*it) as Boolean && right(*it) as Boolean }

/**
 * Operator which distributes its arguments to_ the left and right expressions and yields
 * the logical disjunction of their boolean results.
 */
infix fun Expr.or(other: Expr): Expr =
    DistributedExpr("or", this, other) { left(*it) as Boolean || right(*it) as Boolean }
