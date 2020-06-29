package bowling.domain.game;

import bowling.domain.dto.BowlingGameDto;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.PitchScore;

public class SingleBowlingGame {

    private final Player player;
    private final Frames frames;

    private SingleBowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static SingleBowlingGame of(String playerName, Frames frames) {
        Player player = new Player(playerName);
        return new SingleBowlingGame(player, frames);
    }

    public void bowl(PitchScore pitchScore) {
        frames.bowl(pitchScore);
    }

    public boolean isCurrentFrameFinished() {
        return frames.isCurrentFrameFinished();
    }

    public void moveToNextFrame() {
        frames.moveToNextFrame();
    }

    public boolean hasNextTurn() {
        return frames.hasNextTurn();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public BowlingGameDto getFrameDto() {
        return new BowlingGameDto(player.getName(), frames.getScoreSignatureDtos(), frames.getFrameScores());
    }
}
