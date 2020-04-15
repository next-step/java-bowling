package bowling.view;

public class ResultView {

    private static final String VERTICAL = "|";
    private static final String NAME = "NAME";
    private static final String SCORE_BOARD_TOP;

    static {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%-3s%s  ", VERTICAL, NAME));

        for (int i = 1; i < 11; i++) {
            buffer.append(String.format("%-3s%02d  ", VERTICAL, i));
        }
        buffer.append(VERTICAL);

        SCORE_BOARD_TOP = buffer.toString();
    }

    public static void printScoreBoardTop() {
        System.out.println(SCORE_BOARD_TOP);
    }
}
