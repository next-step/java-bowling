package bowling.domain.frame;

import bowling.domian.frame.Board;
import bowling.domian.frame.NormalFrameResult;
import bowling.domian.state.Pins;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Strike;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    @DisplayName("Board에서 정상적으로 출력값 반환 확인")
    public void addResult() {
        Board board = Board.init();
        NormalFrameResult frameResult = NormalFrameResult.get(new Strike());
        NormalFrameResult secondResult = NormalFrameResult.get(new Miss(Pins.bowl(3), Pins.bowl(5)));

        board.addResult(frameResult, 1);
        board.addResult(secondResult, 2);

        List<String> bowlResultExpect = Arrays.asList(frameResult.getDesc(), secondResult.getDesc());
        List<Integer> scoreExpect = Arrays.asList(15, 20);
        assertThat(board.getBowlResults()).isEqualTo(bowlResultExpect);
        assertThat(board.getScoreResults()).isEqualTo(scoreExpect);
    }
}
