package swap.bot.root.tuneapi.Upgrades;

public class Appearance {
    Integer bumper_front;
    Integer bumper_rear;
    Integer hood;
    Integer sideskirt;
    Integer wing_rear;
    Integer car_body;

    public Appearance(UpgradeApi api){
        bumper_front = api.bumper_front;
        bumper_rear  = api.bumper_rear;
        hood = api.hood;
        sideskirt = api.side_skirts;
        wing_rear = api.wing_rear;
        car_body = api.carbody;
    }

    public String getBumperFront(){
        String value = String.valueOf(bumper_front);
        return value.substring(value.length() - 1);
    }

    public String getBumperRear(){
        String value = String.valueOf(bumper_rear);
        return value.substring(value.length() - 1);
    }

    public String getHood(){
        String value = String.valueOf(hood);
        return value.substring(value.length() - 1);
    }

    public String getSideSkirts(){
        String value = String.valueOf(bumper_rear);
        return value.substring(value.length() - 1);
    }

    public String getWingRear(){
        String value = String.valueOf(wing_rear);
        return value.substring(value.length() - 1);
    }

    public String getCarBody(){
        String value = String.valueOf(car_body);
        return value.substring(value.length() - 1);
    }
}
