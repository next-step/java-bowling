package bowling.dto;

public class PlayerDto {
    private final FramesDto frames;
    private final String playerName;

    private PlayerDto(FramesDto frames, String playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static PlayerDto of(FramesDto frames, String playerName) {
        return new PlayerDto(frames, playerName);
    }

    public FramesDto getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return playerName;
    }
}
