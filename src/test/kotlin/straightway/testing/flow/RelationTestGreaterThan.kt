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
import straightway.dsl.minus
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class RelationTestGreaterThan {

    @Test fun succeeds_int() = assertDoesNotThrow { expect(2 is_ Greater than 1) }

    @Test fun succeeds_mixed() = assertDoesNotThrow { expect(OtherComparable is_ Greater than 1) }

    @Test fun fails() = assertFails { expect(1 is_ Greater than 1) }

    @Test fun negated_succeeds() = assertDoesNotThrow { expect(1 is_ Not - Greater than 1) }

    @Test fun negated_fails() = assertFails { expect(2 is_ Not - Greater than 1) }

    private object OtherComparable : Comparable<Int> {
        override fun compareTo(other: Int) = other
    }
}