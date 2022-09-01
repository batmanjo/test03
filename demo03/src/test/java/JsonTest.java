import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo3.dao.User;
import org.junit.Test;

/**
 * @author yanmiao.wu
 * @create 2022-09-01 10:32
 */
public class JsonTest {
    @Test
    public void jsonTest1() {
        User user = new User();
        user.setId(1);
        user.setName("成龙");
        user.setEmail("1@qq.com");
        System.out.println(JSON.toJSONString(user));

        String userJson = " { \"id\":2, \"name\":\"李连杰\", \"email\":\"2@qq.com\"}";
        User user1 = JSON.parseObject(userJson, User.class);

        System.out.println(user1.toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","3");
        jsonObject.put("name","丁真");
        jsonObject.put("email","3@qq.com");
        User user2 = JSON.parseObject(jsonObject.toJSONString(), User.class);

        System.out.println(user2.toString());
    }

    @Test
    public void testSOUT() {
        int length = 8;
        for (int i = 0; i < length; i++) {
            for (int j = length; j > i; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < i * 2 + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
