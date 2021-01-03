package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;

import java.util.stream.IntStream;

public class Grid {
    public static final String HEADER_NAME = "| NAME |";
    public static final String HEADER_BLANK = "|  %-3s |";
    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String STRING_ZERO = "0";
    public static final String NONE = "";
    public static final int START_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;
    public static final int INDEX_TWO = 2;
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
        sb.append(String.format(HEADER_BLANK, "   "));
        IntStream.rangeClosed(START_FRAME, FINAL_FRAME)
                .forEach(value -> sb.append(String.format(FORMAT_SPACE, NONE)));
        return sb.toString();
    }

    public static String score(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(HEADER_BLANK, player.getName()));
        int remain = 0;
        for (Frame frame : player.getFrames().getFrames()) {
            sb.append(String.format(FORMAT_SPACE, frame.getState()));
            remain++;
        }
        sb.append(remainFrames(remain));
        return sb.toString();
    }

    public static String sum(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(HEADER_BLANK, "   "));

        int remain = 0;
        int score = 0;
        int bonus1;
        int bonus2;
        for (Frame frame : player.getFrames().getFrames()) {
            score += frame.getFrameScore();
            bonus1 = frame.getBonusScore();
            bonus2 = frame.getBonus2Score();
            score += bonus1 + bonus2;

            if (frame instanceof FinalFrame && frame.isVisible()) {
                sb.append(String.format(FORMAT_SPACE, score));
                remain++;
                continue;
            }
            if (frame.isVisible()) {
                sb.append(String.format(FORMAT_SPACE, score));
                remain++;
            }
        }
        sb.append(remainFrames(remain));
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
