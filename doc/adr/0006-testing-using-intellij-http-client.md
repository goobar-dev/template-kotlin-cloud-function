# 6. testing-using-intellij-http-client

Date: 2024-02-27

## Status

Accepted

## Context

Testing a Cloud Function locally or in the cloud is something we want to do very often during development.
This often means we will create a curl command, or Postman request to repeatedly make the same request in the same way.

IntelliJ Ultimate has an HTTP Client built into it that allows for a Postman-like notation for defining requests.
These requests are defined via the .http file extension.
The HTTP Client tooling allows for defining environments and variables so you can differentiate between you local, dev, or
other environments.

When building with IntelliJ, having these files pre-defined and stored in a repo is very convenient for a productive
developer experience.

A drawback to these files are that the HTTP Client is only available in the paid versions of JetBrains IDEs which not
everyone has.

## Decision

We'll add sample requests in individual files within a `docs/requests` directory to make testing easier.

## Consequences

Devs without IntelliJ Ultimate would likely not be able to use these files directly.
However, they could use the IDE to generate a sample curl command from the file and run that instead.
