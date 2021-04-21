package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static final int MAX_FRAME_INDEX = 9;
    public static final String DIVIDER = "|";
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public void printResult(BowlingGames bowlingGames) {

        for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
            printResultDetail(bowlingGame.getPlayer().getName(), bowlingGame.getFrames().getFrames(),
                bowlingGame.getScore());
        }
    }

    public void printResultDetail(String playerName, List<Frame> frames, List<Integer> scores) {
        printHeader();

        System.out.print(DIVIDER);
        printText(playerName);
        printFrames(frames);
        printRemainDivider(frames.size());

        System.out.print(DIVIDER);
        printText("");
        printScores(scores);
        printRemainDivider(scores.size());

    }

    private void printText(String str) {
        System.out.print(center(str, 6));
        System.out.print(DIVIDER);
    }

    private String center(String s, int size) {
        char pad = ' ';
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public void printEmptyResult(List<String> playerNames) {
        for (String playerName : playerNames) {
            printResultDetail(playerName, new ArrayList<>(), new ArrayList<>());
        }

    }

    private void printHeader() {
        System.out.println(HEADER);
    }


    private void printFrames(List<Frame> frames) {
        for (int i = 0; i< frames.size(); i++) {
            printFrame(i, frames);
        }

    }

    private void printFrame(int index, List<Frame> frames) {
        if (!isFinalFrame(index)) {
            printNormalFrame(frames.get(index));
            return;

        }
        printFinalFrame(frames.get(index));
    }

    private void printFinalFrame(Frame frame) {
        FinalFrame finalFrame = (FinalFrame) frame;
        printText(makeStatesToString(((FinalFrame) frame).getStates()));
    }

    public static String makeStatesToString(LinkedList<State> states) {
        return states.stream()
            .map(State::toString)
            .collect(Collectors.joining("|"));
    }

    private void printNormalFrame(Frame frame) {
        NormalFrame normalFrame = (NormalFrame) frame;
        printText(normalFrame.getState().toString());
    }

    private boolean isFinalFrame(int index) {
        return index == MAX_FRAME_INDEX;
    }


    private void printRemainDivider(int size) {
        for (int i = size; i<= MAX_FRAME_INDEX; i++) {
            printText("");
        }
        System.out.print(System.lineSeparator());
    }

    private void printScores(List<Integer> scores) {
        scores.forEach(score -> printText(String.valueOf(score)));
    }

}
