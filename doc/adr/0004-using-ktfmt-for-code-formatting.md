# 4. using-ktfmt-for-code-formatting

Date: 2024-02-27
Relates To: [using-spotless](0003-using-spotless.md)

## Status

Accepted

## Context

I want a code formatter that is less work to integrate and maintain.
ktlint has so many options, and is hard to get things to work consistently between IDE and ktlint when configuring
those options extensively.

ktfmt is much more opinionated.  You can really only pick between a couple of basic code styles so it avoids possible
bikeshedding related to styling rules.  ktfmt provides `Google`, "Facebook" or "KotlinLang" styles to chosse from.

Both options are supported by Spotless Gradle plugin.

## Decision

We'll leverage ktfmt with the Kotlinlang style, and adjust the max line length to 125.

## Consequences

We shouldn't have to spend much time debating style rules.  We might not also have a style that fits exactly what 
everyone would ideally want for themselves but we'll be consistent across the project(s).
