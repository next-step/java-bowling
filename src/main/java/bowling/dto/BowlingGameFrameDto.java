package bowling.dto;

import bowling.domain.frame.BowlingGameFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameFrameDto {

    private final List<BowlingGameHitDto> hits;
    private final boolean hasScore;
    private final int score;

    public BowlingGameFrameDto(List<BowlingGameHitDto> hits, boolean hasScore, int score) {
        this.hits = hits;
        this.hasScore = hasScore;
        this.score = score;
    }

    public static BowlingGameFrameDto from(BowlingGameFrame frame) {
        List<BowlingGameHitDto> hits = new ArrayList<>();
        IntStream.range(0, frame.countHits())
                .forEach(i -> hits.add(new BowlingGameHitDto(frame.getHit(i), frame.getState(i))));
        boolean hasScore = frame.hasScore();
        int score = frame.hasScore() ? frame.getScore() : 0;
        return new BowlingGameFrameDto(hits, hasScore, score);
    }

    public List<BowlingGameHitDto> getHits() {
        return hits;
    }

    public boolean isHasScore() {
        return hasScore;
    }

    public int getScore() {
        return score;
    }

}
