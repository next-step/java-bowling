package com.seok2.bowling.frame.dto;

import com.seok2.bowling.user.dto.UserDTO;
import java.util.List;

public class BoardDTO {

    private final UserDTO user;
    private final List<FrameDTO> frames;

    public BoardDTO(UserDTO user, List<FrameDTO> frames) {
        this.user = user;
        this.frames = frames;
    }

    public UserDTO getUser() {
        return user;
    }

    public List<FrameDTO> getBoardDTO() {
        return frames;
    }
}
