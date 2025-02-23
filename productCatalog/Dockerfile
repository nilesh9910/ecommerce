FROM ubuntu:22.04

# Install dependencies
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    curl \
    unzip \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Install Kafka Connect
RUN curl -O https://packages.confluent.io/confluent-hub/confluent-hub-client-latest.tar.gz && \
    tar -xzf confluent-hub-client-latest.tar.gz && \
    mv confluent-hub-client/bin/confluent-hub /usr/local/bin/ && \
    chmod +x /usr/local/bin/confluent-hub && \
    rm -rf confluent-hub-client-latest.tar.gz confluent-hub-client

# Install Debezium and Elasticsearch connectors
RUN confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:latest && \
    confluent-hub install --no-prompt debezium/debezium-connector-mysql:latest