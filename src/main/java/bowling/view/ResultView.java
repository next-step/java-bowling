package bowling.view;

import bowling.domain.Bowling;

public class ResultView {
    private static final String UPPER_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10    |";

    public static void printBowling(Bowling bowling) {
        StringBuilder sb = new StringBuilder();
        sb.append(UPPER_FRAME)
                .append(System.lineSeparator())
                .append("|");
        bowling.toPrint()
                .forEach(frame -> sb.append(frame)
                        .append("|"));
        sb.append(System.lineSeparator());
        System.out.println(sb);
    }

}
