package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.score.Pin;
import bowling.domain.score.PinType;
import bowling.domain.score.Score;
import bowling.domain.user.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String NAME_BOARD = "| NAME |";
    private static final String BOARD_FORMAT = "  %02d  |";

    private static final String BOARD_NAME_FORMAT = "|  %-3s |";
    private static final String BOARD_SCORE_FORMAT = "  %-3s |";

    private ResultView() {
    }

    public static void printScoreBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(NAME_BOARD);
        sb.append(IntStream.rangeClosed(1, 10)
            .mapToObj(ResultView::toBoardFormat)
            .collect(Collectors.joining()));
        System.out.println(sb.toString());
    }

    private static String toBoardFormat(int num) {
        return String.format(BOARD_FORMAT, num);
    }

    public static void printScoreResultBoard(User user, List<Frame> frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(toNameFormat(user));

        sb.append(printFrameBoard(frames));
        sb.append(printRemainBoard(frames));
        System.out.println(sb.toString());
    }

    private static String printFrameBoard(List<Frame> frames) {
        return frames.stream()
            .map(Frame::score)
            .map(Score::values)
            .map(ResultView::pinsToString)
            .collect(Collectors.joining());
    }

    private static String printRemainBoard(List<Frame> frames) {
        return IntStream.rangeClosed(frames.size(), 10)
            .mapToObj(index -> String.format(BOARD_SCORE_FORMAT, ""))
            .collect(Collectors.joining());
    }

    private static String toNameFormat(User user) {
        return String.format(BOARD_NAME_FORMAT, user.value());
    }

    private static String pinsToString(List<Pin> pins) {
        return IntStream.range(0, pins.size())
            .filter(index -> Objects.nonNull(pins.get(index)))
            .mapToObj(index -> pinToString(pins, index))
            .collect(Collectors.joining("|"));
    }

    private static String pinToString(List<Pin> pins, int index) {
        return PinType.pinToString(pins, index);
    }

}
