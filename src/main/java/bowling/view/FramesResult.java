package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.player.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FinalFrame.MAX_FRAME_NUMBER;

public class FramesResult {
    private final static String FRAME_NUMBER_LINE;

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
        return String.format("| %4s |", player.getName().getValue());
    }

    private String createScores() {
        return frames.values()
                .stream()
                .map(this::createFrameScore)
                .collect(Collectors.joining()) + createEmptyFrame(frames.lastFrame());
    }

    private String createFrameScore(Frame frame) {
        return new FrameResult(frame).getFrameScore() + " |";
    }

    private String createEmptyFrame(Frame frame) {
        return "      |".repeat(MAX_FRAME_NUMBER - frame.number());
    }
}
