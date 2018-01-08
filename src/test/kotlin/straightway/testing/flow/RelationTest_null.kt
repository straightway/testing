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
package straightway.testing.flow

import org.junit.jupiter.api.Test
import straightway.testing.assertDoesNotThrow
import straightway.testing.assertFails

class RelationTest_null {

    @Test
    fun null_is_null() = assertDoesNotThrow { expect(null _is _null) }

    @Test
    fun null_is_not_notnull() = assertFails { expect(null _is not-_null) }

    @Test
    fun notNull_is_not_null() = assertDoesNotThrow { expect(1 _is not-_null) }

    @Test
    fun notNull_is_notnull() = assertFails { expect(1 _is _null) }
}