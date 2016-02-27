import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tenghuanhe on 2016/2/27.
 */
public class StreamCopier {
    public static void main(String[] args) {
        try {
            copy(System.in, System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        synchronized (in) {
            synchronized (out) {
                byte[] buffer = new byte[256];
                while (true) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1) break;
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
