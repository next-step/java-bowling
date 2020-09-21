package bowling.view;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.score.Score;
import bowling.score.ScoreMark;
import bowling.score.Scores;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.global.utils.CommonConstant.*;

public class ResultView {

    private static final String PRINT_HEADER_NAME_COLUMN = "| NAME |";
    private static final String PRINT_BODY_PLAYER_NAME_COLUMN = "| %4s |";
    private static final String PRINT_HEADER_FRAME_NUMBER_COLUMN = "  %02d  |";
    private static final String PRINT_RESULT_COLUMN = "  %-3s |";
    private static final String PRINT_SCORE_DELIMITER = "|";

    private ResultView() {
    }

    public static void printGameBoard(String name, Frames bowling) {
        printHeader();
        printBody(name, bowling);
    }

    private static void printHeader() {
        System.out.print(PRINT_HEADER_NAME_COLUMN);

        IntStream.range(Frame.FIRST_FRAME_NUMBER, Frame.FINAL_FRAME_NUMBER + NUMBER_ONE)
                .boxed()
                .forEach(frameNumber -> System.out.printf(String.format(PRINT_HEADER_FRAME_NUMBER_COLUMN, frameNumber)));

        System.out.println();
    }

    private static void printBody(String name, Frames bowling) {
        System.out.print(String.format(PRINT_BODY_PLAYER_NAME_COLUMN, name));

        List<Frame> frames = bowling.getFrames();
        int frameNumber = bowling.getFrameNumber();
        List<String> results = getScores(frames, frameNumber);

        printProgressed(results);
        printBlankFrame(frames);

        System.out.println();
    }

    private static void printProgressed(List<String> results) {
        results.stream()
                .map(ResultView::printScore)
                .forEach(System.out::print);
    }

    private static void printBlankFrame(List<Frame> results) {
        IntStream.range(results.size(), Frame.FINAL_FRAME_NUMBER)
                .mapToObj(index -> printScore(null))
                .forEach(System.out::print);
    }

    private static String printScore(String result) {
        return (result == null) ? String.format(PRINT_RESULT_COLUMN, " ") : String.format(PRINT_RESULT_COLUMN, result);
    }

    private static String getScore(Scores scores, int frameNumber) {
        return IntStream.range(NUMBER_ZERO, scores.size())
                .mapToObj(index -> getScoreMark(scores, index, frameNumber))
                .collect(Collectors.joining(PRINT_SCORE_DELIMITER));
    }

    private static List<String> getScores(List<Frame> frames, int frameNumber) {
        return frames.stream()
                .map(Frame::getScores)
                .map((Scores scores) -> getScore(scores, frameNumber))
                .collect(Collectors.toList());
    }

    private static String getScoreMark(Scores scores, int index, int frameNumber) {
        Score score = scores.getScoreIndex(index);
        if ((frameNumber == Frame.FINAL_FRAME_NUMBER && score.isStrike())
                || (index == NUMBER_ZERO && score.isStrike())) {
            return ScoreMark.STRIKE.toString();
        }
        if (((index == NUMBER_ONE) && scores.isSpare())
                || (index == NUMBER_ONE && frameNumber == Frame.FINAL_FRAME_NUMBER) && scores.getBonus()) {
            return ScoreMark.SPARE.toString();
        }
        if (score.isGutter()) {
            return ScoreMark.GUTTER.toString();
        }
        return String.valueOf(score);
    }
}
