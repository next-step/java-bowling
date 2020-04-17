package bowling.view;

import bowling.bowlinggame.BowlingGame;
import bowling.bowlinggame.BowlingGames;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;

import java.util.List;
import java.util.stream.Collectors;

import static bowling.view.FrameFormat.*;

public class ResultView {
    private static final int ONE = 1;

    public static void printReadyToBowlingGame(BowlingGames bowlingGames) {
        printBowlingFrame();
        for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
            printPlayerFrame(bowlingGame.getPlayer());
        }
    }

    private static void printBowlingFrame() {
        System.out.println(BOWLING_FRAME.getForamt());
    }

    public static void printPlayerFrame(Player player) {
        System.out.println(String.format(DEFAULT_SCORE_FRAME.getForamt(), player.getName()));
        System.out.println(EMPTY_FRAME.getForamt());
    }

    public static void printBowlingScores(BowlingGames bowlingGames) {
        printBowlingFrame();
        for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
            printBowlingScore(bowlingGame);
        }
    }

    private static void printBowlingScore(BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();
        Frames frames = bowlingGame.getFrames();
        StringBuilder result = new StringBuilder(String.format(NAME_FORMAT.getForamt(), player.getName()));
        StringBuilder pointResult = new StringBuilder(BLANK_FRAME.getForamt());

        for (int i = 0, end = frames.size(); i < end; i++) {
            result.append(formatScores(frames.get(i)));
            pointResult.append(formatPoint(frames, i));
        }

        System.out.println(addBlankFrame(frames.size(), result));
        System.out.println(addBlankFrame(frames.size(), pointResult));
    }

    private static String formatScores(Frame frame) {
        List<String> scores = frame.getScores().stream()
                .map(Score::pointToScore)
                .collect(Collectors.toList());

        if (scores.size() > ONE) {
            return String.format(MULTI_SCORE_FORMAT.getForamt(), String.join(FRAME_LINE.getForamt(), scores));
        }
        return String.format(SINGLE_SCORE_FORMAT.getForamt(), scores.get(0));
    }

    private static String formatPoint(Frames frames, int frameIndex) {
        return String.format(POINT_SCORE_FORMAT.getForamt(), frames.calculateFramePoint(frameIndex));
    }

    private static StringBuilder addBlankFrame(int frameSize, StringBuilder result) {
        for (int i = 0; i < 10 - frameSize; i++) {
            result.append(BLANK_FRAME_FORMAT.getForamt());
        }
        return result;
    }
}
