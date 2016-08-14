package tenghuanhe.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tenghuanhe on 2016/8/14.
 */
public class FileCopier {
    public static void main(String[] args) throws IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        FileOutputStream outFile = new FileOutputStream(args[0]);

        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = outFile.getChannel();

        for (ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024); inChannel.read(buffer) != -1; buffer.clear()) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
        }

        inChannel.close();
        outChannel.close();
    }
}
