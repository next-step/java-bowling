package bowling.dto;

import bowling.domain.PlayerName;
import bowling.domain.frames.FrameViewDto;
import bowling.domain.frames.FramesImpl;

import java.util.stream.Stream;

public class BowlingGameDto {
    private final FramesImpl frames;
    private final PlayerName playerName;

    private BowlingGameDto(FramesImpl frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGameDto of(FramesImpl frames, PlayerName playerName) {
        return new BowlingGameDto(frames, playerName);
    }

    public Stream<FrameViewDto> framesViewDtoStream() {
        return frames.viewDtoStream();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
