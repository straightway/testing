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

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.fail
import java.lang.NullPointerException
import java.util.*

class TraceOnFailureTest {

    @BeforeEach
    fun setup() {
        sut = TraceOnFailure()

        testInstance = Optional.of(mock<TestTraceProvider> {
            on { traces }.thenAnswer { listOf("Hello", "World") }
        })

        executionException = Optional.of(NullPointerException())

        context = mock {
            on { executionException }.thenAnswer { executionException }
            on { testInstance }.thenAnswer { testInstance }
        }
    }

    @Test
    fun `traces are reported on exception`() {
        sut.afterTestExecution(context)
        verify(context).publishReportEntry("trace", "{\n  Hello\n  World\n}")
    }

    @Test
    fun `traces are not reported when context is null`() {
        sut.afterTestExecution(null)
        verify(context, never()).publishReportEntry(any(), any())
    }

    @Test
    fun `traces are not reported when context has null executionException`() {
        executionException = null
        sut.afterTestExecution(context)
        verify(context, never()).publishReportEntry(any(), any())
    }

    @Test
    fun `traces are not reported when context has no executionException`() {
        executionException = Optional.empty()
        sut.afterTestExecution(context)
        verify(context, never()).publishReportEntry(any(), any())
    }

    @Test
    fun `traces are not reported when testInstance is null`() {
        testInstance = null
        sut.afterTestExecution(context)
        verify(context, never()).publishReportEntry(any(), any())
    }

    @Test
    fun `traces are not reported when test does not have TestTraceProvider`() {
        testInstance = Optional.empty()
        sut.afterTestExecution(context)
        verify(context, never()).publishReportEntry(any(), any())
    }

    @Test
    fun `traces are not reported when test does not implement TestTraceProvider`() {
        testInstance = Optional.of(83)
        sut.afterTestExecution(context)
        verify(context, never()).publishReportEntry(any(), any())
    }

    private var testInstance: Optional<Any>? = null
    private lateinit var context: ExtensionContext
    private lateinit var sut: TraceOnFailure
    private var executionException: Optional<Throwable>? = null
}