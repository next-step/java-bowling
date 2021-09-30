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

        for (int i=0; i<frames.size(); i++){
            Frame frame = frames.get(i);
            Score score = frame.score();
            List<Pin> pins = score.values();
            sb.append(pinsToStrine(pins));
        }
        for (int i=frames.size(); i<=10; i++) {
            sb.append(String.format(BOARD_SCORE_FORMAT, ""));
        }
        System.out.println(sb.toString());
    }

    private static String toNameFormat(User user) {
        return String.format(BOARD_NAME_FORMAT, user.value());
    }

    private static String pinsToStrine(List<Pin> pins) {
        return IntStream.range(0, pins.size())
            .filter(index -> Objects.nonNull(pins.get(index)))
            .mapToObj(index -> pinToString(pins, index))
            .collect(Collectors.joining("|"));
    }

    private static String pinToString(List<Pin> pins, int index) {
        return PinType.pinToString(pins, index);
    }

}
