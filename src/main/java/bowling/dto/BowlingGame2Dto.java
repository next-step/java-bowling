package bowling.dto;

public class BowlingGame2Dto {
    private final Frames2Dto frames;
    private final String playerName;

    private BowlingGame2Dto(Frames2Dto frames, String playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame2Dto of(Frames2Dto frames, String playerName) {
        return new BowlingGame2Dto(frames, playerName);
    }

    public Frames2Dto getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return playerName;
    }
}
