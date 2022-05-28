package bowling.view;

import bowling.domain.Board;
import bowling.domain.User;
import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.state.State;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String RESULT_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String RESULT_FORMAT = "%s%s\n%s\n";
    private static final String GAME_END_MESSAGE = "[INFO] 게임 종료";
    private static final String EXCLUDE_STRING = "[|, \\s]";
    private static final String BASIC_SCORE_FORM = "  %s";
    private static final String NOT_EXIST_SCORE = "      |";
    private static final String FIRST_SPACE = "|      |";
    private static final String SPACE = " ";
    private static final String BAR = " ";
    private static final int LAST_FRAME_NUMBER = 10;

    private ResultView() {
    }

    public static void printResult(Board board) {
        System.out.println(RESULT_HEADER);
        System.out.println(board(board));
    }

    private static String board(Board board) {
        List<Frame> frames = board.frames();
        User user = board.user();

        return String.format(RESULT_FORMAT, user.mark(), allFrame(frames), scores(frames));
    }

    private static String allFrame(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number = 1; number <= 9; number++) {
            Frame normalFrame = frames.get(number - 1);
            stringBuilder.append(normalFrame.mark());
        }

        LastFrame lastFrame = (LastFrame) frames.get(LAST_FRAME_NUMBER - 1);
        stringBuilder.append(lastFrame(lastFrame.states()));
        return stringBuilder.toString();
    }

    private static String lastFrame(List<State> states) {
        StringBuilder scores = new StringBuilder(SPACE);
        String score = states.stream()
                .map(State::mark)
                .map(s -> s.replaceAll(EXCLUDE_STRING, ""))
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.joining("|"));

        scores.append(score);
        scores.append(SPACE.repeat(Math.max(0, 6 - scores.toString().length())));
        scores.append("|");
        return scores.toString();
    }

    private static String scores(List<Frame> frames) {
        return score(new StringBuilder(FIRST_SPACE), frames, 0, 0);
    }

    private static String score(StringBuilder result, List<Frame> frames, int frameNumber, int score) {
        if (frameNumber == LAST_FRAME_NUMBER) {
            return result.toString();
        }

        Frame frame = frames.get(frameNumber);
        if (frame.canCalculateCurrentScore()) {
            score += frame.score().score();
            String format = String.format(BASIC_SCORE_FORM, score);
            result.append(format)
                    .append(BAR.repeat(6 - format.length()))
                    .append("|");
        } else {
            result.append(NOT_EXIST_SCORE);
        }
        return score(result, frames, frameNumber + 1, score);
    }

    public static void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE);
    }
}
