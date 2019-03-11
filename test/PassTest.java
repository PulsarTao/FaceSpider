import java.util.HashMap;
import java.util.Map;

public class PassTest {
    static public char upper_encode(char ch, int num) {
        ch = (char) ((int) ch + num);
        if ((int) ch > (int) 'Z') {
            return upper_encode((char) ((int) ch - (int) 'Z' - 1), 0);
        }
        if ((int) ch < (int) 'A') {
            return upper_encode((char) ((int) ch + 26), 0);
        }
        return ch;
    }

    public static void main(String[] args) {
        String code = "ABCD";
        String passwd = "";
        int nums[] = {2, 5, 11,0};
        for (int i = 0; i < code.toCharArray().length; i += 1) {
            passwd += String.valueOf(upper_encode(code.toCharArray()[i], nums[i]));
        }
        System.out.print("ABCD--->");
        System.out.println(passwd);
    }
}
