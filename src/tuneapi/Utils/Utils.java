package swap.bot.root.tuneapi.Utils;

public class Utils {
    public static byte[] getBytes(int value){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value & 0xff);
        bytes[1] = (byte)((value >> 8) & 0xff);
        bytes[2] = (byte) ((value >> 16) & 0xff);
        bytes[3] = (byte) ((value >> 24) & 0xff);
        return bytes;
    }
}
