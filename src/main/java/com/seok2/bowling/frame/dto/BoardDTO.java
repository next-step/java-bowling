package com.seok2.bowling.frame.dto;

import com.seok2.bowling.user.dto.UserDTO;
import java.util.List;

public class BoardDTO {

    private final UserDTO userDTO;
    private final List<FrameDTO> boardDTO;

    public BoardDTO(UserDTO userDTO, List<FrameDTO> boardDTO) {
        this.userDTO = userDTO;
        this.boardDTO = boardDTO;
    }

    public List<FrameDTO> getBoardDTO() {
        return boardDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
