#!/bin/sh

aws cloudformation create-stack --template-body file://./infrastructure-template.yaml  --stack-name $1 --capabilities CAPABILITY_NAMED_IAM \
--parameters \
ParameterKey=KeyName,ParameterValue=$2 \
ParameterKey=SSHLocation,ParameterValue=$3
