package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalFrameTest {

    @Test
    @DisplayName("스트라이크일 때, FinalFrame 생성")
    void strike() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch = Pitch.from(10);
        finalFrame.add(pitch);
        assertThat(finalFrame.getPitchSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크일 때, 보너스 게임 투구 후 종료")
    void strikeFinished() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(10);
        Pitch pitch2 = Pitch.from(10);
        Pitch pitch3 = Pitch.from(10);
        finalFrame.add(pitch1);
        finalFrame.add(pitch2);
        finalFrame.add(pitch3);

        assertThat(finalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스페어처리 후, 보너스 게임 투구 후 종료")
    void spareFinished() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        Pitch pitch3 = Pitch.from(5);
        finalFrame.add(pitch1);
        finalFrame.add(pitch2);
        finalFrame.add(pitch3);

        assertThat(finalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("보너스게임 없이 종료")
    void noBonusFinished() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);
        finalFrame.add(pitch1);
        finalFrame.add(pitch2);

        assertThat(finalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 후, 보너스게임 전에 종료되지 않음")
    void strikeNotFinished() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(10);
        Pitch pitch2 = Pitch.from(8);
        finalFrame.add(pitch1);
        finalFrame.add(pitch2);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫번째 투구가 스트라이크가 아님에도 2번째 투구까지의 합이 10을 초과할 경우, 예외 처리")
    void exceptScore() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(3);
        finalFrame.add(pitch1);

        assertThrows(IllegalArgumentException.class,
                () -> finalFrame.add(pitch2));
    }

    @Test
    @DisplayName("첫번째 투구 반환")
    void getFirstPitch() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        finalFrame.add(pitch1);

        assertThat(finalFrame.getFirstScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("두번째 투구 반환")
    void getSecondPitch() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        finalFrame.add(pitch1);
        finalFrame.add(pitch2);

        assertThat(finalFrame.getSecondScore()).isEqualTo(2);
    }

}
