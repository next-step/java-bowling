package bowling.ui;

import bowling.domain.BowlingGame;
import bowling.domain.frame.*;
import bowling.domain.rolling.FinalRollings;
import bowling.domain.rolling.NormalRollings;
import bowling.domain.rolling.Rollings;
import bowling.utils.StringUtil;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {

    private static final String FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String SCORE = "  %d%s|";
    private static final String NONE = "      |";

    private ResultView() {
        throw new IllegalStateException();
    }

    public static void printFrame() {
        System.out.println(FRAME);
    }

    public static void printBowlingResult(List<BowlingGame> games) {
        ResultView.printFrame();
        games.forEach(game -> {
            ResultView.printFrameByPlayer(game);
            ResultView.printScoreByPlayer(game);
        });
    }

    public static void printFrameByPlayer(BowlingGame bowlingGame) {
        System.out.printf(PLAYER_NAME, bowlingGame.playerName());
        List<Frame> frames = bowlingGame.frames();
        frames.stream()
                .map(frame -> displayChar(frame.rollings()))
                .forEach(System.out::print);

        printNonFrame(frames.size());

    }

    public static void printScoreByPlayer(BowlingGame bowlingGame) {
        System.out.println();
        System.out.print("|      |");
        List<Frame> frames = bowlingGame.frames();

        int totalScore = 0;
        for (Frame frame : frames) {
            int score = displayScore(frames, frame);
            totalScore = displayScore(totalScore, score);
        }

        printNonFrame(frames.size());
        System.out.println();
    }

    private static int displayScore(int totalScore, int score) {
        if (score > 0) {
            totalScore += score;
            System.out.printf(SCORE, totalScore, StringUtil.pad(String.valueOf(totalScore).length(), 4, " "));
        }
        return totalScore;
    }

    private static int displayScore(List<Frame> frames, Frame frame) {
        int score = frame.score(frames).value();
        if (score == -1) {
            System.out.print(NONE);
        }
        return score;
    }

    private static void printNonFrame(int sizeOfFrame) {
        IntStream.range(0, 10 - sizeOfFrame)
                .mapToObj(i -> NONE)
                .forEach(System.out::print);
    }

    private static String displayChar(Rollings rollings) {
        if (rollings instanceof FinalRollings) {
            return FinalFrameDisplay.display((FinalRollings) rollings);
        }
        return NormalFrameDisplay.display((NormalRollings) rollings);
    }

}
