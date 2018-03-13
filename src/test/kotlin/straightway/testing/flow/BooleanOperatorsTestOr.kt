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
import straightway.expr.FunExpr
import straightway.expr.Value

class BooleanOperatorsTestOr {

    @Test
    fun isTrueIfBothArgumentsAreTrue() =
            expect((Value(true) or Value(true))() is_ Equal to_ true)

    @Test
    fun isTrueIfOnlyFirstArgumentIsFalse() =
            expect((Value(false) or Value(true))() is_ Equal to_ true)

    @Test
    fun isTrueIfOnlySecondArgumentIsFalse() =
            expect((Value(true) or Value(false))() is_ Equal to_ true)

    @Test
    fun isFalseIfBothArgumentsAreFalse() =
            expect((Value(false) or Value(false))() is_ Equal to_ false)

    @Test
    fun passesArgumentsToBothSubExpressions() {
        val left = FunExpr("left") { a -> expect(a is_ Equal to_ 83); true }
        val right = FunExpr("right") { a -> expect(a is_ Equal to_ 83); true }
        expect((left or right)(83) is_ Equal to_ true)
    }

    @Test
    fun toString_yieldsProperString() =
            expect((Value(true) or Value(false)).toString() is_ Equal to_ "true or false")
}