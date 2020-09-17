package bowling.domain.frame.dto;

import bowling.domain.frame.ScoreBoard;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ScoreBoardAssembler {

    private ScoreBoardAssembler() {
    }

    public static ScoreBoardDTO assemble(ScoreBoard scoreBoard) {
        return scoreBoard.getFrames().stream()
                .map(FrameAssembler::assemble)
                .collect(collectingAndThen(toList(), ScoreBoardDTO::new));
    }
}
