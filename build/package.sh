#!/bin/bash
echo "current path:"$(pwd)
cd ..
echo "begin maven package"
mvn clean package -U
echo "end maven package"
