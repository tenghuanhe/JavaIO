package tenghuanhe.tio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tenghuanhe on 2016/8/14.
 */
public class HelloServer {
    public static void main(String[] args) throws IOException {
        int port = 2345;
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            try {
                Socket s = ss.accept();
                InputStream in = s.getInputStream();

                int bufferlen = 400;
                byte[] buffer = new byte[bufferlen];
                for (int i = 0; i < bufferlen; i++) {
                    buffer[i] = (byte) in.read();
                }

                System.out.write(buffer);

                String response = "Hello " + s.getInetAddress() + " on port " + s.getPort() + "\r\n";
                response += "This is " + s.getLocalAddress() + " on port " + s.getLocalPort() + "\r\n";
                OutputStream out = s.getOutputStream();
                out.write(response.getBytes("UTF-8"));
                out.flush();
                s.close();
            } catch (Exception e) {

            }

        }
    }
}
