package swap.bot.root.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import swap.bot.root.tuneapi.Utils.Data;
import java.awt.*;


public class RimsCommand {

    public static EmbedBuilder getFirstPage() {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Manufacturer: #-F")
                .setColor(Color.cyan);

        for (String rim : Data.RIMS_5ZI) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_ADV) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_AME) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_ASA) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_BBS) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_BOR) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_BOY) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_BRA) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_BUD) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_COM) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_CRA) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_DUB) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_DYM) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_ENK) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_F52) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_FIK) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        return embed;
    }

    public static EmbedBuilder getSecondPage(){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Manufacturer: G-K")
                .setColor(Color.cyan);

        for(String rim : Data.RIMS_GRA){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_HAL){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_HOL){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_HOL){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_HRE){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_IFO){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_KMC){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_KON){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for(String rim : Data.RIMS_KOS){
            String[] rimSplit = rim.replace("(", "").replace(")","").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }
        return embed;
    }

    public static EmbedBuilder getThirdPage() {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Manufacturer: L-P")
                .setColor(Color.cyan);

        for (String rim : Data.RIMS_LEX) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_MIC) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_MOD) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_MOM) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_MOT) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_OET) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_OZ) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        return embed;
    }

    public static EmbedBuilder getFourthPage() {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Manufacturer: Q-U")
                .setColor(Color.cyan);

        for (String rim : Data.RIMS_RAC) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_ROT) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_SPE) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_TEA) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_TEN) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_TIT) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_TSW) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }
        return embed;
    }

    public static EmbedBuilder getFifthPage(){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Manufacturer: L-P")
                .setColor(Color.cyan);

        for (String rim : Data.RIMS_LEX) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_VOL) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_WED) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_WEL) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        embed.addBlankField(false);

        for (String rim : Data.RIMS_WOR) {
            String[] rimSplit = rim.replace("(", "").replace(")", "").split("\\s+", 2);
            embed.addField(rimSplit[1], rimSplit[0], false);
        }

        return embed;
    }
}
