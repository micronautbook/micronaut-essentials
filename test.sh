#!/bin/bash
EXIT_STATUS=0
cd bean-introspection
./gradlew build || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
cd ..
cd bean-mapping
./gradlew build || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
cd ..
cd at-builder
./gradlew build || EXIT_STATUS=$?
if [ $EXIT_STATUS -ne 0 ]; then
  exit $EXIT_STATUS
fi
exit $EXIT_STATUS
