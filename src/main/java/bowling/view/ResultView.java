package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ResultView {

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String FRONT_FRAME = " %4s ";
    private static final String NORMAL_FRAME = "  %-3s ";
    private static final String FINAL_FRAME = " %-5s";
    private static final String DELIMITER = "|";
    private static final String NEWLINE = "\n";
    private static final String ENDING_MESSAGE = "%s님의 총합 점수 : %s";

    private static final int UN_AVAILABLE = -1;
    private static final int NORMAL_FRAME_START_INDEX = 0;
    private static final int LAST_FRAME_INDEX = 9;

    private static final class ResultViewHolder {
        private static final bowling.view.ResultView instance = new bowling.view.ResultView();
    }

    private ResultView() {
    }

    public static final bowling.view.ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(final Player player) {
        final StringBuilder scoreboardBuilder = new StringBuilder();
        scoreboardBuilder.append(SCORE_BOARD_HEADER).append(NEWLINE);
        scoreboardBuilder.append(frontFormat(player.name()));

        final Frames frames = player.frames();
        IntStream.range(NORMAL_FRAME_START_INDEX, LAST_FRAME_INDEX)
                .mapToObj(index -> frames.get(index))
                .map(Frame::description)
                .map(this::formatNormalFrame)
                .forEach(scoreboardBuilder::append);
        scoreboardBuilder.append(formatFinalFrame(frames.get(LAST_FRAME_INDEX).description()));
        scoreboardBuilder.append(frontFormat(Strings.EMPTY));

        final Scores scores = player.scores();
        final List<String> scoreFrameList = getScoreFrameList(scores);
        final String scoreFrame = getScoreFrame(scoreFrameList);
        final String emptyScoreFrame = getEmptyScoreFrame(scoreFrameList);

        scoreboardBuilder.append(scoreFrame);
        scoreboardBuilder.append(emptyScoreFrame).append(NEWLINE);
        System.out.println(scoreboardBuilder);
    }

    private final String getScoreFrame(final List<String> scoreFrameList) {
        return scoreFrameList.stream().collect(Collectors.joining());
    }

    private final String getEmptyScoreFrame(final List<String> scoreFrameList) {
        return IntStream.range(scoreFrameList.size(), Frame.LAST_SEQUENCE)
                .mapToObj(i -> Strings.EMPTY)
                .map(this::formatNormalFrame)
                .collect(Collectors.joining());
    }

    private final List<String> getScoreFrameList(final Scores scores) {
        return scores.stream()
                .limit(scores.size())
                .map(Score::score)
                .filter(this::filteringUnavailable)
                .map(String::valueOf)
                .map(this::formatNormalFrame)
                .collect(Collectors.toList());
    }

    private final boolean filteringUnavailable(final int score) {
        return score != UN_AVAILABLE;
    }

    private final String frontFormat(final String sentence) {
        return DELIMITER + String.format(FRONT_FRAME, sentence) + DELIMITER;
    }

    private final String formatNormalFrame(final String mark) {
        return String.format(NORMAL_FRAME, mark) + DELIMITER;
    }

    private final String formatFinalFrame(final String mark) {
        return String.format(FINAL_FRAME, mark) + DELIMITER + NEWLINE;
    }

    public final void printResult(final Player player) {
        System.out.println(String.format(ENDING_MESSAGE, player.name(), player.sum()));
    }

}

