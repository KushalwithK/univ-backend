#!/bin/bash
#nohup java -jar /path/to/app/hello-world.jar > /path/to/log.txt 2>&1 &
nohup java -jar backend-1.0.0.jar --spring.profiles.active=prod > log.txt 2>&1 &
echo $! > ./pid.file