package Log;

import com.qxc.LoggingAndAuthorStarter;
import com.qxc.interfaces.Log;
import com.qxc.interfaces.RequireInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 0:48
 * @Version 1.0
 * @PACKAGE Log
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LoggingAndAuthorStarter.class)
public class logTest {

    @Log(name = "test1",info = "end",requireTime = true)
    public void test(){
        System.out.println("qwe");
    }

    @Test
    public void t() {
        test();
    }
}