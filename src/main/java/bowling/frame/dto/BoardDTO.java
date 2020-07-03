package bowling.frame.dto;

import bowling.player.dto.PlayerDTO;


import java.util.List;

public class BoardDTO {

    private final PlayerDTO playerDTO;
    private final List<FrameDTO> boardDTO;

    public BoardDTO(PlayerDTO playerDTO, List<FrameDTO> boardDTO) {
        this.playerDTO = playerDTO;
        this.boardDTO = boardDTO;
    }

    public List<FrameDTO> getBoardDTO() {
        return boardDTO;
    }

    public PlayerDTO getPlayerDTO() {
        return playerDTO;
    }
}
