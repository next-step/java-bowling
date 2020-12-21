package bowling.view;

import bowling.Frames;
import bowling.domain.Frame;
import bowling.domain.FrameStatus;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class GameStatus {
    private final Frames game;

    public GameStatus(Frames game) {
        this.game = game;
    }

    public String getAllFrameStatus() {
        return formatLine(game.getPlayerName(), getFrameStatus());
    }

    public String getAllScore() {
        return formatLine("   ", getFrameScore());
    }

    private String formatLine(String rowHeader, String rowBody) {
        return "|  " + rowHeader + " |" + rowBody + "|";
    }

    private String getFrameScore() {
        return formatScore(makeFrameScore());
    }

    private String getFrameStatus() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getFrameStatus).orElse(null))
                .map(this::toStringFrameStatus)
                .map(this::formatString)
                .collect(joining("|"));
    }

    private List<Integer> makeFrameScore() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getScore))
                .map(score -> score.map(Score::toInt).orElse(null))
                .collect(accumulateScore());
    }

    private Collector<Integer, List<Integer>, List<Integer>> accumulateScore() {
        return Collector.of(ArrayList::new, this::accumulate, this::addAll);
    }

    private List<Integer> addAll(List<Integer> listA, List<Integer> listB) {
        listA.addAll(listB);
        return listA;
    }

    private void accumulate(List<Integer> result, Integer score) {
        if (result.isEmpty()) {
            result.add(score);
            return;
        }

        if (score != null) {
            result.add(result.get(result.size() - 1) + score);
            return;
        }
        result.add(score);
    }

    private String formatScore(List<Integer> scores) {
        return scores.stream()
                .map(this::toStringScore)
                .map(this::formatString)
                .collect(joining("|"));
    }

    private String formatString(String score) {
        return String.format("  %-3s ", score);
    }

    private String toStringFrameStatus(FrameStatus frameStatus) {
        if (frameStatus == null) {
            return "";
        }
        return new FrameStatusView(frameStatus).apply();
    }

    private String toStringScore(Integer score) {
        if (score == null) {
            return "";
        }
        return score.toString();
    }
}
