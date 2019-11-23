package com.seok2.bowling.game.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.seok2.bowling.frame.domain.BoardAssembler;
import com.seok2.bowling.game.dto.BowlingGameDTO;

public class BowlingGameAssembler {


    private BowlingGameAssembler() {
    }

    public static BowlingGameDTO assemble(BowlingGame game) {
        return game.getGame()
            .stream()
            .map(BoardAssembler::assemble)
            .collect(collectingAndThen(toList(), BowlingGameDTO::new));
    }


}