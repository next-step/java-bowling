package bowling.view;

import bowling.domain.*;

import java.util.stream.IntStream;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {
    public static final String HEADER_NAME = "| NAME |";
    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String DELIMITER = "|";
    public static final int START_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;
    public static final String NONE = "";

    public static void print(Frames frames, Users users) {
        header();
        scores(frames, users);
    }

    private static void header() {
        print(HEADER_NAME);
        IntStream.range(START_FRAME, FINAL_FRAME + INDEX_ONE)
                .forEach(value -> {
                    String no = value < 10 ? "0" + value : String.valueOf(value);
                    printf(FORMAT_SPACE, no);
                });
        nextLine();
    }

    private static void scores(Frames frames, Users users) {
        for (int i = INDEX_ZERO; i < users.size(); i++) {
            score(frames, users.get(i));
            sum(frames, users.get(i));
        }
    }

    private static void score(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (Frame frame : frames.getFrames()) {
            String state = String.valueOf(user.getState(frame.getFrameNo()));
            printf(FORMAT_SPACE, state);
        }
        remainFrames(frames.size());
        nextLine();
    }

    private static void sum(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, NONE);

        TotalScore totalScore = new TotalScore();
        for (int i = 0; i < frames.size(); i++) {
            int frameNo = frames.get(i).getFrameNo();
            State frameState = user.getState(frameNo);
            int frameScore = user.getScore(frameNo);
            printStrikeAndSpare(frames, user, totalScore, i, frameState);

            totalScore.add(frameScore);
            printNormal(totalScore, frameState);
        }
        remainFrames(frames.size() - 1);
        nextLine();
    }

    private static void printStrikeAndSpare(Frames frames, User user, TotalScore totalScore, int i, State state) {
        if (i != INDEX_ZERO) {
            State prevFrame = user.getState(frames.get(i - INDEX_ONE).getFrameNo());
            int prevFirstScore = user.getFirstScore(frames.get(i - INDEX_ONE).getFrameNo());

            printStrike(totalScore, state, prevFrame);
            printSpare(totalScore, prevFrame, prevFirstScore);
        }
    }

    private static void printStrike(TotalScore totalScore, State state, State prevFrame) {
        if (prevFrame instanceof Strike) {
            totalScore.add(state.getScore().getFrameScore());
            printFinished(totalScore, state);
        }
    }

    private static void printSpare(TotalScore totalScore, State prevFrame, int prevFirstScore) {
        if (prevFrame instanceof Spare) {
            totalScore.add(prevFirstScore);
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void printNormal(TotalScore totalScore, State state) {
        if (state instanceof Miss || state instanceof Gutter) {
            printFinished(totalScore, state);
        }
    }

    private static void printFinished(TotalScore totalScore, State state) {
        if (state.isFinished()) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void remainFrames(int frameSize) {
        for (int i = frameSize; i < FINAL_FRAME; i++) {
            printf(FORMAT_SPACE, NONE);
        }
    }

    private static void print(String args) {
        System.out.print(args);
    }

    private static void printf(String format, String args) {
        System.out.printf(format, args);
    }

    private static void nextLine() {
        System.out.println();
    }
}
