package ua.nure.kn.stoianov.usermanagement1.domain.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseException;
}
