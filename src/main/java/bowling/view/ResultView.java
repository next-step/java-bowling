package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.frame.Frame;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

public class ResultView {

    public static final int MAX_FRAME_COUNT = 10;
    public static final String DIVIDER = "|";
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public void printResult(BowlingGames bowlingGames) {
        printHeader();

        for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
            printText(bowlingGame.getPlayer().getName());
            printFrames(bowlingGame.getFrames().getFrames());

            printScores(bowlingGame.getScore());

        }
    }

    public void printResult(String playerName, List<Frame> frames, List<Integer> scores) {
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
        System.out.print(StringUtils.center(str, 6));
        System.out.print(DIVIDER);
    }


    public void printEmptyResult(String playerName) {
        printResult(playerName, new ArrayList<>(), new ArrayList<>());
    }

    private void printHeader() {
        System.out.println(HEADER);
    }


    private void printFrames(List<Frame> frames) {
        frames.forEach(frame -> printText(frame.getFallenPins()));
    }

    private void printRemainDivider(int size) {
        for (int i = size; i< MAX_FRAME_COUNT; i++) {
            printText("");
        }
        System.out.print(System.lineSeparator());
    }

    private void printScores(List<Integer> scores) {
        scores.forEach(score -> printText(String.valueOf(score)));
    }


}
