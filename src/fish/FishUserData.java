package root.ogbot.fish;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class FishUserData {
    public static final DecimalFormat df = new DecimalFormat( "#.##" );

        int CASH = 0;
        int COMMON_FISH = 1;
        int RARE_FISH = 2;
        int EXOTIC_FISH = 3;

        int PERK_MAX_COMMON_FISH = 4;
        int PERK_MAX_BONUS_COMMON_FISH = 5;
        int PERK_MAX_RARE_FISH = 6;
        int PERK_MAX_BONUS_RARE_FISH = 7;
        int PERK_MAX_EXOTIC_FISH = 8;
        int PERK_MAX_BONUS_EXOTIC_FISH = 9;
        int PERK_MAX_TREASURE_REWARDS = 10;

        int PERK_FASTER_FISHING = 11;
        int PERK_SELL_MULTIPLIER = 12;
        int PERK_COMMON_SELL_MULTIPLIER = 13;
        int PERK_RARE_SELL_MULTIPLIER = 14;
        int PERK_EXOTIC_SELL_MULTIPLIER = 15;

        int FISH_TIMER = 16;



    private final String[] playerData;
    private final String userFile;
    public FishUserData(String userId) {
        userFile = "data/fish/users/" + userId;
        try {
            playerData = Files.readString(Path.of(userFile)).split(",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getCash(){
        return Double.parseDouble(playerData[CASH]);
    }

    public FishUserData setCash(double cash){
        playerData[CASH] = String.valueOf(df.format(cash));
        return this;
    }

    public long getCommonFish(){
        return Long.parseLong(playerData[COMMON_FISH]);
    }

    public FishUserData setCommonFish(long fish){
        playerData[COMMON_FISH] = String.valueOf(fish);
        return this;
    }

    public long getRareFish(){
        return Long.parseLong(playerData[RARE_FISH]);
    }

    public FishUserData setRareFish(long fish){
        playerData[RARE_FISH] = String.valueOf(fish);
        return this;
    }

    public long getExoticFish(){
        return Long.parseLong(playerData[EXOTIC_FISH]);
    }

    public FishUserData setExoticFish(long fish){
        playerData[EXOTIC_FISH] = String.valueOf(fish);
        return this;
    }

    public int getSellMultiplier(){
        return Integer.parseInt(playerData[PERK_SELL_MULTIPLIER]);
    }

    public FishUserData setSellMultiplier(int level){
        playerData[PERK_SELL_MULTIPLIER] = String.valueOf(level);
        return this;
    }

    public long getFishTimer(){
        return Long.parseLong(playerData[FISH_TIMER]);
    }

    public FishUserData setFishTimer(long time){
        playerData[FISH_TIMER] = String.valueOf(time);
        return this;
    }

    public int getPerkFasterFishing(){
        return Integer.parseInt(playerData[PERK_FASTER_FISHING]);
    }

    public FishUserData setPerkFasterFishing(int level){
        playerData[PERK_FASTER_FISHING] = String.valueOf(level);
        return this;
    }

    public int getPerkMaxCommonFish(){
        return Integer.parseInt(playerData[PERK_MAX_COMMON_FISH]);
    }

    public FishUserData setPerkMaxCommonFish(int max){
        playerData[PERK_MAX_COMMON_FISH] = String.valueOf(max);
        return this;
    }

    public int getPerkMaxBonusCommonFish(){
        return Integer.parseInt(playerData[PERK_MAX_BONUS_COMMON_FISH]);
    }

    public FishUserData setPerkMaxBonusCommonFish(int max){
        playerData[PERK_MAX_BONUS_COMMON_FISH] = String.valueOf(max);
        return this;
    }

    public float getSellMultiplierCommonFish(){
        return Integer.parseInt(playerData[PERK_COMMON_SELL_MULTIPLIER]);
    }

    public FishUserData setSellMultiplierCommonFish(int level){
        playerData[PERK_COMMON_SELL_MULTIPLIER] = String.valueOf(level);
        return this;
    }

    public int getPerkMaxRareFish(){
        return Integer.parseInt(playerData[PERK_MAX_RARE_FISH]);
    }

    public FishUserData setPerkMaxRareFish(int max){
        playerData[PERK_MAX_RARE_FISH] = String.valueOf(max);
        return this;
    }

    public int getPerkMaxBonusRareFish(){
        return Integer.parseInt(playerData[PERK_MAX_BONUS_RARE_FISH]);
    }

    public FishUserData setPerkMaxBonusRareFish(int max){
        playerData[PERK_MAX_BONUS_RARE_FISH] = String.valueOf(max);
        return this;
    }

    public float getSellMultiplierRareFish(){
        return Integer.parseInt(playerData[PERK_RARE_SELL_MULTIPLIER]);
    }

    public FishUserData setSellMultiplierRareFish(int level){
        playerData[PERK_RARE_SELL_MULTIPLIER] = String.valueOf(level);
        return this;
    }
    public int getPerkMaxExoticFish(){
        return Integer.parseInt(playerData[PERK_MAX_EXOTIC_FISH]);
    }

    public FishUserData setPerkMaxExoticFish(int max){
        playerData[PERK_MAX_EXOTIC_FISH] = String.valueOf(max);
        return this;
    }

    public float getSellMultiplierExoticFish(){
        return Integer.parseInt(playerData[PERK_EXOTIC_SELL_MULTIPLIER]);
    }

    public FishUserData setSellMultiplierExoticFish(int level){
        playerData[PERK_EXOTIC_SELL_MULTIPLIER] = String.valueOf(level);
        return this;
    }
    public int getPerkMaxBonusExoticFish(){
        return Integer.parseInt(playerData[PERK_MAX_BONUS_EXOTIC_FISH]);
    }

    public FishUserData setPerkMaxBonusExoticFish(int max){
        playerData[PERK_MAX_BONUS_EXOTIC_FISH] = String.valueOf(max);
        return this;
    }

    public int getPerkMaxTreasureRewards(){
        return Integer.parseInt(playerData[PERK_MAX_TREASURE_REWARDS]);
    }

    public FishUserData setPerkMaxTreasureRewards(int max){
        playerData[PERK_MAX_TREASURE_REWARDS] = String.valueOf(max);
        return this;
    }

    public void save(){
        try {
            FileWriter myWriter = new FileWriter(userFile);
            myWriter.write(String.join(",", playerData));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

