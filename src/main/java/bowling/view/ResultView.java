package bowling.view;

import bowling.utility.Assert;

import java.io.PrintStream;

public final class ResultView {


    private static final String NAME_COLUMN_HEADER = "NAME";
    private static final String FRAME_MESSAGE_FORMAT = "%02d";

    private final PrintStream printer;

    private ResultView(PrintStream printer) {
        Assert.notNull(printer, "printer must not be null");
        this.printer = printer;
    }

    public static ResultView from(PrintStream output) {
        return new ResultView(output);
    }

    public void print() {

    }
}
