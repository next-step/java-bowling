package bowling.dto;

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

    public FramesDto getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return playerName;
    }
}
