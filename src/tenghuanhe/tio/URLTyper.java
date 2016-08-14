package tenghuanhe.tio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class URLTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java tenghuanhe.tio.URLTyper url1 url2 ...");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            if (args.length > 1) {
                System.out.println("args[i]" + ":");
            }
            try {
                URL u = new URL(args[i]);
                InputStream in = u.openStream();
                StreamCopier.copy(in, System.out);
                in.close();
            } catch (MalformedInputException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
