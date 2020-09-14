package bowling.ui;

public class BowlingResultView {
    public static final String SCORE_DELIMITER = " | ";

    private BowlingResultView() {

    }

    public static void printStatus(String status) {
        System.out.println(status);
    }
}
