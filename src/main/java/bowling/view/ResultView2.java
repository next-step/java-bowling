package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
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

    private static void printOneFrame(GameResults gameResults, int frameId) {
        if (frameId == FRAME_ID_FIRST) {
            printFirstFrame(gameResults, frameId);
        }

        if (frameId != FRAME_ID_FIRST) {
            printNormalFrame(gameResults, frameId);
        }
    }

    private static void printFirstFrame(GameResults gameResults, int frameId) {
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printFirstFrameFirst(gameResults.getResultByIndex(j));
                }
                if (i < j) {
                    printName(gameResults.getResultByIndex(j));
                }
            }
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printFirstFrameSecond(gameResults.getResultByIndex(j));
                }
                if (i < j) {
                    printFirstFrameFirst(gameResults.getResultByIndex(j));
                }
            }
        }

        System.out.println();
        System.out.println();
    }

    private static void printNormalFrame(GameResults gameResults, int frameId) {
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i == j) {
                    printOneFrameFirst(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printOneFrameSecond(gameResults.getResultByIndex(j), frameId - 1);
                }

                if (i > j) {
                    printOneFrameFirst(gameResults.getResultByIndex(j), frameId);
                }
            }
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printOneFrameSecond(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printOneFrameFirst(gameResults.getResultByIndex(j), frameId);
                }
            }
        }

        System.out.println();
        System.out.println();
    }


    private static void printName(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFirstFrameSecond(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));
        name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(FRAME_ID_FIRST))));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFirstFrameFirst(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));
        name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(FRAME_ID_FIRST).getFirstPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printOneFrameFirst(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < frameId; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }

        name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(frameId).getFirstPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printOneFrameSecond(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < frameId; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(frameId))));
        System.out.println(name.toString());
        System.out.println();
    }

    private static void printScoreUntil(GameResult gameResult, int frameId){
        StringBuilder name = new StringBuilder();
        name.append(NAME_BLANK);

        for (int i = FRAME_ID_FIRST; i < frameId; i++) {
            name.append(String.format(SCORE, getScoreUntil(gameResult, frameId)));
        }
        name.append(String.format(SCORE, getScoreUntil(gameResult, frameId)));
        System.out.println(name.toString());
        System.out.println();
    }

    private static int getScoreUntil(GameResult gameResult, int frameId){
        if(frameId == 1){
            return gameResult.getFrameByFrameId(frameId).getPointSumOnlyThisFrame();
        }

        if(gameResult.getFrameByFrameId(frameId).isResult(STRIKE)
        || gameResult.getFrameByFrameId(frameId).isResult(SPARE)){
            return gameResult.getScoreUntilFrame(frameId - 1);
        }

        return gameResult.getScoreUntilFrame(frameId);
    }

    private static String getScore(Frame frame) {
        if (frame.isResult(STRIKE)) {
            return " X ";
        }

        if (frame.isResult(SPARE)) {
            return String.valueOf(frame.getFirstPoint())
                    + "|/";
        }

        if (frame.isResult(MISS)) {
            return String.valueOf(frame.getFirstPoint())
                    + "|"
                    + String.valueOf(frame.getSecondPoint());
        }

        return String.valueOf(" - ");
    }
}