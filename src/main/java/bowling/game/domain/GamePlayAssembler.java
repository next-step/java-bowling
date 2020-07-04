package bowling.game.domain;

import bowling.frame.domain.BoardAssembler;
import bowling.game.dto.BowlingGameDTO;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GamePlayAssembler {

    public static BowlingGameDTO assemble(GamePlay gamePlay) {
        return gamePlay.getGame()
                .stream()
                .map(BoardAssembler::assemble)
                .collect(collectingAndThen(toList(), BowlingGameDTO::new));

    }
}
