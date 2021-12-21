# Kubernetes
Запуск проекта при помощи K8s:
1. Скопируйте файлы из этой папки.
2. K8s должен быть готов к работе. Я использовал _minikube_
```shell script
minikube start
```
3. Подключение Secret
```shell script
kubectl apply -f postgresdb-secret.yml
```
4. Подключение ConfigMap
```shell script
kubectl apply -f postgresdb-configmap.yml
```
5. Запуск Postgres
```shell script
kubectl apply -f postgresdb-deployment.yml
```
6. Запуск сам сервис
```shell script
kubectl apply -f spring-deployment.yml
```

После запуска проекта, чтобы узнать ip адрес наберите
```shell script
minikube service spring-boot-service
```
## Упрощенный вариант

1. Скопируйте файлы из этой папки.
2. K8s должен быть готов к работе. Я использовал _minikube_
```shell script
minikube start
```
3. Разрешите исполнение скрипта
```shell script
sudo chmod +x startPassport.sh
```
4. Запустите всё сразу.
```shell script
./startPassport.sh
```
После запуска проекта, чтобы узнать ip адрес наберите 
```shell script
minikube service spring-boot-service
```
