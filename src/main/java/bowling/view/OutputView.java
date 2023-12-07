package bowling.view;

import bowling.domain.FrameBoard;
import bowling.view.formatter.OutputFomatter;
import bowling.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;
    private final OutputFomatter formatter;

    public OutputView(Printer printer, OutputFomatter formatter) {
        this.printer = printer;
        this.formatter = formatter;
    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }

    public void printBoard(FrameBoard frameBoard) {
        printer.printLine("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        String name = formatter.toName(frameBoard);
        String board = formatter.toBoard(frameBoard);
        printer.printLine("| %s  | %s|", name, board);
    }
}
