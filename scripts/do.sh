#!/usr/bin/env bash
./gradlew clean buildNeeded javadocJar test jacocoTestReport dependencyUpdates -Drevision=release