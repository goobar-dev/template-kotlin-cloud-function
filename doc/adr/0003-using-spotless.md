# 3. using-spotless

Date: 2024-02-27
Relates To: [using-ktfmt-for-code-formatting](0004-using-ktfmt-for-code-formatting.md)

## Status

Accepted

## Context

I want to make sure code is consistently formatting across the project.
I want to avoid excessive bikeshedding on style choices.
I want it to be easy for a dev to check their styling and reformat when necessary.

### Direct Integration
Conceptually, the most straightforward option would be to integrate a tool like ktlint for ktfmt directly.
This would result in one less tool.

The drawback to this though is that integrating these tools can actually be surprising complicated, and the integration 
becomes difficult to swap out.

Additionally, each tool will come with its own Gradle commands to learn and run to check styling and applying fixes.
This can be mitigated through custom tasks or makefiles, but is additional work either way, and might be different across projects.

### Using Spotless
Spotless is a tool that supports numerous languages and build tools (Kotlin, Scala, graphql, etc)
It provides a consistent api around checking code formatting and applying fixes.
For each supported platform, it offers configuration options to leverage common formatters.  
For Kotlin, that includes ktlint and ktfmt.

When integrating formatting tools through Spotless, we get the same tasks whether using ktlint, ktfmt or any other tool.
When could swap one formatter for another and keep the same Gradle tasks.

## Decision

Because of the cross-compatible nature of Spotless, the ease of integration and it's support for different formatters
we will use the Spotless Gradle plugin to provide code style verification tasks.

## Consequences

In choosing Spotless, we'll have to pick between the available formatters Spotless supports to actually provide the 
code style rules and fixes.  These options include:
- ktfmt
- ktlint
- dikat
- prettier
