package bowling.dto;

import bowling.domain.BowlingGameFrame;
import bowling.domain.BowlingGameHitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameFrameDto {

    private final List<Integer> hits;
    private final List<BowlingGameHitResult> results;

    public BowlingGameFrameDto(List<Integer> hits, List<BowlingGameHitResult> results) {
        this.hits = hits;
        this.results = results;
    }

    public static BowlingGameFrameDto from(BowlingGameFrame frame) {
        List<Integer> hits = new ArrayList<>();
        List<BowlingGameHitResult> results = new ArrayList<>();
        IntStream.range(0, frame.size())
                .forEach(i -> {
                    hits.add(frame.get(i));
                    results.add(frame.getResult(i));
                });
        return new BowlingGameFrameDto(hits, results);
    }

    public List<Integer> getHits() {
        return hits;
    }

    public List<BowlingGameHitResult> getResults() {
        return results;
    }

}
