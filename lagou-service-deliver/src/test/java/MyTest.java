import com.xu.AutoDeliverApplication;
import com.xu.ite.ResumeFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author xushiwei
 * @Date 2022/11/14 16:47
 * @Version 1.0
 */


@SpringBootTest(classes = AutoDeliverApplication.class)
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private ResumeFeignClient resumeFeignClient;

    @Test
    public void testFeignClient(){
        Integer resumeOpenState = resumeFeignClient.findResumeOpenState(1545132l);
        System.out.println(resumeOpenState);
    }
}
