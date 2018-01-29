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
package straightway.testing

import org.junit.jupiter.api.Test
import straightway.testing.flow.Throw
import straightway.testing.flow.does
import straightway.testing.flow.equal
import straightway.testing.flow.expect
import straightway.testing.flow.is_
import straightway.testing.flow.minus
import straightway.testing.flow.to_

class TestBaseTest {

    class TestSUT : TestBase<Int>() {
        fun getSut() = sut
        fun setSut(sut: Int) { this.sut = sut }
    }

    @Test
    fun callingUnsetSutThrows() =
            expect({ TestSUT().getSut() } does Throw - NullPointerException::class)

    @Test
    fun settingSut() {
        val testSUT = TestSUT()
        testSUT.setSut(83)
        expect(testSUT.getSut() is_ equal to_ 83)
    }

    @Test
    fun tearDownResetsSut() {
        val testSUT = TestSUT()
        testSUT.setSut(83)
        testSUT.tearDown()
        expect({ testSUT.getSut() } does Throw - NullPointerException::class)
    }
}