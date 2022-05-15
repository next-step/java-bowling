package bowling.view;

import bowling.model.frame.FrameNumber;
import bowling.utility.Assert;
import bowling.view.dto.BowlingResponse;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ResultView {

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

    public void print(List<BowlingResponse> bowlings) {
        printer.print(COLUMN_HEADER);
        bowlings.forEach(this::printResult);
    }

    private void printResult(BowlingResponse bowling) {
        printer.printf(ROW_FORMAT, bowling.getName(), framesMessage(bowling.getFrames().getMarks()));
        printer.printf(ROW_FORMAT, "", framesMessage(bowling.getFrames().getScores()));
    }

    private String framesMessage(List<?> messages) {
        Iterator<?> iterator = messages.iterator();
        return IntStream.range(0, FRAMES_SIZE)
                .mapToObj(i -> {
                    if (!iterator.hasNext()) {
                        return String.format(FRAME_FORMAT, "");
                    }
                    return String.format(FRAME_FORMAT, iterator.next());
                })
                .collect(Collectors.joining());
    }
}
