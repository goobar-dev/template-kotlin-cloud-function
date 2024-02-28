# 2. using-gradle

Date: 2024-02-27

## Status

Accepted

## Context

The Cloud Function documentation calls out both Maven and Gradle use cases.

In my experience, deploying a Cloud Function build with Maven requires slightly less work as some of the automated deployment
tooling is setup to recognize a pom.xml file and can build the project from that.

The same automation does not appear to be in place for Gradle projects.  When using Gradle, it appears necessary to
deploy your Cloud Function using a pre-built .jar.

The Cloud Function documentation does give full examples for both build systems however, and I am much more comfortable
using Gradle.

## Decision

I chose Gradle for this project to be consistent with other Kotlin projects so there's unification of build tooling, plugins, etc.

## Consequences

This will require deploying the Cloud Function as .jar file.  Building this jar with all the required dependencies also
requires integration of the [shadow Gradle plugin](https://github.com/johnrengelman/shadow) to build a far JAR.
