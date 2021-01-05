package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreCalculatorFactoryTest {

    @DisplayName("마지막 프레임이면 Final Calculator 를 return 한다")
    @Test
    void finalCalculator(){
        assertThat(FrameScoreCalculatorFactory.create(true, Frame.Status.Miss ))
                .isInstanceOf(FrameScoreCalculator.Final.getClass());
    }

    @DisplayName("일반 프레임에서 Strike 를 치면 Strike Calculator 를 return 한다")
    @Test
    void strikeCalculator(){
        assertThat(FrameScoreCalculatorFactory.create(false, Frame.Status.Strike ))
                .isInstanceOf(FrameScoreCalculator.Strike.getClass());
    }

    @DisplayName("일반 프레임에서 Spare 를 치면 Spare Calculator 를 return 한다")
    @Test
    void spareCalculator(){
        assertThat(FrameScoreCalculatorFactory.create(false, Frame.Status.Spare ))
                .isInstanceOf(FrameScoreCalculator.Spare.getClass());
    }

    @DisplayName("일반 프레임에서 Miss 하면 Default Calculator 를 return 한다")
    @Test
    void defaultCalculator1(){
        assertThat(FrameScoreCalculatorFactory.create(false, Frame.Status.Miss ))
                .isInstanceOf(FrameScoreCalculator.Default.getClass());
    }

    @DisplayName("일반 프레임에서 Gutter 하면 Default Calculator 를 return 한다")
    @Test
    void defaultCalculator2(){
        assertThat(FrameScoreCalculatorFactory.create(false, Frame.Status.Gutter ))
                .isInstanceOf(FrameScoreCalculator.Default.getClass());
    }

}