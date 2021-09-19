#! /bin/bash

ARTIFACT_NAME=`find . -name "demo-*"`

if [ "$1" = 'demo' ]; then
    java $JAVA_OPTS -jar $APP_HOME/$ARTIFACT_NAME
else
    exec "$@"
fi


