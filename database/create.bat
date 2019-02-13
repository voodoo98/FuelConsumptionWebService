psql -d postgres -h localhost -p 5432 -U postgres -f create-database.sql
psql -d fueldb -h localhost -p 5432 -U postgres -f create-schema.sql
psql -d fueldb -h localhost -p 5432 -U postgres -f create-role.sql
psql -d fueldb -h localhost -p 5432 -U postgres -f grant-role.sql
psql -d fueldb -h localhost -p 5432 -U postgres -f grant-access.sql
psql -d fueldb -h localhost -p 5432 -U postgres -f sample-data.sql
