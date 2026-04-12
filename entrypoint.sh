#!/bin/bash

java \
 -XX:+UseZGC -Xmx512m \
 -Dspring.profiles.active=production \
 -jar app.jar
