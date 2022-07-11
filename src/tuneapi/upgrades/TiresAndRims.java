package swap.bot.root.tuneapi.Upgrades;

import swap.bot.root.tuneapi.Utils.Data;

public class TiresAndRims {
    Integer tire_compound;
    Integer tire_width_front;
    Integer tire_width_rear;
    Integer rim_size_front;
    Integer rim_size_rear;
    Integer rims;
    Integer track_width_front;
    Integer track_width_rear;


    public TiresAndRims(UpgradeApi api){
        tire_compound     = api.tire_compound;
        rims              = api.rims;
        tire_width_front  = api.tire_width_front;
        tire_width_rear   = api.tire_width_rear;
        rim_size_front    = api.rim_size_front;
        rim_size_rear     = api.rim_size_rear;
        track_width_front = api.track_width_front;
        track_width_rear  = api.tire_width_rear;
    }

    public String getRims(){
        String rimName = "Rim not found: " + rims;
        System.out.println(rims);
        for(String data: Data.RIMS_ID){
            //if(String.valueOf(rims).equals("1179042640")) rimName = "F52_MonoblockTarmac (Cast Monoblock Tarmac)";
            if(Integer.parseInt(data.split(",")[0]) == rims) rimName = data.split(",")[1] + " (" + data.split(",")[2] + ")";
        }
        return rimName;
    }
}
