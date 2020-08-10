#!/usr/bin/env bash

check_services () {
  TABLES_OUTPUT="$(awslocal dynamodb list-tables)"
  QUEUES_OUTPUT="$(awslocal sqs list-queues)"
  if [[ $TABLES_OUTPUT == *"locations"*
    && $TABLES_OUTPUT == *"establishments"*
    && $QUEUES_OUTPUT == *"creation-vehicle-location-dead-lettter-queue"*
    && $QUEUES_OUTPUT == *"creation-vehicle-location-queue"*
    ]]; then
    echo "$TABLES_OUTPUT"
    echo "$QUEUES_OUTPUT"
    return 0
  else
    echo "$TABLES_OUTPUT"
    echo "$QUEUES_OUTPUT"
    return 1
  fi
}
check_services