package bowling.dto;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FrameDto {

    private final List<HitDto> hits;
    private final boolean hasScore;
    private final int score;

    public FrameDto(List<HitDto> hits, boolean hasScore, int score) {
        this.hits = hits;
        this.hasScore = hasScore;
        this.score = score;
    }

    public static FrameDto from(Frame frame) {
        List<HitDto> hits = new ArrayList<>();
        IntStream.range(0, frame.countHits())
                .forEach(i -> hits.add(new HitDto(frame.getHitValue(i), frame.getState(i))));
        boolean hasScore = frame.hasScore();
        int score = frame.hasScore() ? frame.getScore() : 0;
        return new FrameDto(hits, hasScore, score);
    }

    public List<HitDto> getHits() {
        return hits;
    }

    public boolean isHasScore() {
        return hasScore;
    }

    public int getScore() {
        return score;
    }

}
