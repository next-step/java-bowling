package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FramesTest {

    @Test
    @DisplayName("첫 투구")
    void startPitch() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(5);

        // then
        assertThat(1).isEqualTo(frames.lastFrameNumber());
    }

    @Test
    @DisplayName("다음 투구")
    void nextPitch() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(5);
        frames.pitch(5);
        frames.pitch(3);
        frames.pitch(2);
        frames.pitch(7);

        // then
        assertThat(3).isEqualTo(frames.lastFrameNumber());
    }

    @Test
    @DisplayName("게임 종료")
    void finishGame() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);

        // then
        assertThat(frames.isFinished()).isTrue();
    }

    @Test
    @DisplayName("마지막 투구 후 추가투구 불가")
    void pitch_afterFinish() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);
        frames.pitch(10);

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> frames.pitch(10))
                .withMessageMatching("마지막 프레임입니다.");
    }
}