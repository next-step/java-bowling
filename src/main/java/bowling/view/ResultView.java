package bowling.view;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.Game;
import bowling.domain.NormalFrame;
import bowling.domain.Pin;

public class ResultView {
    private static final String NAME = "NAME";
    private static final int FRAME_NUM = 10;
    private static final int COLUMN_INTERVAL = 6;
    private static final int TEXT_INTERVAL = 2;
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final String FRAME_NUM_FORMAT = "%02d";
    private static final String VERTICAL = "|";
    private static final String BLANK = " ";
    private static final String NEW_LINE = "\n";
    private static final String TITLE_BOWL = "프레임 투구 : ";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";
    private static final String STRIKE = "X";

    private static ResultView resultView = new ResultView();

    public static ResultView getResultView() {
        return resultView;
    }

    public String initScoreBoard(String userName) {
        StringBuilder builder = initBasicInfo(userName);
        repeatInitColumn(FRAME_NUM, builder);
        builder.append(NEW_LINE);
        return builder.toString();
    }

    public String playFrameResult(int frameNum, int falledPin) {
        StringBuilder builder = new StringBuilder();
        builder.append(frameNum)
               .append(TITLE_BOWL)
               .append(falledPin)
               .append(NEW_LINE);
        return builder.toString();
    }

    public String playFrameScoreBoard(Game game) {
        StringBuilder builder = initBasicInfo(game.getUserName());
        List<NormalFrame> frames = game.getFrames();

        for (NormalFrame frame : frames) {
            List<Pin> rolls = frame.getRolls().getRolls();
            if (frame.getFrameNum() != FRAME_NUM) {
                builder.append(displayRolls(rolls, frame.getFalledPins()));
                continue;
            }
            builder.append(displayLastGame(rolls));
        }

        repeatInitColumn(FRAME_NUM - frames.size(), builder);
        builder.append(NEW_LINE);
        return builder.toString();
    }

    private String displayLastGame(List<Pin> rolls) {
        StringBuilder builder = new StringBuilder();
        String lastGame = rolls.stream()
                               .map(roll -> displayScore(roll))
                               .collect(Collectors.joining(VERTICAL));
        builder.append(appendBlank(COLUMN_INTERVAL - lastGame.length()))
               .append(lastGame)
               .append(VERTICAL);
        return builder.toString();
    }

    public void printPlayFrame(Game game, NormalFrame normalFrame, int firstScore) {
        System.out.print(playFrameResult(normalFrame.getFrameNum(), firstScore));
        System.out.println(playFrameScoreBoard(game));
    }

    private String displayRolls(List<Pin> rolls, int falledPins) {
        StringBuilder builder = new StringBuilder(appendBlank(TEXT_INTERVAL));
        boolean spareFlag = isSpareFlag(rolls, falledPins);

        for (int i = 0; i < rolls.size(); i++) {
            String score = displayScore(rolls.get(i));
            appendScoreVertical(builder, i);
            score = isSpareScore(spareFlag, i, score);
            builder.append(score);
        }

        builder.append(appendBlank(COLUMN_INTERVAL - builder.length()));
        builder.append(VERTICAL);
        return builder.toString();
    }

    private String isSpareScore(boolean spareFlag, int i, String score) {
        if (i == 1 && spareFlag) {
            score = SPARE;
        }
        return score;
    }

    private void appendScoreVertical(StringBuilder builder, int i) {
        if (i == 1) {
            builder.append(VERTICAL);
        }
    }

    private boolean isSpareFlag(List<Pin> rolls, int falledPins) {
        boolean spareFlag = false;

        if (rolls.size() == 2 && falledPins == MAX_PINS) {
            spareFlag = true;
        }
        return spareFlag;
    }

    private String displayScore(Pin roll) {
        int rollFalledPins = roll.getFalledPins();
        String displayScore = String.valueOf(rollFalledPins);

        if (rollFalledPins == MAX_PINS) {
            return STRIKE;
        }

        if (rollFalledPins == MIN_PINS) {
            return GUTTER;
        }

        return displayScore;
    }

    private void repeatInitColumn(int repeatNum, StringBuilder builder) {
        for (int i = 0; i < repeatNum; i++) {
            setInitColumn(builder);
        }
    }

    private void setInitColumn(StringBuilder builder) {
        builder.append(appendBlank(COLUMN_INTERVAL)).append(VERTICAL);
    }

    private void setUserNameColumn(String userName, StringBuilder builder) {
        builder.append(VERTICAL)
               .append(appendBlank(TEXT_INTERVAL))
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
            builder.append(appendBlank(TEXT_INTERVAL))
                   .append(String.format(FRAME_NUM_FORMAT, i))
                   .append(appendBlank(TEXT_INTERVAL))
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

    private StringBuilder initBasicInfo(String userName) {
        StringBuilder builder = new StringBuilder();
        setNameHeader(builder);
        setHeaderNumber(builder);
        setUserNameColumn(userName, builder);
        return builder;
    }
}
