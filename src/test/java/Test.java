import lombok.Data;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:00
 * @description：
 * @modified By：
 * @version: $
 */

@Data
public class Test {

    private int code;

    Test(int c) {this.code = c;}

    public static void main(String[] args) {
        Test test = new Test(1);
        System.out.println(test.getCode());
    }

}
