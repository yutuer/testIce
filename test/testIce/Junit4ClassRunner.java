package testIce;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * Created by ZhangXiong on 2017/9/10.
 */
public class Junit4ClassRunner extends SpringJUnit4ClassRunner {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Junit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

}
