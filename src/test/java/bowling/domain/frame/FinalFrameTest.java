package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {

    @DisplayName("투구 전체 완료 후 스페어나 스트라이크가 없는 경우 remainedTryNo == 0 이다.")
    @Test
    void handleAfterTry() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(5);
        finalFrame.handleAfterTry(3);

        assertThat(finalFrame.getRestOfPins()).isEqualTo(2);
        assertThat(finalFrame.getRemainedTryNo()).isEqualTo(0);
    }

    @DisplayName("투구 중 스페어 나온 경우 remainedTryNo == 1이다.")
    @Test
    void handleAfterTry2() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(5);
        finalFrame.handleAfterTry(5);

        assertThat(finalFrame.getRestOfPins()).isEqualTo(10);
        assertThat(finalFrame.getRemainedTryNo()).isEqualTo(1);
    }

    @DisplayName("첫번째 투구가 스트라이크 나온 경우 remainedTryNo == 2이다.")
    @Test
    void handleAfterTry3() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(10);

        assertThat(finalFrame.getRestOfPins()).isEqualTo(10);
        assertThat(finalFrame.getRemainedTryNo()).isEqualTo(2);
    }

    @DisplayName("두번째 투구도 스트라이크 나온 경우 remainedTryNo == 1이다.")
    @Test
    void handelAfterTry3_2() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(10);
        finalFrame.handleAfterTry(10);

        assertThat(finalFrame.getRestOfPins()).isEqualTo(10);
        assertThat(finalFrame.getRemainedTryNo()).isEqualTo(1);
    }

    @DisplayName("투구 중 스트라이크나 스페어가 나와서 보너스 1회를 이미 받은 경우에는 또 보너스가 추가되지 않는다.")
    @Test
    void handleAfterTry4() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(10);
        finalFrame.handleAfterTry(10);

        assertThat(finalFrame.getRemainedTryNo()).isEqualTo(1);
    }

    @DisplayName("2번 던졌을 때 스트라이크나 스페어가 없으면 null 리턴한다.")
    @Test
    void askCurrentFrame() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(3);
        finalFrame.handleAfterTry(4);
        assertThat(finalFrame.moveToNextFrame()).isNull();
    }

    @DisplayName("2번 던졌을 때 스트라이크나 스페어가 나와서 현재 프레임을 유지한다.")
    @Test
    void askCurrentFrame3() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(4);
        finalFrame.handleAfterTry(6);
        assertThat(finalFrame.moveToNextFrame()).isEqualTo(finalFrame);
    }

    @DisplayName("1번 던졌을 때는 현재 프레임을 유지한다.")
    @Test
    void askCurrentFrame2() {
        Frame finalFrame = new FinalFrame(10);
        finalFrame.handleAfterTry(4);
        assertThat(finalFrame.moveToNextFrame()).isEqualTo(finalFrame);
    }

    @DisplayName("파이널의 스코어타입은 항상 Common이다.")
    @Test
    void scoreType() {
        FinalFrame finalFrame = new FinalFrame(1, 6, List.of(1, 3));
        FinalFrame finalFrame2 = new FinalFrame(1, 3, List.of(1, 9, 7));
        FinalFrame finalFrame3 = new FinalFrame(1, 0, List.of(10, 10, 10));

        assertAll(
                () -> assertThat(finalFrame.scoreType()).isEqualTo(ScoreType.COMMON),
                () -> assertThat(finalFrame2.scoreType()).isEqualTo(ScoreType.COMMON),
                () -> assertThat(finalFrame3.scoreType()).isEqualTo(ScoreType.COMMON)
        );
    }
}
