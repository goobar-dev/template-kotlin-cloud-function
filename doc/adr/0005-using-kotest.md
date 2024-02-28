# 5. using-kotest

Date: 2024-02-27

## Status

Accepted

## Context

Kotest is a set of Kotlin testing solutions that includes a test framework, assertions, and property testing utilities.
Kotest supports Kotlin Multiplatform so it can be used across any Kotlin projects we might be working with.

The Kotest Test Framework supports multiple testing styles through various "spec" classes.  These allow for extra options
in how we structure tests for different types of comopnents.

Kotest Assertions leverage Kotlin language features like infix functions to provide very fluent apis that work very well
with their spec classes.

## Decision

We'll leverage the Kotest Framework and Kotest Assertions by default, and can bring in the property testing facilities
as needed.

## Consequences

Developers will likely need to install the IntelliJ Kotest Plugin to get the ideal developer experience with Kotest.
