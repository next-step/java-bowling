package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bowling.domain.score.Score;
import bowling.domain.state.BowlingPin;

public class FinalFrameTest {

    private Frame finalFrame;

    @BeforeEach
    void init() {
        finalFrame = FinalFrame.of(10);
    }

    @Test
    void 생성_테스트() {
        // when & then
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
        assertThatCode(() -> FinalFrame.of(10)).doesNotThrowAnyException();
    }

    @Test
    void 투구_테스트() {
        // when
        finalFrame.bowl(BowlingPin.of(3));
        // then
        assertThat(finalFrame.isDone()).isFalse();
    }

    @Test
    void 투구_테스트_2() {
        // when
        finalFrame.bowl(BowlingPin.of(3));
        finalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(finalFrame.isDone()).isTrue();
    }

    @Test
    void 보너스_투구_스트라이크_테스트() {
        // when
        finalFrame.bowl(BowlingPin.of(10));
        finalFrame.bowl(BowlingPin.of(7));
        finalFrame.bowl(BowlingPin.of(2));

        // then
        assertThat(finalFrame.isDone()).isTrue();
    }

    @Test
    void 보너스_투구_스페어_테스트() {
        // when
        finalFrame.bowl(BowlingPin.of(3));
        finalFrame.bowl(BowlingPin.of(7));
        finalFrame.bowl(BowlingPin.of(2));

        // then
        assertThat(finalFrame.isDone()).isTrue();
    }

    @Test
    void 점수_반환_테스트() {
        // when
        finalFrame.bowl(BowlingPin.of(5));
        finalFrame.bowl(BowlingPin.of(3));
        // then
        assertThat(finalFrame.score()).isEqualTo(Score.of(8));
    }

    @Test
    void 점수_반환_추가_점수_테스트() {
        // when
        finalFrame.bowl(BowlingPin.of(5));
        finalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(finalFrame.score()).isEqualTo(Score.ofProgress());
    }

    @Test
    void 점수_반환_추가_점수_테스트_2() {
        // when
        finalFrame.bowl(BowlingPin.of(10));
        finalFrame.bowl(BowlingPin.of(5));
        finalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(finalFrame.score()).isEqualTo(Score.of(20, 3));
    }
}
