package bowling.game.dto;

import bowling.frame.domain.Frames;

public class BowlingGameDto {

    private final Frames game;

    public BowlingGameDto(Frames game) {
        this.game = game;
    }

    public Frames getFrames() {
        return game;
    }

    public int getCurrentFrame() {
        return game.getSize();
    }
}
