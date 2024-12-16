#!/bin/bash
EXIT_STATUS=0

modules=("bean-introspection" "bean-mapping" "at-builder" "json-serialization-serde" "http-client-netty-manual" "http-client-netty-declarative" "routing-controller-get" "routing-jaxrs-get" "routing-controller-method-httprequest-param" "routing-path-param" "routing-controller-query-param" "routing-controller-query-param-validation")

for module in "${modules[@]}"; do
  cd "$module" || exit 1
  ./gradlew build || EXIT_STATUS=$?
  if [ $EXIT_STATUS -ne 0 ]; then
    exit $EXIT_STATUS
  fi
  ./gradlew nativeTest || EXIT_STATUS=$?
  if [ $EXIT_STATUS -ne 0 ]; then
    exit $EXIT_STATUS
  fi
  cd ..
done

exit $EXIT_STATUS