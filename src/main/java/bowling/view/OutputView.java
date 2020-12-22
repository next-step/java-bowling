package bowling.view;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.state.Pins;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    public static final String NAME = "NAME";
    public static final String INFIX = "|";
    public static final int FRAME_LENGTH = 6;
    public static final String STRIKE_MARK = "X";
    public static final String SPARE_MARK = "/";
    public static final String GUTTER_MARK = "-";
    public static final int TOTAL_FRAME_COUNT = 10;
    private static final PrintStream out = System.out;

    private static String getNameGuideString() {
        return StringUtils.wrap(StringUtils.center(NAME, FRAME_LENGTH), INFIX);
    }

    private static String getFrameWithWord(String word) {
        return StringUtils.wrap(StringUtils.center(word, FRAME_LENGTH), INFIX);
    }

    private static String getFrameGuideString() {
        return IntStream.range(0, 10)
            .mapToObj(frame -> StringUtils.center(String.format("%02d", frame + 1), FRAME_LENGTH))
            .collect(Collectors.joining(INFIX));
    }

    public static String makeFramesGuide() {
        return getNameGuideString() + getFrameGuideString() + INFIX;
    }

    public static void showGame(Game game) {
        List<Frame> firstFrames = game.getCurrentFrames();
        List<Player> players = game.getPlayers();

        IntStream.range(0, players.size())
            .forEach(idx -> showGameOfPlayer(players.get(idx), firstFrames.get(idx)));
        out.println(makeFramesGuide());
    }

    public static void showGameOfPlayer(Player player, Frame frame) {
        StringBuilder builder = new StringBuilder();
        builder.append(getFrameWithWord(player.getName()))
            .append(makeFrameString(frame))
            .append("\n")
            .append(getFrameWithWord(StringUtils.EMPTY))
            .append(makeScoreString(frame));

        out.println(builder.toString());
    }

    private static String makeScoreString(Frame frame) {
        StringBuilder builder = new StringBuilder();

        Frame currentFrame = null;
        int totalScore = 0;
        int frameCount = 0;
        while (frame != currentFrame && Objects.nonNull(frame)) {
            currentFrame = frame;
            Score score = currentFrame.getScore();
            if (!score.isFinished()) {
                break;
            }
            totalScore = totalScore + score.getScore();
            builder.append(StringUtils.center(totalScore + "", FRAME_LENGTH)).append(INFIX);
            frame = currentFrame.getNextFrame();
            frameCount++;
        }

        builder.append(makeRestFrameString(frameCount));
        return builder.toString();
    }

    private static String makeFrameString(Frame frame) {
        StringBuilder builder = new StringBuilder();

        int frameCount = 0;
        Frame currentFrame = null;
        while (frame != currentFrame && Objects.nonNull(frame)) {
            currentFrame = frame;
            String pinsString = getPinsString(currentFrame);
            builder.append(StringUtils.center(pinsString, FRAME_LENGTH)).append(INFIX);
            frame = currentFrame.getNextFrame();
            frameCount++;
        }

        builder.append(makeRestFrameString(frameCount));
        return builder.toString();
    }

    private static String makeRestFrameString(int frameCount) {
        StringBuilder builder = new StringBuilder();

        int rest = TOTAL_FRAME_COUNT - frameCount;
        if (rest > 0) {
            builder.append(getRestFrameString(rest));
        }

        return builder.toString();
    }

    private static String getPinsString(Frame currentFrame) {
        List<Pins> pinsList = currentFrame.getPinsList();
        return pinsList.stream()
            .map(OutputView::getPinsString)
            .collect(Collectors.joining(INFIX));
    }

    private static String getRestFrameString(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> StringUtils.center("", FRAME_LENGTH))
            .collect(Collectors.joining(INFIX)) + INFIX;
    }

    public static String getPinsString(Pins pins) {
        if (pins.isStrike()) {
            return STRIKE_MARK;
        }
        if (pins.isSpare()) {
            return getGutterMark(pins.getFirstFall()) + INFIX + SPARE_MARK;
        }
        return pins.getFalls().stream()
            .map(OutputView::getGutterMark)
            .collect(Collectors.joining(INFIX));
    }

    private static String getGutterMark(int fall) {
        if (fall == 0) {
            return GUTTER_MARK;
        }
        return fall + "";
    }
}
