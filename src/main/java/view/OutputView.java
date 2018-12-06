package view;

import domain.Frames;
import domain.Player;
import utils.StringUtils;

public class OutputView {

    public static void printFrameBoard(int frameOfCount){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(StringUtils.VERTICAL_BAR+"%6s"+StringUtils.VERTICAL_BAR, "NAME"));
        for(int i =1; i <= frameOfCount; i++){
            sb.append(String.format("%6s"+StringUtils.VERTICAL_BAR,StringUtils.makeDoubleDigit(i)));
        }
        System.out.println(sb.toString());
    }

    public static void playerOfGameBoard(Player player, Frames frames){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(StringUtils.VERTICAL_BAR+"%6s"+StringUtils.VERTICAL_BAR, player.getName()));
        sb.append(frames.toString());

        System.out.println(sb.toString());
    }
}
