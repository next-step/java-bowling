package bowling.dto;

import bowling.domain.BowlingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameDto {

    private final List<FrameDto> frames;

    public BowlingGameDto(List<FrameDto> frames) {
        this.frames = frames;
    }

    public static BowlingGameDto from(BowlingGame game) {
        List<FrameDto> frames = new ArrayList<>();
        IntStream.range(0, BowlingGame.SIZE_OF_FRAMES)
                .forEach(i -> frames.add(FrameDto.from(game.get(i))));
        return new BowlingGameDto(frames);
    }

    public List<FrameDto> getFrames() {
        return frames;
    }

}
