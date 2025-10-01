#!/bin/bash
cd pos-backend
./mvnw clean package -DskipTests
java -jar target/*.jar
