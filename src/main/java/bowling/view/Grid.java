package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.*;

import java.util.stream.IntStream;

public class Grid {
    public static final String HEADER_NAME = "| NAME |";
    public static final int START_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;
    public static final int INDEX_TWO = 2;
    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String STRING_ZERO = "0";
    public static final String DELIMITER = "|";
    public static final String NONE = "";

    public static String header() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER_NAME);
        IntStream.rangeClosed(START_FRAME, FINAL_FRAME)
                .forEach(value -> {
                    String no = value < FINAL_FRAME ? STRING_ZERO + value : String.valueOf(value);
                    sb.append(String.format(FORMAT_SPACE, no));
                });
        return sb.toString();
    }

    public static String score(Frames frames, Player player, int playerIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DELIMITER + FORMAT_SPACE, player.getName()));
        int remainSize = 0;
        for (Frame frame : frames.getFrames()) {
            State state = frame.getState(playerIndex);
            sb.append(String.format(FORMAT_SPACE, state));
            remainSize++;
        }
        sb.append(remainFrames(remainSize));
        return sb.toString();
    }

    public static String sum(Frames frames, int playerIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DELIMITER + FORMAT_SPACE, NONE));

        int remain = 0;
        TotalScore totalScore = new TotalScore();
        for (int frameIndex = INDEX_ZERO; frameIndex < frames.size(); frameIndex++) {
            remain += addStrike(frames, totalScore, frameIndex, playerIndex);
            sb.append(printStrike(frames, totalScore, frameIndex, playerIndex));

            addSpare(frames, totalScore, frameIndex, playerIndex);
            sb.append(printSpare(frames, totalScore, frameIndex, playerIndex));

            addMiss(frames, totalScore, frameIndex, playerIndex);
            sb.append(printMiss(frames, totalScore, frameIndex, playerIndex));

            sb.append(printGutter(frames, totalScore, frameIndex, playerIndex));
        }

        sb.append(remainFrames(remain - INDEX_ONE));
        return sb.toString();
    }

    private static int addStrike(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        int sum = 0;

        if (state instanceof Strike) {
            totalScore.add(state.getScore().getFrameScore());
            sum++;
            addStrikeBonus(frames, totalScore, frameIndex, playerIndex);
        }
        return sum;
    }

    private static void addStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getFrameScore(playerIndex));
        }
        if (frameIndex + INDEX_TWO < frames.size()) {
            addNextStrikeBonus(frames, totalScore, frameIndex + INDEX_TWO, playerIndex);

        }
    }

    private static void addNextStrikeBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex);
        State nextState = nextFrame.getState(playerIndex);

        if (nextState instanceof Strike) {
            totalScore.add(frames.get(frameIndex).getFrameScore(playerIndex));
        }
    }

    private static String printStrike(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        if (state instanceof Strike) {
            return printStrikeInstance(frames, totalScore, frameIndex, playerIndex);
        }
        return NONE;
    }

    private static String printStrikeInstance(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        // Strike && Strike
        if (frameIndex + INDEX_TWO < frames.size()) {
            return printStrikeAndStrike(frames, totalScore, frameIndex, playerIndex);
        }
        // Strike && Spare
        if (frameIndex + INDEX_ONE < frames.size()) {
            return printStrikeAndSpare(frames, totalScore, frameIndex, playerIndex);
        }
        // 9프레임 출력
        if (frameIndex == FINAL_FRAME - INDEX_ONE) {
            Frame lastFrame = frames.get(FINAL_FRAME - INDEX_ONE);
            State lastState = lastFrame.getState(playerIndex);
//            printThisFrameStrike(totalScore, lastState);
        }

        return String.format(FORMAT_SPACE, NONE);
    }

    private static String printStrikeAndStrike(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextNextFrame = frames.get(frameIndex + INDEX_TWO);
        State nextNextState = nextNextFrame.getState(playerIndex);
        if (nextNextState instanceof Strike) {
            return String.format(FORMAT_SPACE, totalScore.get());
        }
        return NONE;
    }

    private static String printStrikeAndSpare(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
        State nextState = nextFrame.getState(playerIndex);
        if (nextState instanceof Spare) {
            return String.format(FORMAT_SPACE, totalScore.get());
        }
        return NONE;
    }

    private static String printStrikeQualifiedState(TotalScore totalScore, State nextState) {
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            return String.format(FORMAT_SPACE, totalScore.get());
        }
        return NONE;
    }

    private static void addSpare(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);

        if (state instanceof Spare) {
            totalScore.add(state.getScore().getFrameScore());
            addSpareBonus(frames, totalScore, frameIndex, playerIndex);
        }
    }

    private static void addSpareBonus(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            totalScore.add(frames.get(frameIndex + INDEX_ONE).getFirstScore(playerIndex));
        }
    }

    private static String printSpare(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        if (frameIndex + INDEX_ONE < frames.size()) {
            Frame nextFrame = frames.get(frameIndex + INDEX_ONE);
            State nextState = nextFrame.getState(playerIndex);
            return printSpareQualifiedState(totalScore, nextState);
        }
        return NONE;
    }

    private static String printSpareQualifiedState(TotalScore totalScore, State nextState) {
        if (nextState instanceof Spare || nextState instanceof Miss || nextState instanceof Gutter) {
            return String.format(FORMAT_SPACE, totalScore.get());
        }
        return NONE;
    }

    private static void addMiss(Frames frames, TotalScore totalScore, int frameIndex, int userIndex) {
        Frame thisFrame = frames.get(frameIndex);
        State thisState = thisFrame.getState(userIndex);

        if (thisState instanceof Miss) {
            totalScore.add(thisState.getScore().getFrameScore());
        }
    }

    private static String printMiss(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        return printFinishedState(totalScore, state);
    }

    private static String printFinishedState(TotalScore totalScore, State state) {
        if (state instanceof Spare || state instanceof Miss || state instanceof Gutter) {
            return String.format(FORMAT_SPACE, totalScore.get());
        }
        return NONE;
    }

    private static String printGutter(Frames frames, TotalScore totalScore, int frameIndex, int playerIndex) {
        Frame frame = frames.get(frameIndex);
        State state = frame.getState(playerIndex);
        return printFinishedState(totalScore, state);
    }

    private static String remainFrames(int frameSize) {
        StringBuilder sb = new StringBuilder();
        for (int i = frameSize; i < FINAL_FRAME; i++) {
            sb.append(String.format(FORMAT_SPACE, NONE));
        }
        return sb.toString();
    }

    private Grid() {
    }
}
