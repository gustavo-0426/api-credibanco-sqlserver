IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'Credibanco')
BEGIN
    CREATE DATABASE Credibanco;
END
GO