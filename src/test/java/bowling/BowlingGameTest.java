package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
    @Test
    public void 한_프레임이_끝나면_frameNo가_증가한다() throws CannotBowlException {
        //given
        State state = new Strike();
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(new Score(10, 1));

        //when
        BowlingGame game = new BowlingGame();
        game.bowl(10);

        //then
        assertThat(game).isEqualTo(new BowlingGame(1, Arrays.asList(new NormalRound(state, scores))));
    }

    @Test
    public void 열번째_프레임을_마치면_게임을_종료한다() {
        //given
        BowlingGame game = new BowlingGame(11, Arrays.asList());

        //then
        assertTrue(game.isEndGame());
    }
}
