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

import straightway.expr.BoundExpr
import straightway.expr.FunExpr
import straightway.expr.StateExpr
import straightway.expr.Value
import straightway.expr.inState
import straightway.expr.untyped
import kotlin.reflect.KClass

/**
 * Operator checking if the argument is a code block throwing an exception.
 */
@Suppress("TooGenericExceptionCaught")
object Throw :
        StateExpr<Effect>,
        FunExpr(
                "thrown by",
                untyped { exception: KClass<*>, action: () -> Unit ->
                    try {
                        action()
                        AssertionResult("$exception thrown, but nothing thrown", false)
                    } catch (e: Throwable) {
                        val isCorrectException = exception.isInstance(e)
                        if (isCorrectException) AssertionResult("exception $exception thrown", true)
                        else AssertionResult(
                                "$exception thrown (got: ${e::class}, " +
                                "stack trace:\n  ${e.stackTrace.joinToString("\n  ")}", false)
                    }
                }) {

    val exception = BoundExpr(this, Value(Throwable::class)).inState<Effect>()
    inline fun <reified T : Throwable> type() = BoundExpr(this, Value(T::class)).inState<Effect>()
}