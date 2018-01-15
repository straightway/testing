/****************************************************************************
Copyright 2016 github.com/straightway

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ****************************************************************************/
package straightway.testing.flow

import org.opentest4j.AssertionFailedError
import straightway.dsl.*
import straightway.numbers.compareTo
import straightway.numbers.minus
import java.time.Duration
import java.time.LocalDateTime

/**
 * An expression which represents a relation between two object.
 */
interface Relation : Expr

interface WithTo : Relation
interface WithAs : Relation
interface WithThan : Relation
interface WithHas : Relation
interface WithOf : Relation
interface WithHasAndOf : WithHas, WithOf
interface Unary : Relation

class Equal(predicate: (Any, Any) -> Boolean = { a, b -> a == b })
    : Relation, StateExpr<WithTo>, FunExpr("equal", predicate)

val equal = Equal()
fun equalWithin(range: Any) = Equal({ a, b ->
    if (a is Number && b is Number && range is Number)
        (if (a < b) b - a else a - b) < range
    else if (a is LocalDateTime && b is LocalDateTime && range is Duration)
        (if (a < b) Duration.between(a, b) else Duration.between(b, a)) < range
    else a == b
})
object same
    : Relation, StateExpr<WithAs>, FunExpr("same", { a, b -> a === b })
object greater
    : Relation, StateExpr<WithThan>, FunExpr("greater", { a, b -> 0 < a.untypedCompareTo(b) })
object less
    : Relation, StateExpr<WithThan>, FunExpr("less", { a, b -> a.untypedCompareTo(b) < 0 })
object size
    : Relation, StateExpr<WithHasAndOf>, FunExpr("size", untyped { a: Any, s: Int ->
        (a as Iterable<*>).count() == s })

object _true
    : Relation, StateExpr<Unary>, FunExpr("true", { a -> a as Boolean })
object _false
    : Relation, StateExpr<Unary>, FunExpr("false", { a -> !(a as Boolean) })
object empty
    : Relation, StateExpr<Unary>, FunExpr("empty", { a: Any -> !a.asIterable().any() })

object _null
object _notNull

@Suppress("UNUSED_PARAMETER", "unused")
operator fun not.minus(arg: _null) = _notNull

infix fun <T: Relation> Any._is(op: StateExpr<T>) = BoundExpr(op, Value(this)).inState<T>()
infix fun <T: Comparable<T>, TRel: Relation> T._is(op: StateExpr<TRel>) = BoundExpr(op, Value(this)).inState<TRel>()

@Suppress("UNUSED_PARAMETER")
infix fun Any?._is(arg: _null) = (this === null) _is _true

@Suppress("UNUSED_PARAMETER")
infix fun Any?._is(arg: _notNull) = (this === null) _is _false

infix fun <T: Iterable<*>, TRel: WithHas> T.has(op: StateExpr<TRel>) = BoundExpr(op, Value(this)).inState<TRel>()
infix fun <TRel: WithHas> Array<*>.has(op: StateExpr<TRel>) = this.asList() has op
infix fun <TRel: WithHas> CharSequence.has(op: StateExpr<TRel>) = this.toList() has op

infix fun StateExpr<WithTo>._to(other: Any) = BoundExpr(this, Value(other))
infix fun StateExpr<WithAs>._as(other: Any) = BoundExpr(this, Value(other))
infix fun StateExpr<WithThan>.than(other: Any) = BoundExpr(this, Value(other))
infix fun <T: WithOf> StateExpr<T>.of(other: Any) = BoundExpr(this, Value(other))

@Suppress("UNCHECKED_CAST")
private fun Any.untypedCompareTo(other: Any): Int =
    (this as Comparable<Any>).compareTo(other)
private fun Any.asIterable() =
    when (this) {
        is Iterable<*> -> this
        is Array<*> -> this.asList()
        is CharSequence -> this.toList()
        else -> throw AssertionFailedError("Cannot convert $this _to iterable")
    }