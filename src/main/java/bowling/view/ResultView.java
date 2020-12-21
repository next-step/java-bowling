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
    public static final String STRING_ZERO = "0";

    public static void init(Frames frames, Users users) {
        header();
        scores(frames, users);
    }

    public static void print(Frames frames, Users users) {
        header();
        scores(frames, users);
    }

    private static void header() {
        print(HEADER_NAME);
        IntStream.range(START_FRAME, FINAL_FRAME + INDEX_ONE)
                .forEach(value -> {
                    String no = value < FINAL_FRAME ? STRING_ZERO + value : String.valueOf(value);
                    printf(FORMAT_SPACE, no);
                });
        nextLine();
    }

    private static void scores(Frames frames, Users users) {
        for (int i = INDEX_ZERO; i < users.size(); i++) {
            score(frames, users.get(i), i);
            sum(frames, users.get(i), i);
        }
    }

    private static void score(Frames frames, User user, int index) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        int remainSize = frames.size();
        for (Frame frame : frames.getFrames()) {
            State state = frame.getState(index);
            printf(FORMAT_SPACE, String.valueOf(state));
        }
        remainFrames(remainSize);
        nextLine();
    }

    private static void sum(Frames frames, User user, int index) {
        printf(DELIMITER + FORMAT_SPACE, NONE);

        TotalScore totalScore = new TotalScore();
        int remainSize = frames.size();
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);

            printStrike(frames, user, totalScore, i);
            printSpare(frames, user, totalScore, i);

            totalScore.add(frame.getScore(index));
            printNormal(totalScore, frame.getState(index));
        }
        remainFrames(remainSize - INDEX_ONE);
        nextLine();
    }

    private static void printStrike(Frames frames, User user, TotalScore totalScore, int index) {
        Frame frame = frames.get(index);
        State state = frame.getLastState();

        if (index != INDEX_ZERO) {
            State prevFrame = user.getState(frames.get(i - INDEX_ONE).getFrameNo());
            int prevFirstScore = user.getFirstScore(frames.get(i - INDEX_ONE).getFrameNo());

            printStrike(totalScore, state, prevFrame);
            printSpare(totalScore, prevFrame, prevFirstScore);
        }
    }

    private static void printSpare(Frames frames, User user, TotalScore totalScore, int index) {
        Frame frame = frames.get(index);
        State state = frame.getLastState();

        if (index != INDEX_ZERO) {
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

    private ResultView() {
    }
}
