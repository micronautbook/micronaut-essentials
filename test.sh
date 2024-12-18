#!/bin/bash
EXIT_STATUS=0

# "routing-jaxrs-get"
modules=("bean-introspection" "bean-mapping" "at-builder" "json-serialization-serde" "http-client-netty-manual" "http-client-netty-declarative" "routing-controller-get"  "routing-controller-method-httprequest-param" "routing-path-parameter" "routing-controller-query-param" "routing-controller-query-param-validation" "configuration-immutable-configuration-properties"  "configuration-configuration-properties")

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
