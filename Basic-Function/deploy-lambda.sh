#!/bin/bash

./gradlew shadowJar
aws s3 cp $1 $2
aws cloudformation create-stack --stack-name $3 --template-body file://./lambda-template.yaml --capabilities CAPABILITY_NAMED_IAM \
--parameters ParameterKey=EventSourceArn,ParameterValue=$4 \
ParameterKey=TopicName,ParameterValue=$5 \
ParameterKey=S3Bucket,ParameterValue=$6 \
ParameterKey=S3Key,ParameterValue=$7 \
ParameterKey=Handler,ParameterValue=$8 \