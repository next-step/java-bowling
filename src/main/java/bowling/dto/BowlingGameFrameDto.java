package bowling.dto;

import bowling.domain.BowlingGameFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameFrameDto {

    private final List<Integer> hits;

    public BowlingGameFrameDto(List<Integer> hits) {
        this.hits = hits;
    }

    public static BowlingGameFrameDto from(BowlingGameFrame frame) {
        List<Integer> hits = new ArrayList<>();
        IntStream.range(0, frame.size())
                .forEach(i -> hits.add(frame.get(i)));
        return new BowlingGameFrameDto(hits);
    }

    public List<Integer> getHits() {
        return hits;
    }

}
