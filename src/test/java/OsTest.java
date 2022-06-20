import cn.gig.rs.commons.utils.OSInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author houzhiwei
 * @date 2022/1/25 14:16
 */
@Slf4j
public class OsTest {
    @Test
    public void test() {
        System.out.println(OSInfo.getOSname());
    }
}
