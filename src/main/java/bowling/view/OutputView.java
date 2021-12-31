package bowling.view;

import bowling.engine.ScoreBoard;

public class OutputView {
    public static final String LINE = "|";
    private static final String NAME_FORMAT = LINE + "%5s ";
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    
    public static void printScoreBoard(ScoreBoard board) {
        System.out.println();
        System.out.println(HEADER);

        ScoreBoardPrinter printer = new ScoreBoardPrinter(board);

        System.out.printf(NAME_FORMAT, board.name());
        printer.printByFrame(printer::printShotResult);

        System.out.printf(NAME_FORMAT, "");
        printer.printByFrame(printer::printScore);
    }
}
