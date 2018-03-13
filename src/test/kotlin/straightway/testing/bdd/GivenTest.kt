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
import straightway.testing.flow.Equal
import straightway.testing.flow.expect
import straightway.testing.flow.is_
import straightway.testing.flow.to_

class GivenTest {

    @Test
    fun `construction using a GivenContext object`() {
        val context = Any()
        val sut = Given { context }
        expect(sut.context is_ Same as_ context)
    }

    @Test
    fun fieldsOfContextAreAccessible() {
        val sut = Given {
            object {
                val field = "Hello"
            }
        }
        expect(sut.context.field is_ Equal to_ "Hello")
    }

    @Test
    fun `when_ yields GivenWhen instance with given context`() {
        val context = Any()
        val result = Given { context }.when_ { "Hello" }
        expect(result.given is_ Same as_ context)
    }

    @Test
    fun `when_ yields GivenWhen instance with when result`() {
        val context = Any()
        val givenWhen = Given { context }.when_ { "Hello" }
        expect(givenWhen.result.result is_ Equal to_ "Hello")
    }
}