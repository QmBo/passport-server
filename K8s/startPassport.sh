#!/bin/bash
kubectl apply -f postgresdb-secret.yml && kubectl apply -f postgresdb-configmap.yml && kubectl apply -f postgresdb-deployment.yml && kubectl apply -f spring-deployment.yml