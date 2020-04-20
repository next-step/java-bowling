package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;

import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;
import static bowling.domain.point.Ordinal.FOURTH;
import static bowling.domain.point.Ordinal.THIRD;
import static bowling.view.PrintFormat.*;

public class ResultView {
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

        if (frameId == 2) {
            printSecondFrame(gameResults, frameId);
        }

        if (frameId != FRAME_ID_FIRST && frameId != 2 && frameId != FRAME_ID_FINAL) {
            printNormalFrame(gameResults, frameId);
        }

        if (frameId == FRAME_ID_FINAL) {
            printFinalFrame(gameResults);
        }
    }

    private static void printFirstFrame(GameResults gameResults, int frameId) {
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(frameId).getFirstPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printFirstFrameFirst(gameResults.getResultByIndex(j));
                }
                if (i < j) {
                    printName(gameResults.getResultByIndex(j));
                }
                printScoreBlank();
            }
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(frameId).getSecondPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printFirstFrameSecond(gameResults.getResultByIndex(j));
                    printFirstFrameScoreAtSecond(gameResults.getResultByIndex(j));
                }
                if (i < j) {
                    printFirstFrameFirst(gameResults.getResultByIndex(j));
                    printScoreBlank();
                }
            }
        }

        System.out.println();
        System.out.println();
    }

    private static void printSecondFrame(GameResults gameResults, int frameId) {
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(2).getFirstPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printNormalFrameFirst(gameResults.getResultByIndex(j), frameId);
                    printSecondFrameScoreAtFirst(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printFirstFrameSecond(gameResults.getResultByIndex(j));
                    printFirstFrameScoreAtSecond(gameResults.getResultByIndex(j));
                }
            }
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(2).getSecondPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printNormalFrameSecond(gameResults.getResultByIndex(j), frameId);
                    printSecondFrameScoreAtSecond(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printNormalFrameFirst(gameResults.getResultByIndex(j), 2);
                    printSecondFrameScoreAtFirst(gameResults.getResultByIndex(j), frameId);
                }
            }
        }

        System.out.println();
        System.out.println();
    }

    private static void printNormalFrame(GameResults gameResults, int frameId) {
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(frameId).getFirstPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printNormalFrameFirst(gameResults.getResultByIndex(j), frameId);
                    printScoreAtFirst(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printNormalFrameSecond(gameResults.getResultByIndex(j), frameId - 1);
                    if (frameId == 3) {
                        printSecondFrameScoreAtSecond(gameResults.getResultByIndex(j), 2);
                    } else {
                        printScoreAtSecond(gameResults.getResultByIndex(j), frameId - 1);
                    }
                }
            }
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(frameId).getSecondPoint());
            System.out.println(BOWLING_FRAME);
            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printNormalFrameSecond(gameResults.getResultByIndex(j), frameId);
                    printScoreAtSecond(gameResults.getResultByIndex(j), frameId);
                }

                if (i < j) {
                    printNormalFrameFirst(gameResults.getResultByIndex(j), frameId);
                    printScoreAtFirst(gameResults.getResultByIndex(j), frameId);
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

        if (gameResult.getFrameByFrameId(FRAME_ID_FIRST).getFirstPoint() == 10) {
            name.append(String.format(FIRST_SCORE, "X"));
        }

        if (gameResult.getFrameByFrameId(FRAME_ID_FIRST).getFirstPoint() != 10) {
            name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(FRAME_ID_FIRST).getFirstPoint()));
        }

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printNormalFrameFirst(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < frameId; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }

        if (gameResult.getFrameByFrameId(frameId).getFirstPoint() == 10) {
            name.append(String.format(FIRST_SCORE, "X"));
        }

        if (gameResult.getFrameByFrameId(frameId).getFirstPoint() != 10) {
            name.append(String.format(FIRST_SCORE, gameResult.getFrameByFrameId(frameId).getFirstPoint()));
        }

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printNormalFrameSecond(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < frameId; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(frameId))));
        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFinalFrame(GameResults gameResults) {
        //first
        for (int i = 0; i < gameResults.getSize(); i++) {
            System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
            System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).getFirstPoint());
            System.out.println(BOWLING_FRAME);

            for (int j = 0; j < gameResults.getSize(); j++) {
                if (i >= j) {
                    printNormalFrameFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                    printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                }

                if (i < j) {
                    printNormalFrameSecond(gameResults.getResultByIndex(j), 9);
                    printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL - 1);
                }

            }
        }

        System.out.println();
        System.out.println();

        //second (첫 투구가 Strike가 아닐 때만 출력)
        for (int i = 0; i < gameResults.getSize(); i++) {
            if (!gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
                System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).getSecondPoint());
                System.out.println(BOWLING_FRAME);

                for (int j = 0; j < gameResults.getSize(); j++) {
                    if (i >= j) {
                        printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                        printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                    }

                    if (i < j) {
                        printNormalFrameFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                        printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                    }
                }
            }
        }

        System.out.println();
        System.out.println();

        //third
        for (int i = 0; i < gameResults.getSize(); i++) {
            if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).containsOrdinal(THIRD)) {
                if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                    System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
                    System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).getThirdPoint());
                    System.out.println(BOWLING_FRAME);

                    for (int j = 0; j < gameResults.getSize(); j++) {
                        if (i >= j) {
                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printFinalFrameWhenStrikeThird(gameResults.getResultByIndex(j));
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                                printFinalFrameWhenSpareThird(gameResults.getResultByIndex(j));
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                                printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }
                        }

                        if (i < j) {
                            printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }
                        }
                    }
                }

                if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                    System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
                    System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).getThirdPoint());
                    System.out.println(BOWLING_FRAME);

                    for (int j = 0; j < gameResults.getSize(); j++) {
                        if (i >= j) {
                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                                printFinalFrameWhenSpareThird(gameResults.getResultByIndex(j));
                                printScoreUntil(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                                printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                                printScoreUntil(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printFinalFrameWhenStrikeThird(gameResults.getResultByIndex(j));
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }
                        }

                        if (i < j) {
                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printFinalFrameWhenStrikeThird(gameResults.getResultByIndex(j));
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (!gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }
                        }
                    }
                }

                if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                    System.out.println(gameResults.getResultByIndex(i).getName() + "'s turn : -");
                    System.out.println(BOWLING_FRAME);

                    for (int j = 0; j < gameResults.getSize(); j++) {
                        printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                        printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                    }
                }
            }
        }

        //fourth
        for (int i = 0; i < gameResults.getSize(); i++) {
            if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).containsOrdinal(FOURTH)) {
                if (gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                    System.out.print(gameResults.getResultByIndex(i).getName() + "'s turn : ");
                    System.out.println(gameResults.getResultByIndex(i).getFrameByFrameId(FRAME_ID_FINAL).getFourthPoint());
                    System.out.println(BOWLING_FRAME);

                    for (int j = 0; j < gameResults.getSize(); j++) {
                        if (i >= j) {
                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printFinalFrameWhenStrikeFourth(gameResults.getResultByIndex(j));
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                                printFinalFrameWhenSpareThird(gameResults.getResultByIndex(j));
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                                printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            printScoreUntil(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                        }

                        if (i < j) {
                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(STRIKE)) {
                                printFinalFrameWhenStrikeThird(gameResults.getResultByIndex(j));
                                printScoreAtFirst(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isResult(SPARE)) {
                                printFinalFrameWhenSpareThird(gameResults.getResultByIndex(j));
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }

                            if (gameResults.getResultByIndex(j).getFrameByFrameId(FRAME_ID_FINAL).isGutterOrMiss()) {
                                printNormalFrameSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                                printScoreAtSecond(gameResults.getResultByIndex(j), FRAME_ID_FINAL);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void printFinalFrameWhenStrikeThird(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < FRAME_ID_FINAL; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(SCORE, "X|" + gameResult.getFrameByFrameId(10).getThirdPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFinalFrameWhenSpareThird(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < FRAME_ID_FINAL; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(THIRD_SCORE,
                gameResult.getFrameByFrameId(10).getFirstPoint()
                        + "|/|"
                        + gameResult.getFrameByFrameId(10).getThirdPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFinalFrameWhenStrikeFourth(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(String.format(NAME, gameResult.getName()));

        for (int i = FRAME_ID_FIRST; i < FRAME_ID_FINAL; i++) {
            name.append(String.format(SCORE, getScore(gameResult.getFrameByFrameId(i))));
        }
        name.append(String.format(THIRD_SCORE, "X|"
                + gameResult.getFrameByFrameId(10).getThirdPoint()
                + "|"
                + gameResult.getFrameByFrameId(10).getFourthPoint()));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printScoreFirst(GameResult gameResult) {
        StringBuilder name = new StringBuilder();
        name.append(SCORE_BOX);
        name.append(String.format(SCORE_ONE_DIGIT, getScoreUntil(gameResult, 1)));

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printFirstFrameScoreAtSecond(GameResult gameResult) {
        Frame frame = gameResult.getFrameByFrameId(FRAME_ID_FIRST);

        if (frame.isResult(STRIKE) || frame.isResult(SPARE)) {
            printScoreBlank();
        }

        if (frame.isGutterOrMiss()) {
            printScoreFirst(gameResult);
        }
    }

    private static void printSecondFrameScoreAtFirst(GameResult gameResult, int frameId) {
        Frame prevFrame = gameResult.getFrameByFrameId(frameId - 1);

        if (prevFrame.isResult(STRIKE)) {
            printScoreBlank();
        }

        if (!prevFrame.isResult(STRIKE)) {
            printScoreUntil(gameResult, frameId - 1);
        }
    }

    private static void printSecondFrameScoreAtSecond(GameResult gameResult, int frameId) {
        Frame frame = gameResult.getFrameByFrameId(frameId);
        Frame prevFrame = gameResult.getFrameByFrameId(frameId - 1);

        if (frame.isResult(STRIKE) && !prevFrame.isResult(STRIKE)) {
            printScoreUntil(gameResult, frameId - 1);
        }
        if (frame.isResult(STRIKE) && prevFrame.isResult(STRIKE)) {
            printScoreBlank();
        }

        if (frame.isResult(SPARE)) {
            printScoreUntil(gameResult, frameId - 1);
        }

        if (frame.isGutterOrMiss()) {
            printScoreUntil(gameResult, frameId);
        }
    }

    private static void printScoreAtFirst(GameResult gameResult, int frameId) {
        Frame prevFrame = gameResult.getFrameByFrameId(frameId - 1);
        Frame prevPrevFrame = gameResult.getFrameByFrameId(frameId - 2);

        if (prevFrame.isResult(STRIKE) && !prevPrevFrame.isResult(STRIKE)) {
            printScoreUntil(gameResult, frameId - 2);
        }

        if (prevFrame.isResult(SPARE)) {
            printScoreUntil(gameResult, frameId - 1);
        }

        if (prevFrame.isGutterOrMiss()) {
            printScoreUntil(gameResult, frameId - 1);
        }
    }

    private static void printScoreAtSecond(GameResult gameResult, int frameId) {
        Frame frame = gameResult.getFrameByFrameId(frameId);
        Frame prevFrame = gameResult.getFrameByFrameId(frameId - 1);

        if (frame.isResult(STRIKE) && !prevFrame.isResult(STRIKE)) {
            printScoreUntil(gameResult, frameId - 1);
        }
        if (frame.isResult(STRIKE) && prevFrame.isResult(STRIKE)) {
            printScoreUntil(gameResult, frameId - 2);
        }

        if (frame.isResult(SPARE)) {
            printScoreUntil(gameResult, frameId - 1);
        }

        if (frame.isGutterOrMiss()) {
            printScoreUntil(gameResult, frameId);
        }
    }

    private static void printScoreUntil(GameResult gameResult, int frameId) {
        StringBuilder name = new StringBuilder();
        name.append(SCORE_BOX);

        for (int i = FRAME_ID_FIRST; i <= frameId; i++) {
            if (getScoreUntil(gameResult, i) >= 100) {
                name.append(String.format(SCORE_THREE_DIGIT, getScoreUntil(gameResult, i)));
            }

            if (getScoreUntil(gameResult, i) >= 10 && getScoreUntil(gameResult, i) < 100) {
                name.append(String.format(SCORE_TWO_DIGIT, getScoreUntil(gameResult, i)));
            }

            if (getScoreUntil(gameResult, i) < 10) {
                name.append(String.format(SCORE_ONE_DIGIT, getScoreUntil(gameResult, i)));
            }
        }

        System.out.println(name.toString());
        System.out.println();
    }

    private static void printScoreBlank() {
        StringBuilder name = new StringBuilder();
        name.append(SCORE_BOX);

        System.out.println(name.toString());
        System.out.println();
    }

    private static int getScoreUntil(GameResult gameResult, int frameId) {
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