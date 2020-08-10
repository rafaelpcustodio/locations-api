#!/bin/bash

awslocal dynamodb create-table \
--table-name establishments \
--attribute-definitions \
  AttributeName=id,AttributeType=S \
--key-schema \
  AttributeName=id,KeyType=HASH \
--provisioned-throughput \
ReadCapacityUnits=5,WriteCapacityUnits=5

awslocal dynamodb put-item \
	--table-name establishments \
	--item '{
	   "id": {"S": "fe50938e-4d7e-4e3c-a44b-ae1546b3e291"},
	   "name": {"S": "Restaurants"}
	}'

awslocal dynamodb put-item \
	--table-name establishments \
	--item '{
	   "id": {"S": "fe50938e-4d7e-4e3c-a44b-ae1546b3e292"},
	   "name": {"S": "Gas Stations"}
	}'

awslocal dynamodb put-item \
	--table-name establishments \
	--item '{
	   "id": {"S": "fe50938e-4d7e-4e3c-a44b-ae1546b3e293"},
	   "name": {"S": "Hotels"}
	}'