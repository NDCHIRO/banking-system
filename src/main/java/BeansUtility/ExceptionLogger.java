package BeansUtility;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ExceptionLogger {
    private static final Logger logger = Logger.getLogger(ExceptionLogger.class.getName());


    public static void logException(Exception e) {
        //logger.info(e.getMessage());
        //logger.log(Level.SEVERE, "An exception occurred", e);
        e.printStackTrace();
    }
    
}
