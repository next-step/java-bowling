package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import bowling.domain.ScoreType;
import bowling.domain.player.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FramesResult {
    private final static String FRAME_NUMBER_LINE;

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, 10)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    private final Player player;
    private final Frames frames;


    public FramesResult(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public String getFrameNumberLine() {
        return FRAME_NUMBER_LINE;
    }

    public String getFrameScores() {
        return String.format("| %4s |", player.Name().value()) + createScores();
    }

    private String createScores() {
        return frames.values()
                .stream()
                .map(this::createScore)
                .collect(Collectors.joining("")).replaceAll("0", "-") + createEmptyFrame(frames.lastFrame());
    }

    private String createScore(Frame frame) {
        if (frame instanceof NormalFrame) {
            return createNormalFrameScore(frame);
        }

        return createFinalFrameScore(frame);
    }

    private String createFinalFrameScore(Frame frame) {
        if (frame.firstPin().count() == 10) {
            return "  X   |";
        }

        if (frame.canPitch()) {
            return String.format("  %d   |", frame.firstPin().count());
        }

        if (frame.score().equals(ScoreType.SPARE)) {
            return String.format("  %d|/ |", frame.firstPin().count());
        }

        return String.format("  %d|%d |", frame.firstPin().count(), frame.secondPin().count());

    }

    private String createNormalFrameScore(Frame frame) {
        if (frame.number() == 1 && frame.isEmpty()) {
            return "";
        }

        if (frame.score().equals(ScoreType.PROCEEDING)) {
            return String.format("  %d   |", frame.firstPin().count());
        }

        ScoreType score = frame.score();
        if (score.equals(ScoreType.STRIKE)) {
            return "  X   |";
        }

        if (score.equals(ScoreType.SPARE)) {
            return String.format("  %d|/ |", frame.firstPin().count());
        }

        return String.format("  %d|%d |", frame.firstPin().count(), frame.secondPin().count());
    }

    private static String createEmptyFrame(Frame frame) {
        if (frame.number() == 1 && frame.isEmpty()) {
            return "      |".repeat(10 - frame.number() + 1);
        }

        return "      |".repeat(10 - frame.number());
    }
}
