package tenghuanhe.tio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tenghuanhe on 2016/3/12.
 */
public class NIOCopier {
    public static void main(String[] args) throws IOException {

        long current = System.currentTimeMillis();
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);
        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        while (true) {
            int bytesRead = inChannel.read(buffer);
            if (bytesRead == -1) break;

            buffer.flip();
            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();

        System.out.println(System.currentTimeMillis() - current);   // buffer 256         bytes 25793 ms
                                                                    // buffer 1024 * 1024 bytes  6040 ms
    }
}
