package com.solvd.financialinstituion.autocloseable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnection implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);

    private final String connectionName;

    public DatabaseConnection(String connectionName) {
        this.connectionName = connectionName;
        logger.info("Connection opened: {}", connectionName);
    }

    public void executeQuery(String query) {
        logger.info("Executing query on {}: {}", connectionName, query);
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection Closed: {}", connectionName);
    }
}
