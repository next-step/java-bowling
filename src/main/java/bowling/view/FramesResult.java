package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.ScoreType;
import bowling.domain.player.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FinalFrame.MAX_FRAME_NUMBER;
import static bowling.domain.Pin.MAX_COUNT;

public class FramesResult {
    private final static String FRAME_NUMBER_LINE;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";

    private final Player player;
    private final Frames frames;

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    public FramesResult(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String frameNumberLine() {
        return FRAME_NUMBER_LINE;
    }

    public String frameScores() {
        return createPlayerName() + createScores();
    }

    private String createPlayerName() {
        return String.format("| %4s |", player.Name().value());
    }

    private String createScores() {
        return frames.values()
                .stream()
                .map(this::createFrameScore)
                .collect(Collectors.joining())
                .replaceAll("10", STRIKE)
                .replaceAll("0", GUTTER)
                + createEmptyFrame(frames.lastFrame());
    }

    private String createFrameScore(Frame frame) {
        if (isFirstFrame(frame)) {
            return "";
        }

        if (frame.pinsSize() == 3) {
            if (isFirstSpare(frame)) {
                return String.format(" %d|/|%d|", frame.pinNumber(0), frame.pinNumber(2));
            }

            if (isSecondSpare(frame)) {
                return String.format(" %d|%d|/|", frame.pinNumber(0), frame.pinNumber(1));
            }

            return String.format(" %d|%d|%d|", frame.pinNumber(0), frame.pinNumber(1), frame.pinNumber(2));
        }

        ScoreType score = frame.scoreStatus();
        if (score.equals(ScoreType.FINAL_STRIKE)) {
            return String.format("  %d|%d |", frame.pinNumber(0), frame.pinNumber(1));
        }

        if (score.equals(ScoreType.STRIKE) || score.equals(ScoreType.PROCEEDING)) {
            return String.format("  %d   |", frame.pinNumber(0));
        }

        if (score.equals(ScoreType.SPARE)) {
            return String.format("  %d|/ |", frame.pinNumber(0));
        }

        return String.format("  %d|%d |", frame.pinNumber(0), frame.pinNumber(1));
    }

    private boolean isFirstSpare(Frame frame) {
        return frame.pinNumber(0) != MAX_COUNT &&
                frame.pinNumber(0) + frame.pinNumber(1) == MAX_COUNT;
    }

    private boolean isSecondSpare(Frame frame) {
        return frame.pinNumber(1) + frame.pinNumber(2) == MAX_COUNT;
    }

    private String createEmptyFrame(Frame frame) {
        if (isFirstFrame(frame)) {
            return "      |".repeat(MAX_FRAME_NUMBER - frame.number() + 1);
        }

        return "      |".repeat(MAX_FRAME_NUMBER - frame.number());
    }

    private boolean isFirstFrame(Frame frame) {
        return frame.number() == 1 && frame.isEmpty();
    }
}
