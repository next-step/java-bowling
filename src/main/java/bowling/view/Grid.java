package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TotalScore;
import bowling.domain.state.State;

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
    public static final int NORMAL_FRAME_MAX = 8;

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

    public static String blank() {
        StringBuilder sb = new StringBuilder();
        sb.append(DELIMITER).append(String.format(FORMAT_SPACE, NONE));
        IntStream.rangeClosed(START_FRAME, FINAL_FRAME)
                .forEach(value -> sb.append(String.format(FORMAT_SPACE, NONE)));
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
            remain = GridStrike.add(frames, totalScore, frameIndex, playerIndex);
            sb.append(GridStrike.print(frames, totalScore, frameIndex, playerIndex));

            remain += GridSpare.add(frames, totalScore, frameIndex, playerIndex);
            sb.append(GridSpare.print(frames, totalScore, frameIndex, playerIndex));

            remain += GridMiss.add(frames, totalScore, frameIndex, playerIndex);
            sb.append(GridMiss.print(frames, totalScore, frameIndex, playerIndex));

            remain += GridGutter.add(frames, totalScore, frameIndex, playerIndex);
            sb.append(GridGutter.print(frames, totalScore, frameIndex, playerIndex));
        }
        sb.append(remainFrames(frames.size() - remain));
        return sb.toString();
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
