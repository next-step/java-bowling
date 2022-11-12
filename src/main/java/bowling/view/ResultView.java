package bowling.view;

import bowling.domain.NormalFrame;
import bowling.domain.PlayerName;
import bowling.type.BowlingScore;

public class ResultView {

    private static final String FRAME_NUMBER_PANEL = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_PANEL = "      |";
    public static final String NAME_FORMAT = "|  %s |";

    public void printDefaultPanels(PlayerName playerName) {
        System.out.println(FRAME_NUMBER_PANEL);
        System.out.printf(NAME_FORMAT, playerName);
        for (int i = 0; i < 10; i++) {
            System.out.print(EMPTY_PANEL);
        }
        System.out.println();
    }

    public void printGameScores(PlayerName playerName, NormalFrame normalFrame){
        System.out.println(FRAME_NUMBER_PANEL);
        System.out.printf(NAME_FORMAT, playerName);
        System.out.printf("  %-3s |", getMarker(normalFrame));
        for (int i = 0; i < 9; i++) {
            System.out.print(EMPTY_PANEL);
        }
        System.out.println();
    }

    private String getMarker(NormalFrame normalFrame){
        if(normalFrame.isProgress()) {
            return String.valueOf(normalFrame.getFirstScore());
        }
        if(!normalFrame.isProgress() && !normalFrame.isStrike()) {
            StringBuilder result = new StringBuilder();
            result.append(normalFrame.getFirstScore());
            result.append("|");
            if(normalFrame.getBowlingScore() == BowlingScore.SPARE) {
                result.append("/");
            }
            if(normalFrame.getBowlingScore() == BowlingScore.GUTTER) {
                result.append("-");
            }
            if(normalFrame.getBowlingScore() == BowlingScore.MISS) {
                result.append(normalFrame.getSecondScore());
            }
            return result.toString();
        }
        return "X";
    }
}
