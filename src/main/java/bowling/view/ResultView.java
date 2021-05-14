package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
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

    public final void printScoreBoard(final Players players) {
        final StringBuilder scoreboardsBuilder = new StringBuilder();
        scoreboardsBuilder.append(scoreBoardHead());
        scoreboardsBuilder.append(scoreBoardBody(players));
        System.out.println(scoreboardsBuilder);
    }

    private final String scoreBoardHead() {
        return SCORE_BOARD_HEADER + NEWLINE;
    }

    private final String scoreBoardBody(final Players players) {
        return players.stream()
                .map(this::playerScoreBoard)
                .collect(Collectors.joining());
    }

    private final String playerScoreBoard(final Player player) {
        final StringBuilder scoreboardBuilder = new StringBuilder();
        scoreboardBuilder.append(frontFrame(player.name()));

        final Frames frames = player.frames();
        scoreboardBuilder.append(normalFrameStatus(frames));
        scoreboardBuilder.append(finalFrameStatus(frames));

        final Scores scores = player.scores();
        final List<String> scoreFrameList = scoreFrameList(scores);
        scoreboardBuilder.append(frontFrame(Strings.EMPTY));
        scoreboardBuilder.append(scoreFrame(scoreFrameList));
        scoreboardBuilder.append(emptyScoreFrame(scoreFrameList)).append(NEWLINE);
        return scoreboardBuilder.toString();
    }

    private final String normalFrameStatus(final Frames frames) {
        return IntStream.range(NORMAL_FRAME_START_INDEX, LAST_FRAME_INDEX)
                .mapToObj(frames::get)
                .map(Frame::description)
                .map(this::normalFrame)
                .collect(Collectors.joining());
    }

    private final String finalFrameStatus(final Frames frames) {
        return finalFrame(frames.get(LAST_FRAME_INDEX).description());
    }

    private final List<String> scoreFrameList(final Scores scores) {
        return scores.stream()
                .limit(scores.size())
                .map(Score::score)
                .filter(this::filteringUnavailable)
                .map(this::normalFrame)
                .collect(Collectors.toList());
    }

    private final boolean filteringUnavailable(final int score) {
        return score != UN_AVAILABLE;
    }

    private final String scoreFrame(final List<String> scoreFrameList) {
        return scoreFrameList.stream().collect(Collectors.joining());
    }

    private final String emptyScoreFrame(final List<String> scoreFrameList) {
        return IntStream.range(scoreFrameList.size(), Frame.LAST_SEQUENCE)
                .mapToObj(i -> Strings.EMPTY)
                .map(this::normalFrame)
                .collect(Collectors.joining());
    }

    private final String frontFrame(final String sentence) {
        return DELIMITER + String.format(FRONT_FRAME, sentence) + DELIMITER;
    }

    private final String normalFrame(final int mark) {
        return normalFrame(String.valueOf(mark));
    }

    private final String normalFrame(final String mark) {
        return String.format(NORMAL_FRAME, mark) + DELIMITER;
    }

    private final String finalFrame(final String mark) {
        return String.format(FINAL_FRAME, mark) + DELIMITER + NEWLINE;
    }

    public final void printResult(final Player player) {
        System.out.println(String.format(ENDING_MESSAGE, player.name(), player.sum()));
    }

}

