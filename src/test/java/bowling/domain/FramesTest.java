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
        assertThat(frames.lastFrameNumber()).isEqualTo(1);
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
        assertThat(frames.lastFrameNumber()).isEqualTo(3);
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
        frames.pitch(10);

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> frames.pitch(10))
                .withMessageMatching("마지막 프레임입니다.");
    }

    @Test
    @DisplayName("빈 프레임은 프레임 번호가 존재하지 않음")
    void lastFrameNumber_emptyFrame() {
        // given
        Frames frames = new Frames();

        // when then
        assertThatIllegalStateException()
                .isThrownBy(frames::lastFrameNumber)
                .withMessageMatching("프레임이 존재하지 않습니다. 투구를 실행해 주세요.");
    }

    @Test
    @DisplayName("다음 턴 번호조회 - 빈 프레임")
    void nextTurnNumber_emptyFrame() {
        // given
        Frames frames = new Frames();

        // when then
        assertThat(frames.nextTurnNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 턴 번호조회 - 종료된 프레임")
    void nextTurnNumber_finishedFrames() {
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
        frames.pitch(10);

        // when then
        assertThat(frames.isFinished()).isTrue();
        assertThat(frames.nextTurnNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("다음 턴 번호조회 - 마지막 프레임이 종료되지 않은 경우")
    void nextTurnNumber_lastFrameIsNotFinished() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(10);
        frames.pitch(2);

        // then
        assertThat(frames.isFinished()).isFalse();
        assertThat(frames.lastFrameNumber()).isEqualTo(frames.nextTurnNumber());
        assertThat(frames.nextTurnNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 턴 번호조회 - 마지막 프레임이 종료된 경우")
    void nextTurnNumber_lastFrameIsFinished() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(10);
        frames.pitch(2);
        frames.pitch(0);

        // then
        assertThat(frames.isFinished()).isFalse();
        assertThat(frames.lastFrameNumber()).isNotEqualTo(frames.nextTurnNumber());
        assertThat(frames.nextTurnNumber()).isEqualTo(3);
        assertThat(frames.lastFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("퍼펙트게임")
    void perfectGame() {
        // given
        Frames frames = new Frames();

        // when
        for (int i = 0; i < 12; i++) {
            frames.pitch(10);
        }

        // then
        assertThat(frames.totalScore()).isEqualTo(300);
    }

}