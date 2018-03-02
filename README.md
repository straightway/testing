# testing

This kotlin library makes it more convenient to write unit tests using JUnit 5 in kotlin.
It provides a flow syntax interface for expectations (similar to NUnits Expect syntax), which
is far better readable than the JUnit asserts..

## Status

This software is in pre-release state. Every aspect of it may change without announcement or
notification or downward compatibility. As soon as version 1.0 is reached, all subsequent
changes for sub versions will be downward compatible. Breaking changes will then only occur
with a new major version with according deprecation marking.

## Include in gradle builds

To include this library in a gradle build, add

    repositories {
        ...
        maven { url "https://straightway.github.io/repo" }
    }

Then you can simply configure in your dependencies:

    dependencies {
        compile "straightway:testing:0.3+"
    }
