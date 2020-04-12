package bowling.domain.game.domain;

import bowling.domain.game.dto.BowlingGameDto;

public class BowlingGameAssembler {

    private BowlingGameAssembler() {
    }

    public static BowlingGameDto assemble(BowlingGame bowlingGame) {
        return new BowlingGameDto(bowlingGame.getGame());
    }
}
