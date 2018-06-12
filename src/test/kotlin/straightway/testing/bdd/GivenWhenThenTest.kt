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

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import straightway.error.Panic
import straightway.expr.minus
import straightway.testing.flow.Not
import straightway.testing.flow.Null
import straightway.testing.flow.Throw
import straightway.testing.flow.does
import straightway.testing.flow.Equal
import straightway.testing.flow.expect
import straightway.testing.flow.is_
import straightway.testing.flow.to_

class GivenWhenThenTest {

    @Test
    fun exampleFromGivenDocumentation() =
            Given {
                object {
                    val value = 3
                }
            } when_ {
                value + 2
            } then {
                expect(it is_ Not - Equal to_ value)
            }

    @Test
    fun `exception in when_ can be evaluated in then using result`() =
            Given {
                "Aaaaah!"
            } when_ {
                throw Panic(this)
            } then {
                expect({ it.result } does Throw.type<Panic>())
            }

    @Test
    fun `exception in when_ can be evaluated in then using exception`() =
            Given {
                "Aaaaah!"
            } when_ {
                throw Panic(this)
            } then {
                expect((it.exception as Panic).state is_ Equal to_ "Aaaaah!")
            }

    @Test
    fun `exception in when_ can be evaluated in then using nullableResult`() =
            Given {
                "Aaaaah!"
            } when_ {
                throw Panic(this)
            } then {
                expect({ it.nullableResult } does Throw.type<Panic>())
            }

    @Test
    fun `exception in when leads to failing test if not checked`() =
           expect({
               Given {} when_ { throw Panic("Aah!") } then {}
           } does Throw.type<Panic>())

    @Test
    @Suppress("ThrowsCount")
    fun `exception in when is not hidden by another exception in then`() =
            expect({
                Given {} when_ { throw Panic("Aah!") } then { throw CloneNotSupportedException() }
            } does Throw.type<Panic>())

    @Test
    fun `exception in then is not swallowed`() =
            expect({
                Given {} when_ {} then { throw Panic("Aaaaah!") }
            } does Throw.type<Panic>())

    @Test
    fun `exception is null if no exception was thrown`() =
            Given {} when_ {} then { expect(it.exception is_ Null) }

    @Test
    fun `not null result exists`() =
            Given {} when_ { 1 } then { expect(it.nullableResult is_ Not - Null) }

    @Test
    fun `null result is null`() =
            Given {} when_ { null } then { expect(it.nullableResult is_ Null) }

    @Test
    fun `Unit result is not null`() =
            Given {} when_ {} then { expect(it.nullableResult is_ Not - Null) }

    @Test
    fun `null result of nullable type throws NullPointerException on result query`() =
            Given {} when_ { null } then {
                expect({ it.result } does Throw.type<NullPointerException>())
            }

    @Test
    fun `if given object implements AutoCloseable, it is closed when then block is finished`() {
        val autoCloseable = mock<AutoCloseable>()
        Given {
            autoCloseable
        } when_ {} then {
            verify(autoCloseable, never()).close()
        }

        verify(autoCloseable).close()
    }

    @Test
    fun `if given object implements AutoCloseable, it is closed when then block throws`() {
        val autoCloseable = mock<AutoCloseable>()
        try {
            Given {
                autoCloseable
            } when_ {} then {
                verify(autoCloseable, never()).close()
                throw Panic("Aah!")
            }
        } catch (e: Panic) {
            // Ignorw
        }

        verify(autoCloseable).close()
    }
}