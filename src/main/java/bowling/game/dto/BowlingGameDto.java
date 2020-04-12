package bowling.domain.game.dto;

import bowling.domain.frame.domain.Frames;

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
