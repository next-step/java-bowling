package bowling.view;

import bowling.domain.FrameHistory;
import bowling.domain.PitchHistory;
import bowling.domain.PitchResult;
import bowling.domain.Player;

public class ResultView {
    private static String INIT_SCORE_BOARD_STRING = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String SEPARATE = "|";
    private static String NAME_STRING = "|  %s |";
    private static String STRIKE_STRING = " X   ";
    private static String SPARE_STRING = "/ ";
    private static String MISS_STRING = " ";
    private static String GUTTER_STRING = "- ";
    private static String NONE_STRING = " ";
    private static String VOID_STRING = "";


    public static void printScoreBoard(FrameHistory frameHistory, Player player){
        System.out.println(INIT_SCORE_BOARD_STRING);
        System.out.printf(NAME_STRING, player.getName());
        while(frameHistory != null){
            printScore(frameHistory);
            frameHistory = frameHistory.getNextFrameHistory();
        }
        System.out.println();
    }

    private static void printScore(FrameHistory frameHistory){
        System.out.print(NONE_STRING);
        for(PitchHistory pitchHistory : frameHistory.getPitchHistories()){
            System.out.print(makeResultString(pitchHistory) + SEPARATE);
        }
    }

    private static String makeResultString(PitchHistory pitchHistory){
        if (PitchResult.STRIKE == pitchHistory.getResult()) {
            return STRIKE_STRING;
        }
        if (PitchResult.SPARE == pitchHistory.getResult()) {
            return SPARE_STRING;
        }
        if (PitchResult.MISS == pitchHistory.getResult()) {
            return pitchHistory.getDownPin() + MISS_STRING;
        }
        if (PitchResult.GUTTER == pitchHistory.getResult()) {
            return GUTTER_STRING;
        }
        if (PitchResult.NONE == pitchHistory.getResult()) {
            return NONE_STRING + pitchHistory.getDownPin();
        }
        return VOID_STRING;
    }

}
