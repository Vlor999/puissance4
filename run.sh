#!/bin/bash

echo "🚀 Compilation du projet..."
mvn clean compile

echo "✅ Lancement du projet..."
mvn exec:java -Dexec.mainClass="Main"