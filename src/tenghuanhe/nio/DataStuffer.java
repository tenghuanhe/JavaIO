package tenghuanhe.nio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tenghuanhe on 2016/8/14.
 */
public class DataStuffer {
    private static byte[] data = new byte[256];

    public static void main(String[] args) throws IOException {
        int port = 9000;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }

        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            Thread stuffer = new StufferThread(socket);
            stuffer.start();
        }
    }

    public static class StufferThread extends Thread {
        private Socket socket;

        public StufferThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                OutputStream out = new BufferedOutputStream(socket.getOutputStream());
                while (socket.isClosed()) {
                    out.write(data);
                }
            } catch (IOException ioe) {

            }
        }
    }
}
