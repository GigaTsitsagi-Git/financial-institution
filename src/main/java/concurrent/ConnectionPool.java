package concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private final BlockingQueue<AccountDao> availableConnections;
    private final int maxConnections;
    private final AtomicInteger connectionCounter = new AtomicInteger(1);
    private final AtomicInteger totalCreated = new AtomicInteger(0);

    private ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
        this.availableConnections = new LinkedBlockingQueue<>(maxConnections);
        logger.info("Pool created with {} connections", maxConnections);
    }

    public static ConnectionPool getInstance(int maxConnections) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(maxConnections);
                }
            }
        }
        return instance;
    }

    public AccountDao getConnection() throws InterruptedException {
        AccountDao connection = availableConnections.poll();
        
        if (connection != null) {
            logger.info("Got existing connection {}", connection.getConnectionId());
            return connection;
        }
        
        if (totalCreated.get() < maxConnections) {
            String connectionId = "CONN-" + connectionCounter.getAndIncrement();
            connection = new AccountDao(connectionId);
            totalCreated.incrementAndGet();
            logger.info("Created new connection {}", connectionId);
            return connection;
        }
        
        logger.info("Pool full, waiting for connection");
        return availableConnections.take();
    }

    public boolean releaseConnection(AccountDao connection) {
        if (connection == null) return false;
        
        boolean released = availableConnections.offer(connection);
        if (released) {
            logger.info("Released connection {}", connection.getConnectionId());
        }
        return released;
    }
}
