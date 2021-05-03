package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public class BowlingResult {
    private final Map<FrameNumber, FrameResult> frameResultsMap;

    public BowlingResult() {
        frameResultsMap = new HashMap<>();
    }

    public BowlingResult(List<FrameResult> frameResults) {
        this.frameResultsMap = new HashMap<>();

        FrameNumber frameNumber = new FrameNumber(1);
        for (FrameResult frameResult : frameResults) {
            frameResultsMap.put(frameNumber, frameResult);
            frameNumber = frameNumber.increase();
        }
    }

    public void add(FrameNumber frameNumber, PointSymbols pointSymbols, Score score) {
        Score aggregatedScore = prevAggregatedScore(frameNumber);
        FrameResult frameResult = new FrameResult(pointSymbols, aggregatedScore.add(score));
        frameResultsMap.put(frameNumber, frameResult);
    }

    private Score prevAggregatedScore(FrameNumber frameNumber) {
        if (frameNumber.equals(new FrameNumber(1))) {
            return Score.create(0);
        }

        return frameResultsMap.getOrDefault(frameNumber.decrease(), new FrameResult(Score.create(0))).aggregatedScore();
    }

    public List<FrameResult> results() {
        return Collections.unmodifiableList(frameResultsMapToList());
    }

    public FrameResult result(FrameNumber frameNumber) {
        if (!frameResultsMap.containsKey(frameNumber)) {
            return new FrameResult();
        }
        return frameResultsMap.get(frameNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingResult that = (BowlingResult) o;
        return Objects.equals(frameResultsMap, that.frameResultsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResultsMap);
    }

    private List<FrameResult> frameResultsMapToList() {
        return frameResultsMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> (FrameNumber.compare(entry1.getKey(), entry2.getKey())))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
