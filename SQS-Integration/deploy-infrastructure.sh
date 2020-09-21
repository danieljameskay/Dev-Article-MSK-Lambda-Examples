#!/bin/sh

aws cloudformation create-stack --template-body file://./sqs-template.yaml  \
--stack-name $1 --capabilities CAPABILITY_NAMED_IAM \
--parameters ParameterKey=QueueNameParam,ParameterValue=$2 \
