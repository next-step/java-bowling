package bowling.view;

import bowling.domain.Bowling;

public class ResultView {
    private static final String UPPER_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static String printBowling(Bowling bowling) {
        StringBuilder sb = new StringBuilder();
        sb.append(UPPER_FRAME)
                .append(System.lineSeparator());
        bowling.toPrint()
                .forEach(frame -> sb.append(frame));
        sb.append(System.lineSeparator())
                .append(System.lineSeparator());
        return sb.toString();
    }

}
