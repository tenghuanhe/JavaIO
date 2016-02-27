import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class SocketTyper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java SocketTyper url1 url2 ...");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (args.length > 1) {
                System.out.println(args[i] + ":");
            }
            try {
                URL u = new URL(args[i]);
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
                String request = "GET " + file + " HTTP/1.1\r\n" +
                        "User-Agent: SocketTyper\r\n" +
                        "Accept: text/*\r\n\r\n" +
                        "Host: " + host + "\r\n" +
                        "\r\n";
                byte[] b = request.getBytes();
                OutputStream out = s.getOutputStream();
                InputStream in = s.getInputStream();
                out.write(b);
                out.flush();
                StreamCopier.copy(in, System.out);
                in.close();
                out.close();
                s.close();
            } catch (MalformedInputException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
