import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author houzhiwei
 * @date 2022/1/25 23:03
 */
@Slf4j
public class HutoolTest {
    @Test
    public void test1(){
        String admin = SecureUtil.md5("admin");
        System.out.println(admin);
    }
}
