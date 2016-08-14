package tenghuanhe.tio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class FileCopier {
    public static void main(String[] args) {

        long current = System.currentTimeMillis();
        if (args.length != 2) {
            System.err.println("Usage: java tenghuanhe.tio.FileCopier infile outfile");
        }
        try {
            copy(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - current);   // 256         bytes 54953ms
                                                                    // 1024 * 1024 bytes
    }

    public static void copy(String inFile, String outFile) throws IOException {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(inFile);
            fout = new FileOutputStream(outFile);
            StreamCopier.copy(fin, fout);
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException e) {

            }
            try {
                if (fout != null) fout.close();

            } catch (IOException e) {

            }
        }
    }
}
