package bowling.view;

import bowling.model.Pins;
import bowling.model.frame.FrameNumber;
import bowling.utility.Assert;
import bowling.view.dto.FrameResponse;
import bowling.view.dto.StateResponse;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ResultView {

    private static final String PINS_DELIMITER = "|";
    private static final String SPARE_PINS_MESSAGE = "/";
    private static final String MAX_PINS_MESSAGE = "x";
    private static final String ZERO_PINS_MESSAGE = "-";
    private static final String FRAME_FORMAT = "  %-6s|";
    private static final String ROW_FORMAT = "| %4s |%s\n";
    private static final int FRAMES_SIZE = FrameNumber.LAST.toInt();
    private static final String COLUMN_HEADER = String.format(ROW_FORMAT, "NAME",
            IntStream.rangeClosed(1, FRAMES_SIZE)
                    .mapToObj(number -> String.format(FRAME_FORMAT, String.format("%02d", number)))
                    .collect(Collectors.joining()));

    private final PrintStream printer;

    private ResultView(PrintStream printer) {
        Assert.notNull(printer, "printer must not be null");
        this.printer = printer;
    }

    public static ResultView from(PrintStream output) {
        return new ResultView(output);
    }

    public void print(String name, List<FrameResponse> frames) {
        printer.print(COLUMN_HEADER);
        printer.printf(ROW_FORMAT, name, framesMessage(frames));
    }

    private String framesMessage(List<FrameResponse> frames) {
        Iterator<FrameResponse> iterator = frames.iterator();
        return IntStream.range(0, FRAMES_SIZE)
                .mapToObj(i -> {
                    if (!iterator.hasNext()) {
                        return String.format(FRAME_FORMAT, "");
                    }
                    return String.format(FRAME_FORMAT, frameMessage(iterator.next()));
                })
                .collect(Collectors.joining());
    }

    private String frameMessage(FrameResponse frame) {
        return String.join(PINS_DELIMITER, stateMessage(frame.getState()));
    }

    private List<String> stateMessage(StateResponse state) {
        List<String> countMessages = countMessages(state.getPinsCounts());
        if (state.isSpare()) {
            countMessages.add(SPARE_PINS_MESSAGE);
        }
        countMessages.addAll(countMessages(state.getBonusCounts()));
        return countMessages;
    }

    private List<String> countMessages(List<Integer> counts) {
        return counts.stream()
                .map(this::countMessage)
                .collect(Collectors.toList());
    }

    private String countMessage(Integer count) {
        if (count == Pins.MAX.count()) {
            return MAX_PINS_MESSAGE;
        }
        if (count == Pins.ZERO.count()) {
            return ZERO_PINS_MESSAGE;
        }
        return String.valueOf(count);
    }
}
