package tenghuanhe.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tenghuanhe on 2016/8/14.
 */
public class NIODuplicator {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[1]);
        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        int bytesRead = 0;

        while (bytesRead >= 0 || byteBuffer.hasRemaining()) {
            if (bytesRead != -1) {
                bytesRead = inChannel.read(byteBuffer);
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.compact();
            }
        }

        inChannel.close();
        outChannel.close();
    }
}
