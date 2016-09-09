#!/bin/bash
echo "current path:"$(pwd)
cd ..
echo "begin maven deploy"
mvn clean deploy -U
echo "end maven deploy"
