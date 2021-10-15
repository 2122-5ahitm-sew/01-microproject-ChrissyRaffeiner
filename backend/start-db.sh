docker run --rm=true \
           --name postgres-mircoproject-skischule \
           -e POSTGRES_USER=app \
           -e POSTGRES_PASSWORD=app \
           -e POSTGRES_DB=db \
           -p 5432:5432 \
           postgres:13.1
