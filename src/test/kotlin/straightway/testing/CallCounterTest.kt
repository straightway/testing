/****************************************************************************
Copyright 2016 github.com/straightway

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ****************************************************************************/
package straightway.testing

import org.junit.jupiter.api.Test
import straightway.testing.flow._is
import straightway.testing.flow._to
import straightway.testing.flow.equal
import straightway.testing.flow.expect

class CallCounterTest {

    @Test
    fun callCountIsInitiallyZero()
        = expect(CallCounter().calls _is equal _to 0)

    @Test
    fun callCountIsOneAfterOneCall() {
        val sut = CallCounter()
        sut.action()
        expect(sut.calls _is equal _to 1)
    }
}