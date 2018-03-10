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
import straightway.testing.flow.equal
import straightway.testing.flow.expect
import straightway.testing.flow.is_
import straightway.testing.flow.to_

class ReUseGivenContextInUnitTest {

    private val test get() = Given { object { var x = 7 } }

    @Test
    fun `use and modify given context in one test`() =
            test when_ { x += 4 } then { expect(x is_ equal to_ 11) }

    @Test
    fun `use and modify given context in another test`() =
            test when_ { x += 6 } then { expect(x is_ equal to_ 13) }
}