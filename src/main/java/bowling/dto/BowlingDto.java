package bowling.dto;

import bowling.domain.Bowling;
import bowling.domain.frame.Frame;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BowlingDto {
    private final String player;
    private final Map<Integer, Frame> frames;

    private BowlingDto(String player, Map<Integer, Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingDto valueOf(Bowling bowling) {
        Map<Integer, Frame> result = new LinkedHashMap<>();
        IntStream.range(0, 10)
                .forEach(i -> result.put(i, bowling
                        .getFrame(i)));
        return new BowlingDto(bowling.getPlayer().getUser(), result);
    }

    public String getPlayer() {
        return this.player;
    }

    public Map<Integer, Frame> getFrames() {
        return this.frames;
    }
}
