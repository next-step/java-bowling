package com.seok2.bowling.frame.dto;

import java.util.List;

public class BoardDTO {

    private final List<FrameDTO> boardDTO;

    public BoardDTO(List<FrameDTO> boardDTO) {
        this.boardDTO = boardDTO;
    }

    public List<FrameDTO> getBoardDTO() {
        return boardDTO;
    }
}
