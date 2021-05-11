package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.stream.IntStream;

public final class ResultView {

    private static final int NORMAL_FRAME_START_INDEX = 0;
    private static final int LAST_FRAME_INDEX = 9;

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String DELIMITER = "|";
    private static final String NAME_FORMAT = " %4s ";

    private static final class ResultViewHolder {
        private static final bowling.view.ResultView instance = new bowling.view.ResultView();
    }

    private ResultView() {
    }

    public static final bowling.view.ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(final Player player) {
        StringBuilder scoreboardBuilder = new StringBuilder();
        scoreboardBuilder.append(SCORE_BOARD_HEADER);
        scoreboardBuilder.append(nameFormat(player.name()));

        Frames frames = player.scoreBoard();
        IntStream.range(NORMAL_FRAME_START_INDEX, LAST_FRAME_INDEX)
                .mapToObj(index -> frames.get(index))
                .map(Frame::description)
                .forEach(scoreboardBuilder::append);
        System.out.println(scoreboardBuilder);
    }

    private String nameFormat(String sentence) {
        return DELIMITER + String.format(NAME_FORMAT, sentence) + DELIMITER;
    }

}

