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
    public static final int INDEX_TWO = 2;
    public static final String NONE = "";
    public static final String STRING_ZERO = "0";
    public static final int NINE_FRAME_INDEX = 9;

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

    private static void score(Frames frames, User user, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (Frame frame : frames.getFrames()) {
            State state = frame.getState(userIndex);
            printf(FORMAT_SPACE, String.valueOf(state));
        }
        remainFrames(frames.size());
        nextLine();
    }

    private static void sum(Frames frames, User user, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, NONE);

        TotalScore totalScore = new TotalScore();
        for (int i = INDEX_ZERO; i < frames.size(); i++) {
            strike(frames, totalScore, i, userIndex);
            spare(frames, totalScore, i, userIndex);
            miss(frames, totalScore, i, userIndex);
            gutter(frames, totalScore, i, userIndex);
        }
        if (frames.size() == 1) {
            remainFrames(frames.size() - 1);
        }
        nextLine();
    }

    private static void strike(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Strike) {
            totalScore.add(thisState.getScore().getFrameScore());
            strikeBonus(frames, totalScore, frameIndex, userIndex);
            strikePrint(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void strikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getScore(userIndex));
        }
        if (frameIndex + INDEX_TWO < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_TWO).getScore(userIndex));
        }
    }

    private static void strikePrint(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        // 스트라이크 && 스트라이크 && 스트라이크
        if (frameIndex + INDEX_TWO < frames.size()) {
            Frame nextNextFrame = frames.get(frameIndex + INDEX_TWO);
            State nextNextState = nextNextFrame.getState(userIndex);
            printThisFrameStrike(totalScore, nextNextState);
        }
        // 9프레임
        if (frameIndex == NINE_FRAME_INDEX) {
            Frame nineFrame = frames.get(NINE_FRAME_INDEX);
            State nineFrameState = nineFrame.getState(userIndex);
            printThisFrameStrike(totalScore, nineFrameState);
        }
        // 스트라이크 && Other
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(userIndex);
            printQualifiedState(totalScore, nextState);
        }
    }

    private static void printThisFrameStrike(TotalScore totalScore, State state) {
        if (state instanceof Strike) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void printQualifiedState(TotalScore totalScore, State nextState) {
        if (!nextState.isFinished()) {
            return;
        }
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void spare(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Spare) {
            totalScore.add(thisState.getScore().getFrameScore());
            spareBonus(frames, totalScore, frameIndex, userIndex);
            sparePrint(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void spareBonus(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getFirstScore(userIndex));
        }
    }

    private static void sparePrint(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(userIndex);
            printSpareQualifiedState(totalScore, nextState);
        }
    }

    private static void printSpareQualifiedState(TotalScore totalScore, State nextState) {
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void miss(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Miss) {
            totalScore.add(thisState.getScore().getFrameScore());
            missPrint(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void missPrint(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(userIndex);
            printQualifiedState(totalScore, nextState);
        }
    }

    private static void gutter(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Gutter) {
            gutterPrint(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void gutterPrint(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(userIndex);
            printQualifiedState(totalScore, nextState);
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
