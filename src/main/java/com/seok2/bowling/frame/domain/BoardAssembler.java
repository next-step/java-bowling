package com.seok2.bowling.frame.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.seok2.bowling.frame.dto.BoardDTO;
import com.seok2.bowling.user.domain.UserAssembler;

public class BoardAssembler {

    private BoardAssembler() {
    }

    public static BoardDTO assemble(Board board) {
        return board.getFrames().stream()
            .map(FrameAssembler::assemble)
            .collect(collectingAndThen(toList(),
                boardDTO -> new BoardDTO(UserAssembler.assemble(board.getUser()), boardDTO)));
    }

}
