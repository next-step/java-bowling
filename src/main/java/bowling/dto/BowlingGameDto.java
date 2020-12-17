package bowling.dto;

import java.util.stream.Stream;

public class BowlingGameDto {
    private final FramesDto frames;
    private final String playerName;

    private BowlingGameDto(FramesDto frames, String playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGameDto of(FramesDto frames, String playerName) {
        return new BowlingGameDto(frames, playerName);
    }

    public Stream<FrameDto> framesViewDtoStream() {
        return frames.viewDtoStream();
    }

    public String getPlayerName() {
        return playerName;
    }
}
