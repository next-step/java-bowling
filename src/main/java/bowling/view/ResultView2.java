package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;

import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;
import static bowling.view.PrintFormat.*;

public class ResultView2 {
    private static final String BLANK_ONE = " ";
    private static final String BLANK_TWO = "  ";
    private static final String BLANK_THREE = "   ";
    private static final String BLANK_FOUR = "    ";
    private static final String BLANK_FIVE = "     ";
    private static final String BLANK_SIX = "      ";
    private static final String BLOCK_BORDER = "|";
    private static final String LABEL_NAME = "NAME";
    private static final String SYMBOL_STRIKE = "X";
    private static final String SYMBOL_GUTTER = "-";
    private static final String DELIMITER_SPARE = "/";
    private static final int FRAME_ID_FIRST = 1;
    private static final int FRAME_ID_FINAL = 10;
    private static final int MIN_NUMBER_FOR_THREE_DIGITS = 100;
    private static final int MIN_NUMBER_FOR_TWO_DIGITS = 10;
    private static final int OFFSET = -1;
    private static final int OFFSET_DOUBLE = -2;

    public static void print(GameResults gameResults) {
        System.out.println();
        printResult(gameResults);
    }

    private static void printResult(GameResults gameResults) {
        IntStream.rangeClosed(FRAME_ID_FIRST, FRAME_ID_FINAL)
                .forEach(frameId -> printOneFrame(gameResults, frameId));
    }

    private static void printOneFrame(GameResults gameResults, int frameId){
        for (int i=0; i<gameResults.getSize(); i++){
            System.out.println(BOWLING_FRAME);
            printOneFrameFirst(gameResults.getResultByIndex(i), frameId);
        }

        System.out.println();
        System.out.println();

        for (int i=0; i<gameResults.getSize(); i++){
            System.out.println(BOWLING_FRAME);
            printOneFrameSecond(gameResults.getResultByIndex(i), frameId);
        }

        System.out.println();
        System.out.println();
    }


    private static void printOneFrameFirst(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for(int i=FRAME_ID_FIRST; i<frameId; i++){
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }

        name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(frameId).getFirstPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printOneFrameSecond(GameResult gameResult, int frameId){
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for(int i=FRAME_ID_FIRST; i<frameId; i++){
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(frameId))));
        System.out.println(name.toString());
        System.out.println();
    }

    private static String getScore(Frame frame){
        if(frame.isResult(STRIKE)){
            return " X ";
        }

        if(frame.isResult(SPARE)){
            return String.valueOf(frame.getFirstPoint())
                    +"|/";
        }

        if(frame.isResult(MISS)){
            return String.valueOf(frame.getFirstPoint())
                    +"|"
                    + String.valueOf(frame.getSecondPoint());
        }

        return String.valueOf(" - ");
    }
}