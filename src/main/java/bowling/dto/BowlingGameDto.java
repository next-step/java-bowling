package bowling.dto;

import bowling.domain.BowlingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameDto {

    private final List<BowlingGameFrameDto> frames;

    public BowlingGameDto(List<BowlingGameFrameDto> frames) {
        this.frames = frames;
    }

    public static BowlingGameDto from(BowlingGame game) {
        List<BowlingGameFrameDto> frames = new ArrayList<>();
        IntStream.range(0, BowlingGame.SIZE_OF_FRAMES)
                .forEach(i -> frames.add(BowlingGameFrameDto.from(game.get(i))));
        return new BowlingGameDto(frames);
    }

    public List<BowlingGameFrameDto> getFrames() {
        return frames;
    }

}
