package bowling.view;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.point.Ordinal;
import bowling.domain.result.GameResult;
import bowling.domain.result.GameResults;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.*;
import static bowling.domain.point.Ordinal.*;

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
        printLineSeparator();
        printResult(gameResults.getResults().get(0), gameResults.getResults().get(1));
    }

    private static void printResult(GameResult gameResult1, GameResult gameResult2) {
        IntStream.range(0, FRAME_ID_FINAL)
                .forEach(integer
                        -> printFrameResultSoFar(gameResult1, gameResult2, integer));
    }

    private static void printFrameResultSoFar(GameResult gameResult1, GameResult gameResult2, int count) {
        if (gameResult1.getFrames().getFrames().get(count).isResult(STRIKE)) {
            printFrameResultWhenStrike(FIRST, gameResult1, gameResult2, count);
            printFrameResultWhenStrike(THIRD, gameResult1, gameResult2, count);
            printFrameResultWhenStrike(FOURTH, gameResult1, gameResult2, count);
        }

        if (!gameResult1.getFrames().getFrames().get(count).isResult(STRIKE)){
            printFrameResultWhenNonStrike(FIRST, gameResult1, gameResult2, count);
            printFrameResultWhenNonStrike(SECOND, gameResult1, gameResult2, count);
            printFrameResultWhenNonStrike(THIRD, gameResult1, gameResult2, count);
        }
    }

    private static void printFrameResultWhenStrike(Ordinal ordinal, GameResult gameResult1,
                                                   GameResult gameResult2, int count) {
        if (gameResult1.getFrames().getFrames().get(count).containsOrdinal(ordinal)) {
            System.out.print(gameResult1.getPlayerName().getName() + "'s turn");
            print(" : ");
            println(gameResult1.getFrames().getFrames().get(count).getPointAtOrdinal(ordinal));
            printPlayInformation();

            printForOnePlayer(ordinal, gameResult1.getPlayerName(), gameResult1.getFrames(), gameResult1.getFrames().getFrames().get(count));
            System.out.println();

            if(count == 0 && ordinal.equals(FIRST)){
                printName(gameResult2.getPlayerName().getName());
            }else if(count ==0 && ordinal.equals(SECOND)){
                printForOnePlayer(FIRST, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count !=0 && ordinal.equals(FIRST)){
                printForOnePlayer(SECOND, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count - 1));
            }else if(count !=0 && ordinal.equals(SECOND)){
                printForOnePlayer(FIRST, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count ==9 && ordinal.equals(THIRD)){
                printForOnePlayer(FIRST, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count ==9 && ordinal.equals(FOURTH)){
                printForOnePlayer(THIRD, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }

            printThreeLineSeparators();
        }

        if (gameResult1.getFrames().getFrames().get(count).containsOrdinal(ordinal)) {
            System.out.print(gameResult2.getPlayerName().getName() + "'s turn");
            print(" : ");
            println(gameResult2.getFrames().getFrames().get(count).getPointAtOrdinal(ordinal));
            printPlayInformation();

            printForOnePlayer(ordinal, gameResult1.getPlayerName(), gameResult1.getFrames(), gameResult1.getFrames().getFrames().get(count));
            System.out.println();

            printForOnePlayer(ordinal, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            printThreeLineSeparators();
        }
    }

    private static void printFrameResultWhenNonStrike(Ordinal ordinal, GameResult gameResult1,
                                                      GameResult gameResult2, int count) {
        if (gameResult1.getFrames().getFrames().get(count).containsOrdinal(ordinal)) {
            System.out.print(gameResult1.getPlayerName().getName() + "'s turn");
            print(" : ");
            println(gameResult1.getFrames().getFrames().get(count).getPointAtOrdinal(ordinal));
            printPlayInformation();

            printForOnePlayer(ordinal, gameResult1.getPlayerName(), gameResult1.getFrames(), gameResult1.getFrames().getFrames().get(count));
            System.out.println();

            if(count == 0 && ordinal.equals(FIRST)){
                printName(gameResult2.getPlayerName().getName());
            }else if(count ==0 && ordinal.equals(SECOND)){
                printForOnePlayer(FIRST, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count !=0 && ordinal.equals(FIRST)){
                printForOnePlayer(SECOND, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count - 1));
            }else if(count !=0 && ordinal.equals(SECOND)){
                printForOnePlayer(FIRST, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count ==9 && ordinal.equals(THIRD)){
                printForOnePlayer(SECOND, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }else if(count ==9 && ordinal.equals(FOURTH)){
                printForOnePlayer(THIRD, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            }

            printThreeLineSeparators();
        }

        if (gameResult2.getFrames().getFrames().get(count).containsOrdinal(ordinal)) {
            System.out.print(gameResult2.getPlayerName().getName() + "'s turn");
            print(" : ");
            println(gameResult2.getFrames().getFrames().get(count).getPointAtOrdinal(ordinal));
            printPlayInformation();

            printForOnePlayer(ordinal, gameResult1.getPlayerName(), gameResult1.getFrames(), gameResult1.getFrames().getFrames().get(count));
            System.out.println();

            printForOnePlayer(ordinal, gameResult2.getPlayerName(), gameResult2.getFrames(), gameResult2.getFrames().getFrames().get(count));
            printThreeLineSeparators();
        }
    }

    private static void printForOnePlayer(Ordinal ordinal, PlayerName playerName, Frames frames, Frame frame){
        printName(playerName.getName());
        frames.getFrames()
                .subList(0, frame.getFrameId() + OFFSET)
                .forEach(frame1 -> printFrame(frame1));
        printFrameByOrdinal(frame, ordinal);
        System.out.println();
        if (frame.getFrameId() != FRAME_ID_FIRST) {
            printFrameScoreSoFar(frames, frame, ordinal);
        }
        printCurrentFrameScore(frames, frame, ordinal);
    }

    private static void printFrameScoreSoFar(Frames frames, Frame frame, Ordinal ordinal) {
        printName(BLANK_THREE);

        if (!frames.getPreviousFrame(frame).isResult(STRIKE) && !frame.isFirstFrame()) {
            frames.getFrames()
                    .subList(0, frame.getFrameId() + OFFSET)
                    .forEach(frame1 -> printFrameScore(frames, frame1));
        }

        if (frames.getPreviousFrame(frame).isResult(STRIKE) && FIRST.equals(ordinal)) {
            frames.getFrames()
                    .subList(0, frame.getFrameId() + (OFFSET_DOUBLE))
                    .forEach(frame1 -> printFrameScore(frames, frame1));
        }

        if (frames.getPreviousFrame(frame).isResult(STRIKE) && !FIRST.equals(ordinal)) {
            frames.getFrames()
                    .subList(0, frame.getFrameId() + OFFSET)
                    .forEach(frame1 -> printFrameScore(frames, frame1));
        }
    }

    private static void printFrameScore(Frames frames, Frame frame) {
        print(BLANK_FIVE);

        print(frames.getTotalPointUntil(frame));

        printFormatting(frames, frame);

        printBlockBorder();
    }

    private static void printCurrentFrameScore(Frames frames, Frame frame, Ordinal ordinal) {
        print(BLANK_FOUR);

        if (!frame.isFinalFrame()) {
            printNormalFrameScore(frames, frame, ordinal);
        }

        if (frame.isFinalFrame()) {
            printFinalFrameScore(frames, frame, ordinal);
        }

        printFormatting(frames, frame);
        printBlockBorder();
    }

    private static void printNormalFrameScore(Frames frames, Frame frame, Ordinal ordinal) {
        if (frame.isResult(STRIKE) || frame.isResult(SPARE) || FIRST.equals(ordinal)) {
            print(BLANK_THREE);
        }

        if (SECOND.equals(ordinal) && frame.isGutterOrMiss() && !frame.isFirstFrame()) {
            print(frames.getTotalPointUntil(frame));
            print(BLANK_ONE);
        }

        if (SECOND.equals(ordinal) && frame.isFirstFrame() && !frame.isResult(SPARE)) {
            print(BLANK_SIX);
            print(BLOCK_BORDER);
            print(BLANK_FOUR);
            print(frames.getTotalPointUntil(frame));
            print(BLANK_ONE);
        }
    }

    private static void printFinalFrameScore(Frames frames, Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal) || SECOND.equals(ordinal) && frame.containsOrdinal(THIRD)) {
            print(BLANK_TWO);
        }

        if (SECOND.equals(ordinal) && !frame.containsOrdinal(THIRD)) {
            print(frames.getTotalPointUntil(frame));
        }

        if ((THIRD.equals(ordinal) && frame.isResult(SPARE))) {
            print(frames.getTotalPointUntil(frame));
        }

        if (FOURTH.equals(ordinal) && frame.isResult(STRIKE)) {
            print(frames.getTotalPointUntil(frame));
        }
    }

    private static void printFormatting(Frames frames, Frame frame) {
        if (frames.getTotalPointUntil(frame) >= MIN_NUMBER_FOR_THREE_DIGITS) {
            print(BLANK_TWO);
        }

        if (frames.getTotalPointUntil(frame) >= MIN_NUMBER_FOR_TWO_DIGITS) {
            print(BLANK_THREE);
        }

        if (frames.getTotalPointUntil(frame) < MIN_NUMBER_FOR_TWO_DIGITS) {
            print(BLANK_FOUR);
        }
    }

    private static void printFrame(Frame frame) {
        if (frame.isResult(STRIKE)) {
            printStrikeByOrdinal(frame, FIRST);
        }

        if (frame.isResult(SPARE)) {
            printSpareByOrdinal(frame, SECOND);
        }

        if (frame.isResult(MISS)) {
            printMissByOrdinal(frame, SECOND);
        }

        if (frame.isResult(GUTTER)) {
            printGutter();
        }
    }

    private static void printFrameByOrdinal(Frame frame, Ordinal ordinal) {
        if (frame.isResult(STRIKE)) {
            printStrikeByOrdinal(frame, ordinal);
        }

        if (frame.isResult(SPARE)) {
            printSpareByOrdinal(frame, ordinal);
        }

        if (frame.isResult(MISS)) {
            printMissByOrdinal(frame, ordinal);
        }

        if (frame.isResult(GUTTER)) {
            printGutter();
        }
    }

    private static void printStrikeByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenStrike();
        }

        if (THIRD.equals(ordinal)) {
            printFrameThirdWhenStrike(frame);
        }

        if (FOURTH.equals(ordinal)) {
            printFrameFourthWhenStrike(frame);
        }
    }

    private static void printFrameFirstWhenStrike() {
        print(BLANK_FIVE);
        print(SYMBOL_STRIKE);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printFrameThirdWhenStrike(Frame frame) {
        print(BLANK_FIVE);
        print(SYMBOL_STRIKE);
        print(BLOCK_BORDER);
        print(frame.getThirdPoint());
    }

    private static void printFrameFourthWhenStrike(Frame frame) {
        printFrameThirdWhenStrike(frame);
        print(BLOCK_BORDER);
        print(frame.getFourthPoint());
        print(BLANK_ONE);
        printBlockBorder();
    }

    private static void printSpareByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenSpare(frame);
        }

        if (SECOND.equals(ordinal)) {
            printFrameSecondWhenSpare(frame);
        }

        if (THIRD.equals(ordinal)) {
            printFrameThirdWhenSpare(frame);
        }
    }

    private static void printFrameFirstWhenSpare(Frame frame) {
        print(BLANK_FOUR);
        print(frame.getFirstPoint());

        print(BLANK_TWO);
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printFrameSecondWhenSpare(Frame frame) {
        print(BLANK_FOUR);
        print(frame.getFirstPoint());

        print(BLOCK_BORDER);
        print(DELIMITER_SPARE);

        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printFrameThirdWhenSpare(Frame frame) {
        print(BLANK_FOUR);
        print(frame.getFirstPoint());

        print(BLOCK_BORDER);
        print(DELIMITER_SPARE);

        print(BLOCK_BORDER);
        print(frame.getThirdPoint());

        print(BLANK_ONE);
        printBlockBorder();
    }

    private static void printMissByOrdinal(Frame frame, Ordinal ordinal) {
        if (FIRST.equals(ordinal)) {
            printFrameFirstWhenMiss(frame);
        }

        if (SECOND.equals(ordinal)) {
            printFrameSecondWhenMiss(frame);
        }
    }

    private static void printFrameFirstWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        print(frame.getFirstPoint());
        print(BLANK_FIVE);
        printBlockBorder();
    }

    private static void printFrameSecondWhenMiss(Frame frame) {
        print(BLANK_FOUR);
        print(frame.getFirstPoint());
        print(BLOCK_BORDER);
        print(frame.getSecondPoint());
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static void printGutter() {
        print(BLANK_FIVE);
        print(SYMBOL_GUTTER);
        print(BLANK_FOUR);
        printBlockBorder();
    }

    private static void printFrameId(Frame frame) {
        print(frame.getFrameId() + "프레임 투구");
    }

    private static void printPlayInformation() {
        printNameColumn(LABEL_NAME);
        IntStream.range(1, 11)
                .forEach(it -> printOneBlockWith(convertFrameNumberToString(it)));
        printLineSeparator();
    }

    private static void printNameColumn(String name) {
        printBlockBorder();
        printOneBlockWith(name);
    }

    private static void printName(String name) {
        printBlockBorder();
        print(BLANK_ONE);
        printOneBlockWith(name);
    }

    private static void printOneBlockWith(String message) {
        print(BLANK_THREE);
        print(message);
        print(BLANK_THREE);
        printBlockBorder();
    }

    private static String convertFrameNumberToString(int number) {
        String stringNumber
                = (number >= MIN_NUMBER_FOR_TWO_DIGITS)
                ? String.valueOf(BLANK_ONE + number + BLANK_ONE)
                : " 0" + number + BLANK_ONE;
        return stringNumber;
    }

    private static void printBlockBorder() {
        print(BLOCK_BORDER);
    }

    private static void printThreeLineSeparators() {
        IntStream.range(0, 3)
                .forEach(IntConsumer -> printLineSeparator());
    }

    private static void printLineSeparator() {
        System.out.println();
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void print(int number) {
        System.out.print(number);
    }

    private static void println(int message) {
        System.out.println(message);
    }
}