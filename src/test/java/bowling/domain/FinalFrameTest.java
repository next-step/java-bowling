package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    public void setUp() {
        NormalFrame normalFrame = NormalFrame.first();

        for (int i = 0; i < 8; i++) {
            normalFrame.pitch(new Pitch(10));
            normalFrame = (NormalFrame) normalFrame.next();
        }

        normalFrame.pitch(new Pitch(10));
        finalFrame = (FinalFrame)normalFrame.next();
    }

    @Test
    @DisplayName("마지막 NormalFrame의 다음 프레임은 FinalFrame")
    void next_lastNormalFrame() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        for (int i = 0; i < 8; i++) {
            normalFrame.pitch(new Pitch(10));
            normalFrame = (NormalFrame) normalFrame.next();
        }

        normalFrame.pitch(new Pitch(10));
        Frame finalFrame = normalFrame.next();

        // then
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("마지막 프레임 - 스트라이크일 경우 보너스 투구 가능")
    void bonusPitch_byStrike() {
        // given when
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(5));

        // then
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 - 스페어일 경우 보너스 투구 가능")
    void bonusPitch_bySpare() {
        // given when
        finalFrame.pitch(new Pitch(2));
        finalFrame.pitch(new Pitch(8));
        finalFrame.pitch(new Pitch(7));

        // then
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 - 미스일 경우 보너스 투구 불가")
    void bonusPitch_byMiss() {
        // given when
        finalFrame.pitch(new Pitch(2));
        finalFrame.pitch(new Pitch(7));

        // then
        assertThat(finalFrame.isFinished()).isTrue();
        assertThatIllegalStateException()
                .isThrownBy(() -> finalFrame.pitch(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

}