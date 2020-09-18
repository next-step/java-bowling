package bowling.view;

import bowling.domain.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultViewer {
    private static final int SHOW_FRAME_NUMBER = 10;
    private static final int SHOW_LAST_FRAME_NUMBER = SHOW_FRAME_NUMBER;

    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String FRAME_FORMAT = "  %02d  |";
    private static final String FRAME_LAST_FORMAT = "   %02d   |";
    private static final String NAME_FORMAT = "| %4s |";
    private static final String GAME_RESULT_FORMAT = "  %-3s |";
    private static final String GAME_RESULT_LAST_FORMAT = "  %-5s |";
    private static final String GAME_RESULT_DELIMITER = "|";

    private final String name;
    private final Map<Integer, Frame> status;

    public ResultViewer(String name) {
        this.name = name;
        this.status = new HashMap<>();
    }

    public void record(Frame frame) {
        status.put(frame.getNumber(), frame);
    }

    public void printing() {
        showStatus(getStatus());
        showResultScores(getScores());
    }

    public static void showHead() {
        System.out.print(STAGE_PREFIX_STRING);

        IntStream.rangeClosed(1, LastFrame.LAST_FRAME_NUMBER)
                .forEach(frame ->
                        System.out.printf(
                                String.format(
                                        frame != SHOW_LAST_FRAME_NUMBER ? FRAME_FORMAT : FRAME_LAST_FORMAT,
                                        frame
                                )
                        )
                );

        System.out.println();
    }

    public List<List<String>> getStatus() {
        return status.values()
                .stream()
                .map(Frame::toPins)
                .map(this::toStatus)
                .collect(Collectors.toList());
    }

    private List<String> toStatus(List<Pin> pins) {
        List<String> results = new ArrayList<String>() {{
            add(toResult(Pin.of(0), pins.get(0)));
        }};

        for (int index = 0; index < pins.size() - 1; index++) {
            results.add(toResult(pins.get(index), pins.get(index + 1)));
        }

        return results;
    }

    private String toResult(Pin prev, Pin current) {
        if (current.isStrike()) {
            return Result.STRIKE.toString();
        }

        if (current.isSpare(prev.getCount())) {
            return Result.SPARE.toString();
        }

        return String.valueOf(current.getCount());
    }

    private void showStatus(List<List<String>> frames) {
        System.out.print(String.format(NAME_FORMAT, name));

        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
                .forEach(frame -> System.out.printf(statusToString(frame, frames)));

        System.out.println();
    }

    private String statusToString(int frameNumber, List<List<String>> frames) {
        if (frames.size() < frameNumber) {
            return String.format(
                    frameNumber != SHOW_LAST_FRAME_NUMBER ? GAME_RESULT_FORMAT : GAME_RESULT_LAST_FORMAT,
                    "");
        }

        return String.format(
                frameNumber != SHOW_LAST_FRAME_NUMBER ? GAME_RESULT_FORMAT : GAME_RESULT_LAST_FORMAT,
                frameToString(frames.get(frameNumber - 1)));
    }

    private String frameToString(List<String> results) {
        return results.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(GAME_RESULT_DELIMITER));
    }

    public List<Integer> getScores() {
        List<Integer> result = new ArrayList<>();

        List<Frame> frameSet = status.values().stream()
                .filter(Frame::isFinish)
                .collect(Collectors.toList());

        setScore(result, frameSet);

        return result;
    }

    private void setScore(List<Integer> result, List<Frame> frames) {
        frames.forEach(frame -> result.add(addBeforeScore(result, sumScore(frame, frames))));
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

    private void showResultScores(List<Integer> scores) {
        System.out.print(String.format(NAME_FORMAT, ""));

        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
                .forEach(frame -> System.out.printf(frameToString(frame, scores)));

        System.out.println();
    }

    private String frameToString(int frameNumber, List<Integer> scores) {
        if (scores.size() < frameNumber) {
            return String.format(
                    frameNumber != SHOW_LAST_FRAME_NUMBER ? GAME_RESULT_FORMAT : GAME_RESULT_LAST_FORMAT,
                    "");
        }

        return String.format(
                frameNumber != SHOW_LAST_FRAME_NUMBER ? GAME_RESULT_FORMAT : GAME_RESULT_LAST_FORMAT,
                scores.get(frameNumber - 1));
    }

    public static Map<String, ResultViewer> makeResultMap(List<String> names) {
        Map<String, ResultViewer> result = new HashMap<>();

        for (String name : names) {
            result.put(name, new ResultViewer(name));
        }

        return result;
    }

    public static void printAll(List<String> names, Map<String, ResultViewer> resultViewerMap) {
        ResultViewer.showHead();

        for (String name : names) {
            resultViewerMap.get(name).printing();
        }
    }
}
