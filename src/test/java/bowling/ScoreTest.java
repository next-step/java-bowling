package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static bowling.model.Score.*;
import bowling.model.Score;

public class ScoreTest {
    @Test
    public void 스트라이크는_다음_2번의_투구까지_점수를_합산한다() {
        //given
        int secondPin = 10;
        int thirdPin = 10;

        //when
        Score strike = ofStrike();
        //then
        assertThat(strike).isEqualTo(new Score(10, 2));

        //when
        Score secondScore = strike.bowl(secondPin);
        //then
        assertFalse(secondScore.canCalculateScore());
        assertThat(secondScore).isEqualTo(new Score(20, 1));

        //when
        Score thirdScore = secondScore.bowl(thirdPin);
        //then
        assertTrue(thirdScore.canCalculateScore());
        assertThat(thirdScore).isEqualTo(new Score(30, 0));
        assertThat(thirdScore.getScore()).isEqualTo(30);
    }

    @Test
    public void 스페어는_다음_1번의_투구까지_점수를_합산한다() {
        //given
        int secondPin = 10;

        //when
        Score spare = ofSpare();
        //then
        assertThat(spare).isEqualTo(new Score(10, 1));

        //when
        Score result = spare.bowl(secondPin);
        //then
        assertTrue(result.canCalculateScore());
        assertThat(result).isEqualTo(new Score(20, 0));
        assertThat(result.getScore()).isEqualTo(20);
    }

//    @Test
//    public void 미스는_현재_투구까지_점수를_합산한다() {
//        //when
//        Score miss = ofMiss(4);
//
//        //then
//        assertTrue(miss.canCalculateScore());
//        assertThat(miss).isEqualTo(new Score(4, 0));
//        assertThat(miss.getScore()).isEqualTo(4);
//    }


}
