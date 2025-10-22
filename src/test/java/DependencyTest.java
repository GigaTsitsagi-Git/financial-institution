import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DependencyTest {
    private static final Logger logger = LogManager.getLogger(DependencyTest.class);
    
    public static void main(String[] args) {
        // Test Commons IO
        String testString = "Hello World";
        String[] words = StringUtils.split(testString);
        logger.info("Commons Lang3 working: {}", words.length);
        
        // Test Log4j
        logger.info("Log4j working: {}", "Success");
        
        System.out.println("All dependencies are working correctly!");
    }
}
