package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.*;
import step2.strategy.PitchesStrategy;

import static org.assertj.core.api.Assertions.assertThat;

public class OneFrameTest {

    @DisplayName("10개의 프레임 생성 테스트")
    @Test
    void makeFrame() {
        PitchesStrategy strategy = StrategyTest.TEST_RETURN_STRATEGY;
        Frames frames = BowlingGame.build();
        assertThat(frames.size()).isEqualTo(10);
    }

    @DisplayName("첫번째 프레임과 마지막 프레임 클래스 타입 테스트")
    @Test
    void checkFirstAndLastFrame() {
        Frames frames = BowlingGame.build();
        assertThat(frames.getHead()).isInstanceOf(NormalFrame.class);
        assertThat(frames.getTail()).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("투구 테스트")
    @Test
    void pitches() {
        Frames frames = BowlingGame.build();
        Frame head = frames.getHead();

        head.pitches(StrategyTest.T6);
        head.pitches(StrategyTest.T3);

        assertThat(head.getScore()).isEqualTo(9);
    }

}
