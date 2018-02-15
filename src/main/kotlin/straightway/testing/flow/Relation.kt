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

import straightway.expr.Expr
import straightway.expr.FunExpr
import straightway.expr.StateExpr
import straightway.numbers.compareTo
import straightway.numbers.minus
import java.time.Duration
import java.time.LocalDateTime

/**
 * An expression which represents a relation between two object.
 */
interface Relation : Expr

/**
 * A relation which requires 'to' as preposition.
 */
interface WithTo : Relation

/**
 * A relation which requires 'as' as preposition.
 */
interface WithAs : Relation

/**
 * A relation which requires 'than' as preposition.
 */
interface WithThan : Relation

/**
 * A relation which requires 'has' as prepositional verb.
 */
interface WithHas : Relation

/**
 * A relation which requires 'of' as preposition.
 */
interface WithOf : Relation

/**
 * A relation which requires both, 'has' as prepositional verb and 'of' as preposition.
 */
interface WithHasAndOf : WithHas, WithOf

/**
 * A relation having only one parameter.
 */
interface Unary : Relation

/**
 * A relation determining if two values are equal.
 */
class Equal(
        predicate: (Any, Any) -> Boolean = { a, b -> a == b }
) : Relation, StateExpr<WithTo>, FunExpr("equal", predicate)

val equal = Equal()
fun equalWithin(range: Any) =
        Equal({ a, b ->
                  if (a is Number && b is Number && range is Number)
                      abs(a, b) < range
                  else if (a is LocalDateTime && b is LocalDateTime && range is Duration)
                      abs(a, b) < range
                  else a == b
              })

private fun abs(a: LocalDateTime, b: LocalDateTime) =
        if (a < b) Duration.between(a, b) else Duration.between(b, a)

private fun abs(a: Number, b: Number) = if (a < b) b - a else a - b

object Same
    : Relation, StateExpr<WithAs>, FunExpr("Same", { a, b -> a === b })

object Greater
    : Relation, StateExpr<WithThan>, FunExpr("Greater", { a, b -> 0 < a.untypedCompareTo(b) })

object Less
    : Relation, StateExpr<WithThan>, FunExpr("Less", { a, b -> a.untypedCompareTo(b) < 0 })

@Suppress("UNCHECKED_CAST")
private fun Any.untypedCompareTo(other: Any): Int =
        (this as Comparable<Any>).compareTo(other)
