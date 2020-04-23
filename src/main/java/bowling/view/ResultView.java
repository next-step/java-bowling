package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.game.BowlingGame;
import bowling.domain.game.BowlingGames;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class ResultView {
    private static final int TOTAL_FRAME_COUNT = 10;
    private static final int DEFAULT_SCORE = 0;

    private static final String FRAME_HEAD = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String FRAME_FOOTER = "----------------------------------------------------------------------------------------------------";
    private static final String FRAME_LINE = "|";
    private static final String FRAME_EMPTY = "        |";

    public static void viewResult(BowlingGames bowlingGames) {
        viewFrameHead();
        bowlingGames.getBowlingGames().stream()
                .forEach(ResultView::viewGame);
    }

    private static void viewGame(BowlingGame bowlingGame) {
        viewPlayerName(bowlingGame.getPlayerName());
        viewFrames(bowlingGame.getFrames());
        viewScores(bowlingGame.getFrames());
        viewFrameFooter();
    }

    private static void viewFrames(Frames frames) {
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining(FRAME_LINE));
        System.out.print(frameViews + FRAME_LINE);
        printEmptyFrames(TOTAL_FRAME_COUNT - frames.size());
    }

    private static void viewScores(Frames frames) {
        viewPlayerName("");
        StringBuilder sb = new StringBuilder();
        Integer sumScore = DEFAULT_SCORE;
        LinkedList<Frame> linkedFrames = frames.getFrames();
        for (int i = 0; i < frames.size(); i++) {
            sb.append(getScores(linkedFrames.get(i), sumScore));
            sumScore += getSumScore(linkedFrames.get(i).getScore());
        }
        System.out.print(sb.toString());
        printEmptyFrames(TOTAL_FRAME_COUNT - frames.size());
    }

    private static String getScores(Frame preframe, Integer sumScore) {
        Integer score = preframe.getScore();
        if (score != null) {
            return String.format("%5s   |", score + sumScore);
        }
        return FRAME_EMPTY;
    }

    private static int getSumScore(Integer score) {
        if (score != null) {
            return score;
        }
        return DEFAULT_SCORE;
    }

    private static String viewFrame(Frame frame) {
        if (frame instanceof NormalFrame) {
            return String.format("%5s   ", frame.getStatus().print());
        }
        return String.format(" %5s  ", frame.getStatus().print());
    }

    private static void printEmptyFrames(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(FRAME_EMPTY);
        }
        System.out.println(sb.toString());
    }

    private static void viewPlayerName(String name) {
        System.out.print(String.format("|%6s  |", name));
    }

    private static void viewFrameHead() {
        System.out.println(FRAME_HEAD);
    }

    private static void viewFrameFooter() {
        System.out.println(FRAME_FOOTER);
    }
}
