package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static bowling.model.Score.ofStrike;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {
    @Test
    public void 스트라이크_미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(new NormalRound());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(3);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(15, 20));
    }

    @Test
    public void 스페어_미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(new NormalRound());

        //when
        game.bowl(8);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

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
        BowlingGame game = new BowlingGame(new NormalRound());

        //when
        game.bowl(2);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(3);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(5));
    }

    @Test
    public void 거터_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(new NormalRound());

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(0));
    }

    @Test
    public void 트리플_스트라이크_점수계산() throws CannotBowlException {
        //given
        List<Integer> scores = new ArrayList<>();
        List<Round> rounds = new ArrayList<>();
        rounds.add(new FinalRound());
        BowlingGame game = new BowlingGame(scores, rounds);

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(30));
    }

    @Test
    public void 스트라이크_스페어_점수계산() throws CannotBowlException {
        //given
        List<Integer> scores = new ArrayList<>();
        List<Round> rounds = new ArrayList<>();
        rounds.add(new FinalRound());
        BowlingGame game = new BowlingGame(scores, rounds);

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(9);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(1);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(20));
    }

    @Test
    public void 스페어_스트라이크_점수계산() throws CannotBowlException {
        //given
        List<Integer> scores = new ArrayList<>();
        List<Round> rounds = new ArrayList<>();
        rounds.add(new FinalRound());
        BowlingGame game = new BowlingGame(scores, rounds);

        //when
        game.bowl(9);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(1);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(10);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(20));
    }
}
