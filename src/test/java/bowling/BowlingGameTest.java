package bowling;

import java.util.*;

import bowling.model.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    @Test
    public void 스트라이크_미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(new NormalFrame());
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
        BowlingGame game = new BowlingGame(new NormalFrame());
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
        assertThat(game.getScore()).isEqualTo(Arrays.asList(5));
    }

    @Test
    public void 미스_점수계산() throws CannotBowlException {
        //given
        BowlingGame game = new BowlingGame(new NormalFrame());

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
        BowlingGame game = new BowlingGame(new NormalFrame());

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Collections.emptyList());

        //when
        game.bowl(0);
        //then
        assertThat(game.getScore()).isEqualTo(Arrays.asList(0));
    }
}
