package bowling.domain.game.dto;

import bowling.domain.frame.dto.ScoreBoardAssembler;
import bowling.domain.game.BowlingGame;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGameAssembler {

    private BowlingGameAssembler() {
    }

    public static BowlingGameDTO assemble(BowlingGame game) {
        return game.getGame().stream()
                .map(scoreBoard -> ScoreBoardAssembler.assemble(scoreBoard))
                .collect(collectingAndThen(toList(), BowlingGameDTO::new));
    }
}
