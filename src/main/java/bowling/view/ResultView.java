package bowling.view;

public class ResultView {
    private static final String NAME = "NAME";
    private static final int FRAME_NUM = 10;
    private static final String VERTICAL = "|";
    private static final String BLANK = " ";
    private static final String NEW_LINE = "\n";


    private static ResultView resultView = new ResultView();

    public static ResultView getResultView() {
        return resultView;
    }

    public String initScoreBoard(String userName) {
        StringBuilder builder = new StringBuilder();
        setNameHeader(builder);
        setHeaderNumber(builder);
        setUserNameColumn(userName, builder);
        for (int i = 0; i < FRAME_NUM; i++) {
            setInitColumn(builder);
        }
        builder.append(NEW_LINE);
        return builder.toString();
    }

    private void setInitColumn(StringBuilder builder) {
        builder.append(appendBlank(6)).append(VERTICAL);
    }

    private void setUserNameColumn(String userName, StringBuilder builder) {
        builder.append(VERTICAL)
               .append(appendBlank(2))
               .append(userName)
               .append(BLANK)
               .append(VERTICAL);
    }

    private String appendBlank(int repeatCount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            builder.append(BLANK);
        }
        return builder.toString();
    }

    private void setHeaderNumber(StringBuilder builder) {
        for (int i = 1; i <= FRAME_NUM; i++) {
            builder.append(appendBlank(2))
                   .append(String.format("%02d", i))
                   .append(appendBlank(2))
                   .append(VERTICAL);
        }
        builder.append(NEW_LINE);
    }

    private void setNameHeader(StringBuilder builder) {
        builder.append(VERTICAL)
               .append(BLANK)
               .append(NAME)
               .append(BLANK)
               .append(VERTICAL);
    }
}
