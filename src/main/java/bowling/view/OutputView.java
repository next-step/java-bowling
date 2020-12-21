package bowling.view;

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

    private static String getNameString(Player player) {
        return StringUtils.wrap(StringUtils.center(player.getName(), FRAME_LENGTH), INFIX);
    }

    private static String getFrameGuideString() {
        return IntStream.range(0, 10)
            .mapToObj(frame -> StringUtils.center(String.format("%02d", frame + 1), FRAME_LENGTH))
            .collect(Collectors.joining(INFIX));
    }

    public static void showFramesGuide() {
        out.println(getNameGuideString() + getFrameGuideString() + INFIX);
    }

    public static void showEmptyRecords(Player player) {
        showFramesGuide();

        StringBuilder builder = new StringBuilder(getNameString(player));
        addRestFrameString(builder, 0);

        out.println(builder.toString());
    }

    public static void showRecords(Player player, Frame frame) {
        showFramesGuide();

        StringBuilder builder = new StringBuilder(getNameString(player));
        addFrameString(frame, builder);
        builder.append("\n");
        builder.append(getNameString(player));
        addScoreString(frame, builder);

        out.println(builder.toString());
    }

    private static void addScoreString(Frame frame, StringBuilder builder) {
        int frameCount = 0;
        Frame currentFrame = frame;
        int totalScore = 0;
        while (Objects.nonNull(currentFrame)) {
            Score score = currentFrame.getScore();
            if (!score.isFinished()) {
                break;
            }
            totalScore = totalScore + score.getScore();
            builder.append(StringUtils.center(totalScore + "", FRAME_LENGTH)).append(INFIX);
            currentFrame = currentFrame.getNextFrame();
            frameCount++;
        }

        addRestFrameString(builder, frameCount);
    }

    private static void addFrameString(Frame frame, StringBuilder builder) {
        int frameCount = 0;
        Frame currentFrame = frame;
        while (Objects.nonNull(currentFrame)) {
            String pinsString = getPinsString(currentFrame);
            builder.append(StringUtils.center(pinsString, FRAME_LENGTH)).append(INFIX);
            currentFrame = currentFrame.getNextFrame();
            frameCount++;
        }

        addRestFrameString(builder, frameCount);
    }

    private static void addRestFrameString(StringBuilder builder, int frameCount) {
        int rest = TOTAL_FRAME_COUNT - frameCount;
        if (rest > 0) {
            builder.append(getRestFrameString(rest));
        }
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
