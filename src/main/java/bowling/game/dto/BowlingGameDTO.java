package bowling.game.dto;

import bowling.frame.dto.BoardDTO;
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
