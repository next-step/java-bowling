package bowling.view;

import bowling.domain.FrameRecordIterator;
import bowling.domain.Player;
import bowling.domain.Players;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingResultView {

    private static final String BOARD_DELIMITER = "|";
    private static final String EMPTY_SECTION = StringUtils.repeat(" ", 6);
    private static final String FRAME_HEADER;

    static {
        FRAME_HEADER = createFrameHeader();
    }

    public static Runnable printFrameHeader() {
        return () -> System.out.println(FRAME_HEADER);
    }

    public static void printEmptyScoreBoard(Players players) {
        System.out.println(FRAME_HEADER
                + System.lineSeparator()
                + players.stream()
                        .map(BowlingResultView::getEmptyScoreBoard)
                        .collect(Collectors.joining(System.lineSeparator()))
        );
    }

    public static Consumer<FrameRecordIterator> printPlayerScore() {
        return (frameRecordIterator) -> {
            Player player = frameRecordIterator.getPlayer();
            System.out.println(getPlayerRow(player, frameRecordIterator.getMarkingIterator()));
        };
    }

    private static String getEmptyScoreBoard(Player player) {
        return getPlayerRow(player, Collections.emptyIterator());
    }

    private static String getPlayerRow(Player player, Iterator<String> iterator) {
        return createRow(i -> i == 0
                ? String.format("%4s", player.getName())
                : iterator.hasNext() ? iterator.next() : EMPTY_SECTION);
    }

    private static String createRow(IntFunction<String> mapper) {
        return StringUtils.wrap(
                IntStream.rangeClosed(0, 10)
                        .mapToObj(value -> format(mapper.apply(value)))
                        .collect(Collectors.joining(BOARD_DELIMITER)), BOARD_DELIMITER);
    }

    private static String createFrameHeader() {
        return createRow(i -> i == 0 ? "NAME" : createFrameNumber(i));
    }

    private static String createFrameNumber(int frameNumber) {
        return StringUtils.leftPad(String.valueOf(frameNumber), 2, '0');
    }

    private static String format(String value) {
        if (StringUtils.isBlank(value)) {
            return EMPTY_SECTION;
        }

        return StringUtils.wrap(
                value.length() > 2
                        ? StringUtils.rightPad(value, 4, " ")
                        : StringUtils.center(value, 4, " "), " ");
    }
}
