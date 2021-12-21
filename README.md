## Это серверная часть проекта демонстрирующего синхронный обмен данными между сервисами.
### Выполнено в рамках прохождения курса [Job4J.ru](https://job4j.ru/)
# Что делает?
Простоя реализация репозитория с доступом через REST API.

Сервис является прослойкой между базой данных и сервисами потребителями этих данных.
Сервис поднимается на порту 8080.

Используется:
* Spring Boot
* Spring Data
* Spring Liquibase

Обрабатываемые запросы:
* GET `localhost:8080/api/passport/find` возвращает все паспорта 
* GET `localhost:8080/api/passport/find?serial=*` возвращает паспорт по серийному номеру
* POST `localhost:8080/api/passport/save` сохраняет переданный паспорт
* DELETE`localhost:8080/api/passport/delete?id=*` удаляет паспорт по id
* PUT `localhost:8080/api/passport/update?id=*` обновляет паспорт по id
* GET `localhost:8080/api/passport/unavailable` возвращает паспорта с истекшим сроком годности
* GET `localhost:8080/api/passport/find-replaceable` возвращает паспорта с истекшим сроком годности
и паспорта которые надо заменить в ближайшие 3 месяца


# Docker
Запуск проекта при помощи Docker:
1. Скопируйте проект и перейдите в его корень
2. Соберите проект при помощи Gradle 
```shell script
gradle build -x test
```
3. Соберите контейнер с основной программой 
```shell script
docker build -t passport .
```
4. Запустите приложение 
```shell script
docker-compose up
```

После запуска проект будет доступен на _80_ порту.

# Kubernetes
Запуск проекта при помощи K8s:
1. Скопируйте проект (или только файлы из папки [K8s](K8s)) и перейдите в папку `./K8s`
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

1. Скопируйте проект (или только файлы из папки [K8s](K8s)) и перейдите в папку `./K8s`
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
# Примечания
Если не использовать Docker, то нужно создать базу данных. 

```shell script
create database passport
```

# Остальные сервисы проекта
* [Клиент](https://github.com/QmBo/passport-client) - сервис производящий запрос к данному сервису
* [Mail сервис](https://github.com/QmBo/passport-mail) Kafka сервис отвечающий да рассылку
