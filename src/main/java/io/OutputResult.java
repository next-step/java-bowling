package io;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.score.Score;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class OutputResult {

    public final static int EMPTY = -1;
    public final static String SYMBOL_DELIMITER = "|";
    public final static String EMPTY_SPACE = "   ";
    private final static String TITLES = "NAME|  01  | 02  | 03  | 04  | 05  | 06  | 07  | 08  | 09  | 10  ";
    private final static String BOARD_DELIMITER = " | ";

    public static void printBoard(String name, Frames frames) {
        System.out.println(TITLES);
        List<String> symbols = printSymbol(name, frames);
        System.out.println(String.join(BOARD_DELIMITER, symbols));

        List<String> scores = printScore(frames);
        System.out.println(String.join(BOARD_DELIMITER, scores));
    }

    private static List<String> printScore(Frames frames) {
        List<String> scoreList = new ArrayList<>();
        int resultScore = 0;
        scoreList.add(EMPTY_SPACE);

        List<Frame> frames1 = frames.getFrames();
        for (Frame frame : frames1) {
            Score score = frame.getScore();
            int scoreValue = score.getValue();
            String stringScore = getStringScore(scoreValue, resultScore);
            scoreList.add(stringScore);
        }
        return scoreList;
    }

    private static String getStringScore(int score, int resultScore) {
        if(isEmptyScore(score)) {
            return EMPTY_SPACE;
        }
        resultScore += score;
        return String.valueOf(resultScore);
    }

    private static boolean isEmptyScore(int score) {
        return score == EMPTY;
    }

    private static List<String> printSymbol(String name, Frames frames) {
        List<String> symbols = new ArrayList<>();
        symbols.add(name);
        List<Frame> framesList = frames.getFrames();
        for (Frame frame : framesList) {
            List<String> states = getStates(frame);
            symbols.addAll(states);
        }
        return symbols;
    }

    private static List<String> getStates(Frame frame) {
        List<String> symbols = new ArrayList<>();
        List<State> states = frame.getState();
        for (State state : states) {
            symbols.add(state.toSymbol());
        }
        return symbols;
    }
}
