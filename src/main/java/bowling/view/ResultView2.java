package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;

import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;
import static bowling.view.PrintFormat.*;

public class ResultView2 {
    private static final int FRAME_ID_FIRST = 1;
    private static final int FRAME_ID_FINAL = 10;

    public static void print(GameResults gameResults) {
        System.out.println();
        printResult(gameResults);
    }

    private static void printResult(GameResults gameResults) {
        IntStream.rangeClosed(FRAME_ID_FIRST, FRAME_ID_FINAL)
                .forEach(frameId -> printOneFrame(gameResults, frameId));
    }

    private static void printOneFrame(GameResults gameResults, int frameId){
        if(frameId == FRAME_ID_FIRST){
            for(int i=0; i<gameResults.getSize(); i++){
                System.out.println(BOWLING_FRAME);
                for (int j=0; j<gameResults.getSize(); j++){
                    if(i==j){
                        printFirstFrameFirst(gameResults.getResultByIndex(j));
                    }
                    if(i<j){
                        printName(gameResults.getResultByIndex(j));
                    }

                    if(i>j){
                        printFirstFrameFirst(gameResults.getResultByIndex(j));
                    }
                }
            }
        } else {
            for (int i=0; i<gameResults.getSize(); i++){
                System.out.println(BOWLING_FRAME);
                for(int j=0; j<gameResults.getSize(); j++){
                    if(i==j){
                        printOneFrameFirst(gameResults.getResultByIndex(j), frameId);
                    }

                    if(i<j){
                        printOneFrameFirst(gameResults.getResultByIndex(j), frameId - 1);
                    }

                    if(i>j){
                        printOneFrameFirst(gameResults.getResultByIndex(j), frameId);
                    }
                }
            }
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

    private static void printName(GameResult gameResult){
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFirstFrameFirst(GameResult gameResult){
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));
        name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(FRAME_ID_FIRST).getFirstPoint()));

        System.out.println(name.toString());
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