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
package straightway.testing.flow

import org.junit.jupiter.api.Test
import straightway.expr.minus
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class RelationTestNull {

    @Test
    fun null_is_null() = assertDoesNotThrow { expect(null is_ Null) }

    @Test
    fun null_is_not_notnull() = assertFails { expect(null is_ not - Null) }

    @Test
    fun notNull_is_not_null() = assertDoesNotThrow { expect(1 is_ not - Null) }

    @Test
    fun notNull_is_notnull() = assertFails { expect(1 is_ Null) }
}