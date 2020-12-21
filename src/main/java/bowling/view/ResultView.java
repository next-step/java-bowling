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
    public static final int ONE = 1;
    public static final String NONE = "";
    public static final int USER_INDEX = 0;
    public static final int ZERO_INDEX = 0;

    private final Frames frames;
    private final Users users;

    public ResultView(Frames frames, Users users) {
        this.frames = frames;
        this.users = users;
    }

    public void print() {
        header();
        scores();
    }

    private void header() {
        print(HEADER_NAME);
        IntStream.range(START_FRAME, FINAL_FRAME + ONE).forEach(value -> {
            String no = value < 10 ? "0" + value : String.valueOf(value);
            printf(FORMAT_SPACE, no);
        });
        nextLine();
    }

    private void scores() {
        for (int i = USER_INDEX; i < this.users.size(); i++) {
            score(this.frames, this.users.get(i));
            sum(this.frames, this.users.get(i));
        }
    }

    private void score(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (Frame frame : frames.getFrames()) {
            String state = String.valueOf(user.getState(frame.getFrameNo()));
            printf(FORMAT_SPACE, state);
        }
        remainFrames(frames.size());
        nextLine();
    }

    private static void sum(Frames frames, User user) {
        TotalScore totalScore = new TotalScore();
        printf(DELIMITER + FORMAT_SPACE, NONE);
        for (int i = 0; i < frames.size(); i++) {
            State state = user.getState(frames.get(i).getFrameNo());
            int frameScore = user.getScore(frames.get(i).getFrameNo());
            sumBonusAndPrint(frames, user, totalScore, i, state);
            totalScore.add(frameScore);
            normalPrint(totalScore, state);
        }
        remainFrames(frames.size());
        nextLine();
    }

    private static void normalPrint(TotalScore totalScore, State state) {
        if (state instanceof Miss || state instanceof Gutter) {
            isFinishedThenPrint(totalScore, state);
        }
    }

    private static void sumBonusAndPrint(Frames frames, User user, TotalScore totalScore, int i, State state) {
        if (i != ZERO_INDEX) {
            State prevFrame = user.getState(frames.get(i - 1).getFrameNo());
            int prevScore = user.getScore(frames.get(i - 1).getFrameNo());
            int prevFirstScore = user.getFirstScore(frames.get(i - 1).getFrameNo());

            printSumStrike(totalScore, state, prevFrame, prevScore);
            printSumSpare(totalScore, prevFrame, prevFirstScore);
        }
    }

    private static void printSumStrike(TotalScore totalScore, State state, State prevFrame, int prevScore) {
        if (prevFrame instanceof Strike) {
            totalScore.add(prevScore);
            isFinishedThenPrint(totalScore, state);
        }
    }

    private static void printSumSpare(TotalScore totalScore, State prevFrame, int prevFirstScore) {
        if (prevFrame instanceof Spare) {
            totalScore.add(prevFirstScore);
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void isFinishedThenPrint(TotalScore totalScore, State state) {
        if (state.isFinished()) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void remainFrames(int framesSize) {
        for (int i = framesSize; i < FINAL_FRAME; i++) {
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
