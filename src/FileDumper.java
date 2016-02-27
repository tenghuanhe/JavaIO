import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by tenghuanhe on 2016/2/28.
 */
public class FileDumper {

    public static final int ASC = 0;
    public static final int DEC = 1;
    public static final int HEX = 2;

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("Usage: java FileDumper [-adh] file1 file2 ...");
            return;
        }

        int firstArg = 0;
        int mode = ASC;
        if (args[0].startsWith("-")) {
            firstArg = 1;
            if (args[0].equals("-h"))
                mode = HEX;
            else if (args[0].equals("-d"))
                mode = DEC;
        }
        for (int i = firstArg; i < args.length; i++) {
            if (mode == ASC) dumpAscii(args[i]);
            else if (mode == HEX) dumpHex(args[i]);
            else if (mode == DEC) dumpDecimal(args[i]);
            if (i < args.length - 1) {
                System.out.println("\r\n--------------------\r\n");
            }
        }
    }

    public static void dumpAscii(String filename) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(filename);
            StreamCopier.copy(fin, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
            }
        }
    }

    public static void dumpDecimal(String filename) {
        FileInputStream fin = null;
        byte[] buffer = new byte[16];
        boolean end = false;
        int bytesRead;
        try {
            fin = new FileInputStream(filename);
            while (!end) {
                bytesRead = 0;
                while (bytesRead < buffer.length) {
                    int r = fin.read(buffer, bytesRead, buffer.length - bytesRead);
                    if (r == -1) {
                        end = true;
                        break;
                    }
                    bytesRead += r;
                }
                for (int i = 0; i < bytesRead; i++) {
                    int dec = buffer[i];
                    if (dec < 0) dec += 256;
                    if (dec < 10) System.out.print("00" + dec + " ");
                    else if (dec < 100) System.out.print("0" + dec + " ");
                    else System.out.print(dec + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {

            }
        }
    }

    public static void dumpHex(String filename) {
        FileInputStream fin = null;
        byte[] buffer = new byte[24];
        boolean end = false;
        int bytesRead;
        try {
            fin = new FileInputStream(filename);
            while (!end) {
                bytesRead = 0;
                while (bytesRead < buffer.length) {
                    int r = fin.read(buffer, bytesRead, buffer.length - bytesRead);
                    if (r == -1) {
                        end = true;
                        break;
                    }
                    bytesRead += r;
                }
                for (int i = 0; i < bytesRead; i++) {
                    int hex = buffer[i];
                    if (hex < 0) hex += 256;
                    if (hex > 16)
                        System.out.print(Integer.toHexString(hex) + " ");
                    else
                        System.out.print("0" + Integer.toHexString(hex) + " ");

                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
            }
        }
    }
}
