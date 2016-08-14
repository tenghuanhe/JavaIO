package tenghuanhe.tio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class WebCat {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java tenghuanhe.tio.WebCat url1 url2 ...");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            if (i > 0 && i < args.length) {
                System.out.println();
                System.out.println("-------------------------");
                System.out.println();
            }
            System.out.println(args[i] + ":");
            try {
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                uc.connect();
                InputStream in = uc.getInputStream();
                StreamCopier.copy(in, System.out);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
