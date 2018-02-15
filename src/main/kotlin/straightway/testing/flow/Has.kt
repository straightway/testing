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
import straightway.expr.StateExpr
import straightway.expr.Value
import straightway.expr.inState

infix fun <T : Iterable<*>, TRel : WithHas> T.has(op: StateExpr<TRel>) =
        BoundExpr(op, Value(this)).inState<TRel>()

infix fun <TRel : WithHas> Array<*>.has(op: StateExpr<TRel>) =
        this.asList() has op

infix fun <TRel : WithHas> CharSequence.has(op: StateExpr<TRel>) =
        this.toList() has op
