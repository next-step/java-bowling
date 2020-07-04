package bowling.frame.domain;

import bowling.frame.dto.BoardDTO;
import bowling.player.domain.PlayerAssembler;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BoardAssembler {

    private BoardAssembler() {
        // block
    }

    public static BoardDTO assemble(Board board) {
        return board.getFrames().stream()
                .map(FrameAssembler::assemble)
                .collect(collectingAndThen(toList(), boardDTO -> new BoardDTO(
                                PlayerAssembler.assemble(
                                        board.getPlayer()), boardDTO
                        ))
                );
    }

}
