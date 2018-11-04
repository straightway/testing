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

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.opentest4j.AssertionFailedError
import straightway.error.Panic
import straightway.expr.Expr

/**
 * Expect the condition evaluated by the given expression to be true.
 */
@Suppress("TooGenericExceptionCaught")
fun expect(condition: Expr) {
    try {
        check(condition)
    } catch (e: AssertionFailedError) {
        throw e
    } catch (e: Panic) {
        fail<Unit>("Expectation <${ExpressionVisualizer(condition).string}> failed (${e.state})")
    } catch (e: Throwable) {
        fail<Unit>("Expectation <${ExpressionVisualizer(condition).string}> failed ($e)")
    }
}

/**
 * Expect the given condition to be true.
 */
fun expect(condition: Boolean) = assertTrue(condition)

/**
 * Expect the given condition to be true.
 */
fun expect(condition: Boolean, lazyExplanation: () -> String) =
        assertTrue(condition, lazyExplanation)

private fun check(condition: Expr) =
        with(condition() as AssertionResult) {
            if (!isSuccessful) fail<Unit> { "Expectation $explanation failed." }
        }
