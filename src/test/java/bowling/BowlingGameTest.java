package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bowling.model.Score.ofStrike;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
    @Test
    public void 한_프레임이_끝나면_frameNo가_증가한다() throws CannotBowlException {
        //given
        State state = new Strike();
        LinkedList<Score> scores = new LinkedList<>();
        scores.add(ofStrike());
        NormalRound currentRound = new NormalRound(state, scores);

        LinkedList<Score> nextScores = new LinkedList<>();
        nextScores.add(ofStrike());
        nextScores.add(new Score());
        NormalRound nextRound = new NormalRound(new Ready(), nextScores);

        //when
        BowlingGame game = new BowlingGame();
        game.bowl(10);
        List<Integer> gameScore = game.getScore();

        //then
        assertThat(gameScore).isEqualTo(Arrays.asList());
        assertThat(game).isEqualTo(new BowlingGame(2, Arrays.asList(currentRound, nextRound)));
    }

    @Test
    public void 열번째_프레임을_마치면_게임을_종료한다() {
        //given
        BowlingGame game = new BowlingGame(11, Arrays.asList());

        //then
        assertTrue(game.isEndGame());
    }

    @Test
    public void 스트라이크_미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame();

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(3);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(15, 20));
    }

    @Test
    public void 스페어_미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame();

        //when
        game.bowl(8);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(3);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(13));

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(18));
    }

    @Test
    public void 미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame();

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(3);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(5));
    }

    @Test
    public void 거터_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame();

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(0));
    }

    @Test
    public void 트리플_스트라이크_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(10, Arrays.asList(new FinalRound()));

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(30));
    }

    @Test
    public void 스트라이크_스페어_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(10, Arrays.asList(new FinalRound()));

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(9);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(1);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(20));
    }

    @Test
    public void 스페어_스트라이크_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(10, Arrays.asList(new FinalRound()));

        //when
        game.bowl(9);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(1);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(20));
    }
}
