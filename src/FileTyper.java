import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class FileTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: javaFileTyper file1 file2 ...");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            try {
                typeFile(args[i]);
                if (i + 1 < args.length) {
                    System.out.println();
                    System.out.println("-------------------------");
                }
            } catch (IOException e) {
                System.err.println(e);
            }

        }
    }

    public static void typeFile(String filename) throws IOException {
        FileInputStream fin = new FileInputStream(filename);
        StreamCopier.copy(fin, System.out);
        fin.close();
    }
}
