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
package straightway.testing.bdd

/**
 * Continuation of a given/when/then clause. @see Given
 */
class GivenWhen<TGiven, TResult>(
        val given: TGiven,
        val result: WhenResult<TResult>
) {
    @Suppress("TooGenericExceptionCaught")
    infix fun then(op: TGiven.(WhenResult<TResult>) -> Unit) =
        try {
            val givenResult = given.op(result)
            throwIgnoredResultException()
            givenResult
        } catch (e: Throwable) {
            throwIgnoredResultException()
            throw e
        }

    private fun throwIgnoredResultException() {
        if (result.hasIgnoredException)
            throw result.exception as Throwable
    }
}