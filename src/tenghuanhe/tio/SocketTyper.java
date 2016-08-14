package tenghuanhe.tio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class SocketTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java tenghuanhe.tio.SocketTyper url1 url2 ...");
            return;
        }
        for (String arg : args) {
            if (args.length > 1) {
                System.out.println(arg + ":");
            }
            try {
                URL u = new URL(arg);
                if (!u.getProtocol().equalsIgnoreCase("http")) {
                    System.err.println("Sorry, " + u.getProtocol() + " is not supported.");
                    return;
                }
                String host = u.getHost();
                int port = u.getPort();
                String file = u.getFile();
                if (file == null) file = "/";
                if (port <= 0) port = 80;
                Socket s = new Socket(host, port);
                String request = "GET / HTTP/1.1\r\n" +
                        "User-Agent: tenghuanhe.tio.SocketTyper\r\n" +
                        "Accept: text/*\r\n" +
                        "Host: " + host + "\r\n" +
                        "\r\n";
                System.out.println(request);
                byte[] b = request.getBytes();
                OutputStream out = s.getOutputStream();
                InputStream in = s.getInputStream();
                out.write(b);
                out.flush();
                StreamCopier.copy(in, System.out);
//                for (int c = in.read(); c != -1; c = in.read()) {
//                    System.out.println(c);
//                }
                in.close();
                out.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
