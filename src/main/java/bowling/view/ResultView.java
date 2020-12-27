package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.*;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {
    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String DELIMITER = "|";
    public static final int FINAL_FRAME = 10;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;
    public static final String NONE = "";

    public static void init(Frames frames, Players players) {
        header();
        scores(frames, players);
    }

    public static void print(Frames frames, Players players) {
        header();
        scores(frames, players);
    }

    private static void header() {
        println(Grid.header());
    }

    private static void scores(Frames frames, Players players) {
        for (int playerIndex = INDEX_ZERO; playerIndex < players.size(); playerIndex++) {
            score(frames, players.get(playerIndex), playerIndex);
            sum(frames, playerIndex);
        }
    }

    private static void score(Frames frames, Player player, int playerIndex) {
        println(Grid.score(frames, player, playerIndex));
    }

    private static void sum(Frames frames, int playerIndex) {
        println(Grid.sum(frames, playerIndex));
    }






























    public static void last(Frames frames, Players players) {
        header();
        lastScores(frames, players);
    }

    private static void lastScores(Frames frames, Players players) {
        for (int userIndex = INDEX_ZERO; userIndex < players.size(); userIndex++) {
            lastScore(frames, players.get(userIndex), userIndex);
            lastSum(frames, userIndex);
        }
    }

    private static void lastScore(Frames frames, Player player, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, player.getName());
        for (int frameIndex = INDEX_ZERO; frameIndex < frames.size() - INDEX_ONE; frameIndex++) {
            State state = frames.getFrames().get(frameIndex).getState(userIndex);
            printf(FORMAT_SPACE, String.valueOf(state));
        }

        // 10 프레임
        State state = frames.getFrames().get(FINAL_FRAME - INDEX_ONE).getState(userIndex);
        printf(FORMAT_SPACE, String.valueOf(state));

        nextLine();
    }

    private static void lastSum(Frames frames, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, NONE);

        TotalScore totalScore = new TotalScore();
        for (int frameIndex = INDEX_ZERO; frameIndex < frames.size(); frameIndex++) {
//            addStrike(frames, totalScore, frameIndex, userIndex);
//            addSpare(frames, totalScore, frameIndex, userIndex);
//            addMiss(frames, totalScore, frameIndex, userIndex);
//            addGutter(frames, totalScore, frameIndex, userIndex);
        }

        // 10 프레임
        Frame lastFrame = frames.get(FINAL_FRAME - INDEX_ONE);
        State lastState = lastFrame.getState(userIndex);

        totalScore.add(lastState.getScore().getFrameScore());
        printf(FORMAT_SPACE, totalScore.get());

        nextLine();
    }

    private static void remainFrames(int frameSize) {
        for (int i = frameSize; i < FINAL_FRAME; i++) {
            printf(FORMAT_SPACE, NONE);
        }
    }

    private static void printf(String format, String args) {
        System.out.printf(format, args);
    }

    private static void println(String args) {
        System.out.println(args);
    }

    private static void nextLine() {
        System.out.println();
    }

    private ResultView() {
    }
}
