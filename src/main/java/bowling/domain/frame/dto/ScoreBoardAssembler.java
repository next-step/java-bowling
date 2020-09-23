package bowling.domain.frame.dto;

import bowling.domain.frame.ScoreBoard;
import bowling.domain.user.dto.UserAssembler;

public class ScoreBoardAssembler {

    private ScoreBoardAssembler() {
    }

    public static ScoreBoardDTO assemble(ScoreBoard scoreBoard) {
        return new ScoreBoardDTO(UserAssembler.assemble(scoreBoard.getUser()), scoreBoard.getFrames());
    }
}
