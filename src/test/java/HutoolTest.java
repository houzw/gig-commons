import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author houzhiwei
 * @date 2022/1/25 23:03
 */
@Slf4j
public class HutoolTest {
    @Test
    public void test1() {
        String admin = SecureUtil.md5("admin");
        System.out.println(admin);
    }

    //https://www.hutool.cn/docs/#/http/Http%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%B7%A5%E5%85%B7%E7%B1%BB-HttpUtil
    @Test
    public void HttpTest() {
        String url = "";
        Map<String, Object> paramMap = new HashMap<>();
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);
    }
}
