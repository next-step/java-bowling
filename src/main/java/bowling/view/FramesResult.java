package bowling.view;

import bowling.domain.Frame;
import bowling.domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FinalFrame.MAX_FRAME_NUMBER;
import static bowling.domain.Frame.NO_SCORE;

public class FramesResult {
    private static final String FRAME_NUMBER_LINE;
    private static final String FRAME_SIGN_BAR = " %3s  |".repeat(MAX_FRAME_NUMBER - 1) + " %-5s|";
    private static final String FRAME_SCORE_BAR = "|      |" + " %3s  |".repeat(MAX_FRAME_NUMBER);

    private final FrameResult frameResult = new FrameResult();

    static {
        FRAME_NUMBER_LINE = "| NAME |" + IntStream.rangeClosed(1, MAX_FRAME_NUMBER)
                .mapToObj(i -> String.format("  %02d  |", i))
                .collect(Collectors.joining());
    }

    public FramesResult() {

    }

    public String frameNumberLine() {
        return FRAME_NUMBER_LINE;
    }

    public String getPlayerFrameSigns(Player player) {
        return playerName(player) + createSigns(player);
    }

    private String playerName(Player player) {
        return String.format("| %4s |", player.getName().getValue());
    }

    private String createSigns(Player player) {
        List<String> signs = player.getFrames().values()
                .stream()
                .map(this::createFrameSign)
                .collect(Collectors.toList());

        return String.format(FRAME_SIGN_BAR, padEmptyString(signs));
    }

    private String createFrameSign(Frame frame) {
        return frameResult.frameSign(frame);
    }

    public String getPlayerScores(Player player) {
        List<Integer> scores = player.getFrames().values()
                .stream()
                .map(this::getScore)
                .filter(score -> score != NO_SCORE)
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        if (scores.size() > 0) {
            result.add(scores.get(0));
        }

        for (int i = 1; i < scores.size(); i++) {
            result.add(result.get(i - 1) + scores.get(i));
        }

        List<String> stringScores = result.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        return String.format(FRAME_SCORE_BAR, padEmptyString(stringScores));
    }

    private int getScore(Frame frame) {
        return frameResult.frameScore(frame);
    }

    private Object[] padEmptyString(List<String> strings) {
        int additionalSize = MAX_FRAME_NUMBER - strings.size();

        for (int i = 0; i < additionalSize; i++) {
            strings.add("");
        }

        return strings.toArray();
    }
}
