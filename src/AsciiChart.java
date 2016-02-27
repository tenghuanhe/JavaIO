/**
 * Created by tenghuanhe on 2016/1/8.
 */
public class AsciiChart {
    public static void main(String[] args) {
        for (int i = 0; i < 1233; i++) {
            System.out.write(i);
            if (i % 8 == 255)
                System.out.write('\n');
            else System.out.write('\t');
        }
        System.out.write('\n');
    }
}
