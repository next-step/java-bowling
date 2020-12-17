package bowling.dto;

import bowling.domain.PlayerName;

import java.util.stream.Stream;

public class BowlingGameDto {
    private final FramesDto frames;
    private final PlayerName playerName;

    private BowlingGameDto(FramesDto frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGameDto of(FramesDto frames, PlayerName playerName) {
        return new BowlingGameDto(frames, playerName);
    }

    public Stream<FrameDto> framesViewDtoStream() {
        return frames.viewDtoStream();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
