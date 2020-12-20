package bowling.view;

import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.domain.interfaces.Frame;
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
    private static final PrintStream out = System.out;

    private static String getNameGuideString() {
        return StringUtils.wrap(StringUtils.center(NAME, FRAME_LENGTH), INFIX);
    }

    private static String getFrameGuideString() {
        return IntStream.range(0, 10)
            .mapToObj(frame -> StringUtils.center(String.format("%02d", frame + 1), FRAME_LENGTH))
            .collect(Collectors.joining(INFIX));
    }

    public static void showFramesGuide() {
        out.println(getNameGuideString() + getFrameGuideString() + INFIX);
    }
}
