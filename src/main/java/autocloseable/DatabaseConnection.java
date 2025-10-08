package autocloseable;

public class DatabaseConnection implements AutoCloseable {

    private final String connectionName;

    public DatabaseConnection(String connectionName) {
        this.connectionName = connectionName;
        System.out.println("Connection opened: " + connectionName);
    }

    public void executeQuery(String query) {
        System.out.println("Executing query on " + connectionName + ": " + query);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Connection Closed: " + connectionName);
    }
}
