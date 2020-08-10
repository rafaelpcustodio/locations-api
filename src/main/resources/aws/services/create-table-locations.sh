#!/bin/bash

awslocal dynamodb create-table \
--table-name locations \
--attribute-definitions \
  AttributeName=licensePlate,AttributeType=S \
  AttributeName=createdAt,AttributeType=S \
--key-schema \
  AttributeName=licensePlate,KeyType=HASH \
  AttributeName=createdAt,KeyType=SORT \
--provisioned-throughput \
ReadCapacityUnits=5,WriteCapacityUnits=5

