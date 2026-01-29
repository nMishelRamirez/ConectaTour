Ejecutar las migraciones:

dotnet ef database update

Base de datos:
docker run --name postgres-turistas -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=api_turistas -p 5433:5432 -d postgres