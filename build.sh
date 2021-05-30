#!/bin/sh

mvn clean install

docker build -t library-management-rest-api .