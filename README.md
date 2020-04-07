# Architecture
The architecture consists of three components in a Producer-Consumer model.

## FileReader (Producer)
The FileReader component is a service that reads a fixed width file in csv format. 
Each line in the file is stored in postgres database as a row. 
Each line is also pushed to a kafka stream as a message.

The input file `Input.csv` is moved to a different name `Input.csv.<timestamp>` to indicate the file ingestion was completed.

## Kafka (Messaging)
Kafka component exists as a stream for all the transactions that are received in Input.csv file.

## Transaction Summary (Consumer)
The Transaction Summary is a service that consumes the kafka messages and calculates the total transaction amount per client and product. 
The summary data is stored in a postgres database. The service exposes an api that can produce a json response or a csv files. 

# Gettting Started

## Prerequisites
* java8
* maven 3.x
* Docker engine must be installed on the machine
* kubernetes*

## How to run
Run the `run.sh` in bin folder. This will deploy containers used in this ecosystem.

If you wish to build the docker image locally to avoid downloading, please run `build.sh` in `file-reader` and `transaction-summary` folders.

The stack can be torndown with `.stop.sh` in the bin folder.

# Usage
Once all the services are up, 
The service exposes an api that can produce a json response or a csv files. 

`GET http://localhost:8080/summary` produces json response

`GET http://localhost:8080/summary-export` downloads a csv file.

Log files can be found under `file-reader/logs` and `transaction-summary/logs`
Sample csv file is included : `Output.csv`

# Work In Progress
* Deployment: Container orchestration: Kubernetes is currently a work in progress as I faced some issues with setting up kafka. 
  The related `yml` files can be found in `k8s` folder.
* File Reader: File polling mechanism improvements to read from local & ftp locations seamlessly.
