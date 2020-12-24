package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.*;
import bowling.domain.user.User;
import bowling.domain.user.Users;

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
        for (int userIndex = INDEX_ZERO; userIndex < users.size(); userIndex++) {
            score(frames, users.get(userIndex), userIndex);
            sum(frames, userIndex);
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

    private static void sum(Frames frames, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, NONE);

        TotalScore totalScore = new TotalScore();
        for (int frameIndex = INDEX_ZERO; frameIndex < frames.size(); frameIndex++) {
            addStrike(frames, totalScore, frameIndex, userIndex);
            addSpare(frames, totalScore, frameIndex, userIndex);
            addMiss(frames, totalScore, frameIndex, userIndex);
            addGutter(frames, totalScore, frameIndex, userIndex);
        }
        remainFrames(frames.size() - 1);
        nextLine();
    }

    private static void addStrike(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Strike) {
            totalScore.add(thisState.getScore().getFrameScore());
            addStrikeBonus(frames, totalScore, frameIndex, userIndex);
            printStrike(frames, totalScore, frameIndex, userIndex);
        }
        if (thisState instanceof LastState) {
            totalScore.add(thisState.getScore().getFrameScore());
            printStrike(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void addStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getFrameScore(userIndex));
        }
        // 스트라이크 && 스트라이크 && 스트라이크
        if (frameIndex + INDEX_TWO < frames.size() && frameIndex < 8) {
            Frame nextFrame = frames.get(frameIndex + INDEX_TWO);
            State nextState = nextFrame.getState(userIndex);
            addQualifiedScore(frames, totalScore, frameIndex, userIndex, nextState);
        }
    }

    private static void addQualifiedScore(Frames frames, TotalScore totalScore, int frameIndex, int userIndex, State nextState) {
        if (nextState instanceof Strike) {
            totalScore.add(frames.get(frameIndex + INDEX_TWO).getFrameScore(userIndex));
        }
    }

    private static void printStrike(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        // 스트라이크 && 스트라이크 && 스트라이크
        if (frameIndex + INDEX_TWO < frames.size()) {
            Frame nextNextFrame = frames.get(frameIndex + INDEX_TWO);
            State nextNextState = nextNextFrame.getState(userIndex);
            printThisFrameStrike(totalScore, nextNextState);
        }
        // 9프레임 출력
        if (frameIndex == FINAL_FRAME - INDEX_ONE) {
            Frame lastFrame = frames.get(FINAL_FRAME - INDEX_ONE);
            State lastState = lastFrame.getState(userIndex);
            printThisFrameStrike(totalScore, lastState);
        }
        // 스트라이크 && Other
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(userIndex);
            printStrikeQualifiedState(totalScore, nextState);
        }
    }

    private static void printThisFrameStrike(TotalScore totalScore, State state) {
        if (state instanceof Strike) {
            printf(FORMAT_SPACE, totalScore.get());
        }
        if (state instanceof LastState) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void printStrikeQualifiedState(TotalScore totalScore, State nextState) {
        if (!nextState.isFinished()) {
            return;
        }
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void addSpare(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Spare) {
            totalScore.add(thisState.getScore().getFrameScore());
            addSpareBonus(frames, totalScore, frameIndex, userIndex);
            printSpare(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void addSpareBonus(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getFirstScore(userIndex));
        }
    }

    private static void printSpare(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
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

    private static void addMiss(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Miss) {
            totalScore.add(thisState.getScore().getFrameScore());
            printMiss(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void printMiss(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);
        printFinishedState(totalScore, thisState);
    }

    private static void printFinishedState(TotalScore totalScore, State thisState) {
        if (!thisState.isFinished()) {
            return;
        }
        if (thisState instanceof Spare || thisState instanceof Miss || thisState instanceof Gutter) {
            printf(FORMAT_SPACE, totalScore.get());
        }
    }

    private static void addGutter(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Gutter) {
            printGutte(frames, totalScore, frameIndex, userIndex);
        }
    }

    private static void printGutte(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);
        printFinishedState(totalScore, thisState);
    }

    public static void last(Frames frames, Users users) {
        header();
        lastScores(frames, users);
    }

    private static void lastScores(Frames frames, Users users) {
        for (int userIndex = INDEX_ZERO; userIndex < users.size(); userIndex++) {
            lastScore(frames, users.get(userIndex), userIndex);
            lastSum(frames, userIndex);
        }
    }

    private static void lastScore(Frames frames, User user, int userIndex) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
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
            addStrike(frames, totalScore, frameIndex, userIndex);
            addSpare(frames, totalScore, frameIndex, userIndex);
            addMiss(frames, totalScore, frameIndex, userIndex);
            addGutter(frames, totalScore, frameIndex, userIndex);
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
