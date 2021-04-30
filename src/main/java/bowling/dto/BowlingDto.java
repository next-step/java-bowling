package bowling.dto;

import bowling.domain.Bowling;
import bowling.domain.frame.Scores;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BowlingDto {
    private final String player;
    private final Map<Integer, Scores> frames;

    private BowlingDto(String player, Map<Integer, Scores> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingDto valueOf(Bowling bowling) {
        Map<Integer, Scores> result = new LinkedHashMap<>();
        IntStream.range(0, 10)
                .forEach(i -> result.put(i, bowling
                        .getFrame(i)));
        return new BowlingDto(bowling.getPlayer().getUser(), result);
    }

    public String getPlayer() {
        return this.player;
    }

    public Map<Integer, Scores> getFrames() {
        return this.frames;
    }
}
