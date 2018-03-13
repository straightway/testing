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
 * Start a BDD style given/when/then clause by giving a context or a delegate providing
 * a context. It is especially useful to return objects of anonymous type here. Example:
 *
 * Given {
 *   object {
 *     val value = 3
 *   }
 * } when_ {
 *   value + 2
 * } then {
 *   expect(it is_ Not-Equal to_ value)
 * }
 */
class Given<TContext> private constructor(val context: TContext) {

    constructor(initializer: () -> TContext) : this(initializer())

    @Suppress("TooGenericExceptionCaught", "FunctionNaming")
    infix fun <TResult> when_(codeBlock: TContext.() -> TResult): GivenWhen<TContext, TResult> {
        return try {
            GivenWhen(context, WhenResult(context.codeBlock()))
        } catch (ex: Throwable) {
            GivenWhen(context, WhenResult.threw(ex))
        }
    }
}