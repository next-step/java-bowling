package bowling.step2.view;

public class ResultView {
    private static final ResultView INSTANCE = new ResultView();

    private ResultView () {}

    public static ResultView getInstance () {
        return INSTANCE;
    }
}