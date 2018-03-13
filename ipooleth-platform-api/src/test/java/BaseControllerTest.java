import com.ipooleth.IpoolethPlatformApplication;
import com.ipooleth.platform.services.EthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpoolethPlatformApplication.class)
@AutoConfigureMockMvc
public class BaseControllerTest {
	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("UTF-8"));


//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ApplicationContext applicationContext;

    //@MockBean
    @Autowired
    private EthService ethService;

    @Test
    public void historyStatsTest() throws Exception {

        this.ethService.historyStats("test");


//        given(this.ethService.historyStats("test"))
//                .willReturn("aaaaa");
//        this.mvc.perform(get("/api/historyStats").accept(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
//

    }

}
