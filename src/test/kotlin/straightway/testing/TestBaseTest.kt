/*
 * ***************************************************************************
 * Copyright 2016 github.com/straightway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  ***************************************************************************
 */
package straightway.testing

import org.junit.jupiter.api.Test
import straightway.testing.flow.*

class TestBaseTest {

    class Testee : TestBase<Int>() {
        fun getSut() = sut
        fun setSut(sut: Int) { this.sut = sut }
    }

    @Test
    fun callingUnsetSutThrows() =
        expect({ Testee().getSut() } does _throw-NullPointerException::class)

    @Test
    fun settingSut() {
        val testee = Testee()
        testee.setSut(83)
        expect(testee.getSut() _is equal _to 83)
    }

    @Test
    fun tearDownResetsSut() {
        val testee = Testee()
        testee.setSut(83)
        testee.tearDown()
        expect({ testee.getSut() } does _throw-NullPointerException::class)
    }
}