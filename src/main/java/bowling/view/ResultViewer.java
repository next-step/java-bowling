package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultViewer {
    private static final int SHOW_FRAME_NUMBER = 10;
    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String FRAME_FORMAT = "  %02d  |";
    private static final String NAME_FORMAT = "| %4s |";
    private static final String GAME_RESULT_FORMAT = "  %-3s |";
    private static final String GAME_RESULT_DELIMITER = "|";

    private final Game game;

    public ResultViewer(Game game) {
        this.game = game;
    }

    public void addScore(int hitCount) {
//        ResultViewer.showHead();
        record(game.hit(hitCount));
//        ResultViewer.showFramesBody();
//        ResultViewer.showResultFrames(game.getPlayerName(), game.hit(hitCount));
//        ResultViewer.showResultScores(game.getSumScores());
    }

    private void record(Frame frame) {
//        System.out.println(frame.toResults());
    }

    public List<Integer> getScores() {
        List<Integer> result = new ArrayList<>();

        List<Frame> frameSet = game.getFrames().stream()
                .filter(Frame::isFinish)
                .collect(Collectors.toList());

        setScore(result, frameSet);

        return result;
    }

    private void setScore(List<Integer> result, List<Frame> frames) {
        frames.stream()
                .forEach(frame ->
                        result.add(
                                addBeforeScore(result, sumScore(frame, frames))
                        )
                );
    }

    private int addBeforeScore(List<Integer> result, int score) {
        if (!result.isEmpty()) {
            score += result.get(result.size() - 1);
        }
        return score;
    }

    private int sumScore(Frame frame, List<Frame> frames) {
        Score score = frame.getScore();
        int nextIndex = frame.getNumber();

        while (score.canNextSum() && nextIndex < frames.size()) {
            score = sumMore(score, frames.get(nextIndex));
            nextIndex = nextIndex + 1;
        }

        return score.toInt();
    }

    private Score sumMore(Score score, Frame nextFrame) {
        if (nextFrame.isFinish()) {
            score = nextFrame.additionalScore(score);
        }
        return score;
    }


//    private void showResultFrames(String name, List<List<String>> frames) {
//        showFramesBody(name, frames);
//        System.out.println();
//    }
//
//    private String framesToString(int frameNumber, List<List<String>> frames) {
//        if (frames.size() < frameNumber) {
//            return String.format(GAME_RESULT_FORMAT, "");
//        }
//
//        return String.format(GAME_RESULT_FORMAT, scoresToString(frames.get(frameNumber - 1)));
//    }
//
//    private String scoresToString(List<String> results) {
//        return results.stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining(GAME_RESULT_DELIMITER));
//    }
//
//    private String scoresToString(int frameNumber, List<Integer> scores) {
//        if (scores.size() < frameNumber) {
//            return String.format(GAME_RESULT_FORMAT, "");
//        }
//
//        return String.format(GAME_RESULT_FORMAT, scores.get(frameNumber - 1));
//    }
//
//    private static void showHead() {
//        System.out.print(STAGE_PREFIX_STRING);
//
//        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
//                .forEach(frame -> System.out.printf(String.format(FRAME_FORMAT, frame)));
//
//        System.out.println();
//    }
//
//    private static String showFramesBody(String name, List<List<String>> frames) {
//        System.out.print(String.format(NAME_FORMAT, name));
//
//        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
//                .forEach(frame -> System.out.printf(framesToString(frame, frames)));
//    }
//
//    private static void showResultScores(List<Integer> scores) {
//        System.out.print(String.format(NAME_FORMAT, ""));
//
//        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
//                .forEach(frame -> System.out.printf(scoresToString(frame, scores)));
//
//        System.out.println();
//    }
}
