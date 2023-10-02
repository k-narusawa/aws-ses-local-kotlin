# aws-ses-local-kotlin

## List of APIs

* List Emails

```shell
curl --location 'http://127.0.0.1:8080/store?since=1683448121000'
```

* Search Emails

```shell
curl --location 'http://127.0.0.1:8080/emails?page=0&size=3&to=recipient%40example.com'
```

* Clear Emails
```shell
curl --location --request POST 'http://127.0.0.1:8080/clear'
```

## Sample (docker-compose)

```yaml
version: '3'
services:
  mysql:
    image: mysql:8.0
    platform: linux/x86_64
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: ses_mock
      MYSQL_USER: docker
      MYSQL_PASSWORD: docker
      TZ: 'Asia/Tokyo'
    volumes:
      - ./docker/db/data:/var/lib/mysql
      - ./docker/db/my.cnf:/etc/mysql/conf.d/my.cnf
      - ./docker/db/sql:/docker-entrypoint-initdb.d
    ports:
      - "13306:3306"
    command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
  app:
    image: 19992240/aws-ses-local
    environment:
      DATA_SOURCE_URL: jdbc:mysql://mysql:3306/ses_mock
      DATA_SOURCE_USER: docker
      DATA_SOURCE_PASSWORD: docker
    ports:
      - "8080:8080"
    depends_on:
      - mysql
```