package bowling.view;

import bowling.frame.BowlingBoard;
import bowling.frame.Frame;
import bowling.score.Score;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.global.utils.CommonConstant.NUMBER_ONE;
import static bowling.global.utils.CommonConstant.NUMBER_ZERO;

public class ResultView {

    private static final String PRINT_HEADER_NAME_COLUMN = "| NAME |";
    private static final String PRINT_BODY_PLAYER_NAME_COLUMN = "| %4s |";
    private static final String PRINT_HEADER_FRAME_NUMBER_COLUMN = "  %02d  |";
    private static final String PRINT_RESULT_COLUMN = "  %-3s |";
    private static final String PRINT_SCORE_DELIMITER = "|";

    private ResultView() {
    }

    public static void printGameBoard(BowlingBoard bowling) {
        printHeader();
        printBody(bowling);
        printFooter(getScores(bowling));
    }

    private static void printHeader() {
        System.out.print(PRINT_HEADER_NAME_COLUMN);
        printHeaderOfGameInfo();
        System.out.println();
    }

    private static void printHeaderOfGameInfo() {
        IntStream.rangeClosed(Frame.FIRST_FRAME_NUMBER, Frame.FINAL_FRAME_NUMBER)
                .forEach(frameNumber -> System.out.printf(PRINT_HEADER_FRAME_NUMBER_COLUMN, frameNumber));
    }

    private static void printBody(BowlingBoard bowling) {
        System.out.printf(PRINT_BODY_PLAYER_NAME_COLUMN, bowling.getBowlerName());

        List<String> scoreResults = printStateMark(bowling.getFrames());
        printProgressedFrameMark(scoreResults);
        printBlankFrameMark(bowling);

        System.out.println();
    }

    private static List<String> printStateMark(List<Frame> frames) {
        return frames.stream()
                .map(ResultView::getScoreResults)
                .collect(Collectors.toList());
    }

    private static String getScoreResults(Frame frame) {
        return String.join(PRINT_SCORE_DELIMITER, frame.getBowlResults());
    }

    private static void printProgressedFrameMark(List<String> scoreResults) {
        scoreResults.stream()
                .map(ResultView::printMarkBlankRatio)
                .forEach(System.out::print);
    }

    private static void printBlankFrameMark(BowlingBoard bowling) {
        IntStream.range(bowling.getFrames().size(), Frame.FINAL_FRAME_NUMBER)
                .mapToObj(result -> printMarkBlankRatio(null))
                .forEach(System.out::print);
    }

    private static String printMarkBlankRatio(String result) {
        if (result == null) {
            return String.format(PRINT_RESULT_COLUMN, " ");
        }
        return String.format(PRINT_RESULT_COLUMN, result);
    }

    private static void printFooter(List<Integer> scores) {
        System.out.printf(PRINT_BODY_PLAYER_NAME_COLUMN, Strings.EMPTY);
        printFooterOfGameScores(scores);
        System.out.println();
    }

    private static void printFooterOfGameScores(List<Integer> scores) {
        IntStream.rangeClosed(Frame.FIRST_FRAME_NUMBER, Frame.FINAL_FRAME_NUMBER)
                .forEach(frame -> System.out.print(printScoreResultRatio(frame, scores)));
    }

    private static List<Integer> getScores(BowlingBoard bowling) {
        List<Frame> frames = bowling.getFrames().stream()
                .filter(Frame::isFinish)
                .collect(Collectors.toList());

        return initScores(frames);
    }

    private static List<Integer> initScores(List<Frame> frames) {
        List<Integer> scores = new ArrayList<>();

        for (int i = NUMBER_ZERO; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            int sumScore = sumScore(frame, frames);
            scores.add(calculatePreviousScore(scores, sumScore));
        }
        return scores;
    }

    private static int sumScore(Frame frame, List<Frame> frames) {
        Score score = frame.getScore();
        int frameNumber = frame.getFrameNumber();

        while (!score.isCalculateScore() && frameNumber < frames.size()) {
            score = calculateAdditionalScore(score, frames.get(frameNumber));
            frameNumber = frameNumber + NUMBER_ONE;
        }
        return score.getFellPins();
    }

    private static Score calculateAdditionalScore(Score score, Frame nextFrame) {
        if (nextFrame.isFinish()) {
            score = nextFrame.calculateScore(score);
        }
        return score;
    }

    private static int calculatePreviousScore(List<Integer> scores, int score) {
        if (!scores.isEmpty()) {
            score += scores.get(scores.size() - NUMBER_ONE);
        }
        return score;
    }

    private static String printScoreResultRatio(int frameNumber, List<Integer> scores) {
        if (scores.size() < frameNumber) {
            return String.format(PRINT_RESULT_COLUMN, Strings.EMPTY);
        }
        return String.format(PRINT_RESULT_COLUMN, scores.get(frameNumber - NUMBER_ONE));
    }
}
