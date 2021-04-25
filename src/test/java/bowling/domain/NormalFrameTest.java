package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @Test
    @DisplayName("start 프레임 생성")
    void startFrame() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when then
        assertThat(1).isEqualTo(startFrame.getNumber());
    }

    @Test
    @DisplayName("투구 실행 - null 입력")
    void pitch_null() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> startFrame.pitch(null))
                .withMessageMatching("투구 정보를 입력해 주세요.");
    }

    @Test
    @DisplayName("핀 처리 횟수가 남은 횟수를 초과할 때")
    void pitch_greaterThanSpareCount() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(3));

        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> startFrame.pitch(new Pitch(8)))
                .withMessageMatching("핀 처리횟수가 남은 핀수를 초과합니다. 투구정보를 확인해 주세요.");
    }

    @Test
    @DisplayName("종료된 프레임")
    void finished() {
        // given
        NormalFrame strikeFrame = NormalFrame.first();
        NormalFrame spareFrame = NormalFrame.first();
        NormalFrame maxPitchFrame = NormalFrame.first();
        NormalFrame unFinishedFrame = NormalFrame.first();

        // when
        strikeFrame.pitch(new Pitch(10));
        spareFrame.pitch(new Pitch(7));
        spareFrame.pitch(new Pitch(3));
        maxPitchFrame.pitch(new Pitch(1));
        maxPitchFrame.pitch(new Pitch(0));
        unFinishedFrame.pitch(new Pitch(5));

        // then
        assertAll(
                () -> assertThat(strikeFrame.isFinished()).isTrue(),
                () -> assertThat(spareFrame.isFinished()).isTrue(),
                () -> assertThat(maxPitchFrame.isFinished()).isTrue(),
                () -> assertThat(unFinishedFrame.isFinished()).isFalse()
        );
    }

    @Test
    @DisplayName("종료된 프레임 - 스트라이크로 인한 종료")
    void pitch_finishedByStrike() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(10));

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> startFrame.pitch(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

    @Test
    @DisplayName("종료된 프레임 - 스페어처리로 인한 종료")
    void pitch_finishedBySpare() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(7));
        startFrame.pitch(new Pitch(3));

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> startFrame.pitch(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

    @Test
    @DisplayName("종료된 프레임 - 투구횟수 초과")
    void pitch_finishedByPitchCount() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(5));
        startFrame.pitch(new Pitch(2));

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> startFrame.pitch(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

}