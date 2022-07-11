package swap.bot.root.tuneapi;

import swap.bot.root.tuneapi.Utils.Data;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class MetadataApi {
    ByteBuffer BYTE_FILE;
    private Short padding;
    private Integer ordinal;

    public MetadataApi(ByteBuffer bytefile){
        BYTE_FILE = bytefile;
        //padding   = bytefile.getShort();
        bytefile.position(2);
        ordinal   = bytefile.getInt();
    }

    //public short getPadding(){ return padding; }

    public String getCarName(){
        String car_name = "Ordinal: " + ordinal;
        Scanner sc = new Scanner(Data.ORDINALS);
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String[] line = sc.next().trim().split(",");
            int id = Integer.parseInt(line[1]);
            if(id == ordinal) {
                car_name = line[0];
            }
        }
        return car_name;
    }

    public int getOrdinal(){
        return ordinal;
    }
}
