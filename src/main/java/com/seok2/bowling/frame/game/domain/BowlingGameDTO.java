package com.seok2.bowling.frame.game.domain;

import com.seok2.bowling.frame.dto.BoardDTO;
import java.util.List;

public class BowlingGameDTO {

    private final List<BoardDTO> boardDTOs;

    public BowlingGameDTO(List<BoardDTO> boardDTOs) {
        this.boardDTOs = boardDTOs;
    }

    public List<BoardDTO> getBoardDTOs() {
        return boardDTOs;
    }
}
