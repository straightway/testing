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
 * Result of the execution of a when_ block.
 */
data class WhenResult<TResult> private constructor(
        private val _result: TResult?,
        private val _exception: Throwable?
) {
    constructor(result: TResult) : this(result, null)

    val exception: Throwable? get() {
        _isExceptionIgnored = false
        return _exception
    }

    val hasIgnoredException get() = _exception != null && _isExceptionIgnored

    val result: TResult get() {
        _isExceptionIgnored = false
        return _result ?: throw _exception!!
    }

    val nullableResult: TResult? get() {
        _isExceptionIgnored = false
        return if (_exception != null) throw _exception else _result
    }

    private var _isExceptionIgnored = true

    companion object {
        fun <TResult> threw(exception: Throwable) = WhenResult<TResult>(null, exception)
    }
}