package bowling.view;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.user.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ResultView {

    private static final String SCORE_BOARD_START_FORMAT = "| NAME |";
    private static final String SCORE_BOARD_FORMAT = "  %02d  |";

    private static final String SCORE_START_FORMAT = "|  %3s |";
    private static final String SCORE_EMPTY_FORMAT = "      |";
    private static final String SCORE_FORMAT = "  %-3s |";

    private static final String SCORE_DEMLIMITER = "|";

    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    public static void printStartBoard(User user) {
        printScoreBoard();
        printEmptyScore(user);
    }

    private static void printEmptyScore(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SCORE_START_FORMAT, user.nameToString()));
        IntStream.range(0, 10)
            .forEach(index -> sb.append(SCORE_EMPTY_FORMAT));
        System.out.println(sb.toString());
    }

    public static void printResult(User user, Frames frames) {
        printScoreBoard();
        printScore(user, frames);
    }

    private static void printScoreBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(SCORE_BOARD_START_FORMAT);
        sb.append(IntStream.rangeClosed(1, 10)
            .mapToObj(index -> String.format(SCORE_BOARD_FORMAT, index))
            .collect(joining()));
        System.out.println(sb.toString());
    }

    private static void printScore(User user, Frames frames) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SCORE_START_FORMAT, user.nameToString()));
        List<Frame> framesList = frames.values();
        IntStream.range(0, framesList.size())
            .filter(index -> Objects.nonNull(framesList.get(index)))
            .mapToObj(framesList::get)
            .map(pin -> sb.append(String.format(SCORE_FORMAT, scoreToString(pin.score()))));


        for (int i = 0; i < framesList.size(); i++) {
            if (Objects.isNull(framesList.get(i))) {
                sb.append(String.format(SCORE_FORMAT, ""));
            } else {
                Frame frame = framesList.get(i);
                sb.append(String.format(SCORE_FORMAT, scoreToString(frame.score())));
            }
        }
        for (int i = framesList.size(); i < 10; i++) {
            sb.append(String.format(SCORE_FORMAT, ""));
        }

        System.out.println(sb.toString());
    }

    private static String scoreToString(Score score) {
        if (Objects.isNull(score)) {
            return "";
        }
        List<Pin> values = score.values();
        return IntStream.range(0, values.size())
            .filter(index -> Objects.nonNull(values.get(index)))
            .mapToObj(index -> pinToString(values, index))
            .collect(joining(SCORE_DEMLIMITER));
    }

    private static String pinToString(List<Pin> score, int index) {
        Pin nowPin = score.get(index);
        if (nowPin == Pin.of(10)) {
            return STRIKE;
        }
        if (nowPin == Pin.of(0)) {
            return GUTTER;
        }
        if (index == 1 && Score.isSpare(score.get(0), score.get(1))) {
            return SPARE;
        }
        return valueOf(nowPin.value());
    }


}
