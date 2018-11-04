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

import straightway.expr.FunExpr
import straightway.expr.StateExpr

/**
 * Unary operator checking if its argument is a container and contains
 * a number of specified objects by value.
 */
class Values private constructor(val elements: Array<*>) :
        Relation,
        StateExpr<WithHas>,
        FunExpr(
                "Values[${elements.joinToString(", ")}]",
                { a: Any? ->
                    val iterable = a.asIterable
                    AssertionResult(
                            "[${iterable.joinToString(", ")}] " +
                            "contains all [${elements.joinToString(", ")}] ",
                            elements.all { iterable.contains(it) })
                }) {

    companion object {
        operator fun invoke(vararg elements: Any?) = Values(elements)
    }
}