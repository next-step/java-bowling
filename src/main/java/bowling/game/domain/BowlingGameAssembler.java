package bowling.game.domain;

import bowling.game.dto.BowlingGameDto;

public class BowlingGameAssembler {

    private BowlingGameAssembler() {
    }

    public static BowlingGameDto assemble(BowlingGame bowlingGame) {
        return new BowlingGameDto(bowlingGame.getGame());
    }
}
