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

import java.time.Duration
import java.time.LocalDateTime

/**
 * Relation checking if two objects are equal within a given range.
 */
class EqualWithin(private val range: Any) :
        EqualBase({ a: Any?, b: Any? ->
            AssertionResult(
                    "${a.formatted()} == ${b.formatted()} [+/- ${range.formatted()}]",
                    range.areEqualWithin(a, b))
        }) {

    companion object {
        fun Any?.areEqualWithin(a: Any?, b: Any?) =
                if (a is Number && b is Number && this is Number)
                    a.isWithinRangeOf(b, this)
                else if (a is LocalDateTime && b is LocalDateTime && this is Duration)
                    distance(a, b) < this
                else a == b

        private fun Number.isWithinRangeOf(other: Number, range: Number): Boolean =
                if (this.isFloatingPoint || other.isFloatingPoint || range.isFloatingPoint)
                    Math.abs(toDouble() - other.toDouble()) < range.toDouble()
                else Math.abs(toLong() - other.toLong()) < range.toLong()

        private val Number.isFloatingPoint get() = this is Float || this is Double

        private fun distance(a: LocalDateTime, b: LocalDateTime) =
                if (a < b) Duration.between(a, b) else Duration.between(b, a)
    }
}