import eu.bitwalker.useragentutils.UserAgent;
import org.junit.Test;

/**
 * @FileName:Browser.class
 * @Author:ly
 * @Date:2022/5/31
 * @Description:
 */
public class Browser {

    @Test
    public void testBrowser() {
        String s = "\"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53\"";
        String s1 = "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36";
        //UserAgent userAgent = new UserAgent("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53");
        UserAgent userAgent = new UserAgent(s1);
        System.out.println(userAgent.getOperatingSystem());
        System.out.println(userAgent.getBrowser());
        System.out.println(userAgent.getBrowserVersion());
    }
}
