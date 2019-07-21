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

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import straightway.utils.joinMultiLine

/**
 * JUnit 5 extension which on failure issues traces collected during a test.
 * The test must implement the TestTraceProvider interface.
 */
class TraceOnFailure : AfterTestExecutionCallback {

    override fun afterTestExecution(context: ExtensionContext?) {
        if (context == null) return
        if (context.executionException?.isPresent != true) return
        if (context.testInstance?.isPresent != true) return
        val test = context.testInstance.get()
        if (test !is TestTraceProvider) return
        val traces = test.traces.joinMultiLine(2)
        context.publishReportEntry("trace", traces)
    }
}