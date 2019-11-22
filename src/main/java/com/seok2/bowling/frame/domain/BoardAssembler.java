package com.seok2.bowling.frame.domain;

import com.seok2.bowling.frame.dto.BoardDTO;
import com.seok2.bowling.frame.dto.FrameDTO;
import com.seok2.bowling.user.domain.UserAssembler;
import java.util.ArrayList;
import java.util.List;

public class BoardAssembler {

    private BoardAssembler() {
    }

    public static BoardDTO assemble(Board board) {
        List<FrameDTO> frames = new ArrayList<>();
        Frame current = board.getFirst();
        frames.add(FrameAssembler.assemble(current));
        while (current.hasNext()) {
            current = current.next();
            frames.add(FrameAssembler.assemble(current));
        }
        return new BoardDTO(UserAssembler.assemble(board.getUser()), frames);
    }

}
