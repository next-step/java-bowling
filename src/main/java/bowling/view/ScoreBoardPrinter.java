package bowling.view;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Score;
import bowling.engine.ScoreBoard;
import bowling.engine.Sequence;
import bowling.engine.Shots;

import static bowling.view.OutputView.LINE;

public class ScoreBoardPrinter {
    private static final String EMPTY = LINE + "      ";
    private static final String SCORE_FORMAT = LINE + "  %-3s ";
    private static final String FINAL_SCORE_FORMAT = LINE + " %-5s";

    private static final int NORMAL_SCORE_LENGTH = 3;
    private static final int SIZE_SEQUENCE_DIFF = 1;
    private static final int HEAD = 0;

    private final ScoreBoard scoreBoard;

    public ScoreBoardPrinter(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void printByFrame(Consumer<Integer> printBoardFunc) {
        IntStream.range(HEAD, Sequence.LAST)
                .forEach(printBoardFunc::accept);
        System.out.println(LINE);
    }

    public void printShotResult(int sequence) {
        Optional<Frame> frame = scoreBoard.elementOfOptional(sequence);
        String shotResults = frame
                .map(Frame::result)
                .map(Result::shots)
                .map(this::parseFrameScore)
                .map(this::formatFrameBoard)
                .orElse(EMPTY);
        System.out.print(shotResults);
    }

    public void printScore(int sequence) {
        if (scoreBoard.empty(sequence) || scoreBoard.remainBonuses(sequence)) {
            System.out.print(EMPTY);
            return;
        }

        int score = scoreBoard.stream()
                .limit(sequence + SIZE_SEQUENCE_DIFF)
                .map(Frame::result)
                .map(Result::score)
                .map(Score::toInt)
                .reduce(HEAD, Integer::sum);

        System.out.print(formatFrameBoard(String.valueOf(score)));
    }

    private String formatFrameBoard(String score) {
        if (score.length() > NORMAL_SCORE_LENGTH) {
            return String.format(FINAL_SCORE_FORMAT, score);
        }

        return String.format(SCORE_FORMAT, score);
    }

    public String parseFrameScore(Shots shots) {
        return shots.stream()
                .map(ShotScore::of)
                .map(ShotScore::toString)
                .collect(Collectors.joining(LINE));
    }
}
