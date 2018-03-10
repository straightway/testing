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

import org.junit.jupiter.api.Test
import straightway.testing.flow.Same
import straightway.testing.flow.as_
import straightway.testing.flow.equal
import straightway.testing.flow.expect
import straightway.testing.flow.is_
import straightway.testing.flow.to_

class GivenWhenTest {

    @Test
    fun `then calls specified code block`() {
        val context = Any()
        val sut = GivenWhen(context, WhenResult("Hello"))
        var isCalled = false
        sut.then { isCalled = true }
        expect(isCalled)
    }

    @Test
    fun `then is called on given context`() {
        val context = Any()
        val sut = GivenWhen(context, WhenResult("Hello"))
        sut.then { expect(this is_ Same as_ context) }
    }

    @Test
    fun `then passes the when result`() {
        val context = Any()
        val sut = GivenWhen(context, WhenResult("Hello"))
        sut.then { expect(it.result is_ equal to_ "Hello") }
    }
}