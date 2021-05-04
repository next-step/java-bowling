package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.util.BowlingFixture.*;

public final class ResultView {

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String DELIMITER = "|";
    private static final String NAME_FORMAT = " %4s ";

    private static final FrameView NORMAL_FRAME_VIEW;
    private static final FrameView FINAL_FRAME_VIEW;

    static {
        NORMAL_FRAME_VIEW = NormalFrameView.getInstance();
        FINAL_FRAME_VIEW = FinalFrameView.getInstance();
    }

    private static final class ResultViewHolder {
        private static final ResultView instance = new ResultView();
    }

    private ResultView() {
    }

    public static final ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(Player player, Frames frames) {
        StringBuilder scoreboardBuilder = new StringBuilder();
        scoreboardBuilder.append(SCORE_BOARD_HEADER);
        scoreboardBuilder.append(nameFormat(player.name()));

        List<Frame> frameList = frames.frames();
        IntStream.range(FRAME_START_INDEX, FRAME_LAST_INDEX)
                .mapToObj(index -> frameList.get(index - ONE))
                .map(NORMAL_FRAME_VIEW::formatFrameScore)
                .forEach(scoreboardBuilder::append);
        scoreboardBuilder.append(FINAL_FRAME_VIEW.formatFrameScore(frameList.get(FRAME_LAST_INDEX - ONE)));
        System.out.println(scoreboardBuilder);
    }

    private String nameFormat(String sentence) {
        return DELIMITER + String.format(NAME_FORMAT, sentence) + DELIMITER;
    }

}
