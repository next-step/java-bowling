package bowling.dto;

import bowling.domain.BowlingGameFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameFrameDto {

    private final List<BowlingGameHitDto> hits;

    public BowlingGameFrameDto(List<BowlingGameHitDto> hits) {
        this.hits = hits;
    }

    public static BowlingGameFrameDto from(BowlingGameFrame frame) {
        List<BowlingGameHitDto> hits = new ArrayList<>();
        IntStream.range(0, frame.countHits())
                .forEach(i -> hits.add(new BowlingGameHitDto(frame.getHit(i), frame.getResult(i))));
        return new BowlingGameFrameDto(hits);
    }

    public List<BowlingGameHitDto> getHits() {
        return hits;
    }

}
