package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.score.FrameScore;
import com.jaeyeonling.bowling.domain.frame.state.FinishedFrameStateException;
import com.jaeyeonling.bowling.domain.frame.state.Gutter;
import com.jaeyeonling.bowling.domain.frame.state.Spare;
import com.jaeyeonling.bowling.domain.frame.state.Strike;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new FinalFrame();
    }

    @DisplayName("볼을 던진다.")
    @Test
    void bowl() {
        frame.bowl(KnockdownPins.MIN);
    }

    @DisplayName("거터 후 점수를 확인한다.")
    @Test
    void gutter() {
        // when
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MIN);
        final FrameScore frameScore = frame.getFrameScore();

        // then
        assertThat(frameScore).isEqualTo(Gutter.SCORE);
    }

    @DisplayName("스페어 후 점수를 확인한다.")
    @Test
    void spare() {
        // when
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MAX);
        final FrameScore frameScore = frame.getFrameScore();

        // then
        assertThat(frameScore).isEqualTo(FrameScore.UN_SCORE);
    }

    @DisplayName("미스 후 점수를 확인한다.")
    @Test
    void miss() {
        // when
        frame.bowl(KnockdownPins.valueOf(1));
        frame.bowl(KnockdownPins.valueOf(1));
        final FrameScore frameScore = frame.getFrameScore();

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(2));
    }

    @DisplayName("진행 중 프레임 종료되지 않음을 확인한다.")
    @Test
    void continueIsFinished() {
        // when
        frame.bowl(KnockdownPins.MIN);
        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isFalse();
    }

    @DisplayName("거터 후 프레임 종료를 확인한다.")
    @Test
    void gutterIsFinished() {
        // when
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MIN);
        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isTrue();
    }

    @DisplayName("스페어 미스 후 프레임 종료를 확인한다.")
    @Test
    void spareIsFinished() {
        // when
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MAX);
        frame.bowl(KnockdownPins.valueOf(5));

        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isTrue();
    }

    @DisplayName("스트라이크 후 프레임 종료가 안됐는지 확인한다.")
    @Test
    void strikeIsNotFinished() {
        // when
        frame.bowl(KnockdownPins.MAX);
        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isFalse();
    }

    @DisplayName("스트라이크 스트라이크 스트라이크 후 프레임 종료가 됐는지 확인한다.")
    @Test
    void strikeStrikeStrikeIsFinished() {
        // when
        frame.bowl(KnockdownPins.MAX);
        frame.bowl(KnockdownPins.MAX);
        frame.bowl(KnockdownPins.MAX);

        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isTrue();
    }

    @DisplayName("미스 후 프레임 종료를 확인한다.")
    @Test
    void missIsFinished() {
        // when
        frame.bowl(KnockdownPins.valueOf(1));
        frame.bowl(KnockdownPins.valueOf(1));
        final boolean finished = frame.isFinished();

        // then
        assertThat(finished).isTrue();
    }

    @DisplayName("거터로 프레임 종료 후 게임시 예외처리를 확인한다.")
    @Test
    void gutterThrowFinishedFrameStateException() {
        // given
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MIN);

        // when / then
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> frame.bowl(KnockdownPins.MIN));
    }

    @DisplayName("스페어로 스트라이크로 프레임 종료 후 게임시 예외처리를 확인한다.")
    @Test
    void spareSpareThrowFinishedFrameStateException() {
        // given
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MAX);

        frame.bowl(KnockdownPins.MAX);

        // when / then
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> frame.bowl(KnockdownPins.MIN));
    }

    @DisplayName("스트라이크 스트라이크 스트라이크로 프레임 종료 후 게임시 예외처리를 확인한다.")
    @Test
    void strikeStrikeStrikeThrowFinishedFrameStateException() {
        // given
        frame.bowl(KnockdownPins.MAX);
        frame.bowl(KnockdownPins.MAX);
        frame.bowl(KnockdownPins.MAX);

        // when / then
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> frame.bowl(KnockdownPins.MIN));
    }

    @DisplayName("미스 미스로 프레임 종료 후 게임시 예외처리를 확인한다.")
    @Test
    void missMissThrowFinishedFrameStateException() {
        // given
        frame.bowl(KnockdownPins.valueOf(1));
        frame.bowl(KnockdownPins.valueOf(1));

        // when / then
        assertThatExceptionOfType(FinishedFrameStateException.class)
                .isThrownBy(() -> frame.bowl(KnockdownPins.MIN));
    }

    @DisplayName("스페어와 거터를 점수 계산한다.")
    @Test
    void spareGutterCalculate() {
        // given
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MIN);

        // when
        final FrameScore frameScore = frame.calculateScore(Spare.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(10));
    }

    @DisplayName("스페어와 미스를 점수 계산한다.")
    @Test
    void spareMissCalculate() {
        // given
        frame.bowl(KnockdownPins.valueOf(5));
        frame.bowl(KnockdownPins.MIN);

        // when
        final FrameScore frameScore = frame.calculateScore(Spare.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(15));
    }

    @DisplayName("스페어와 스트라이크를 점수 계산한다.")
    @Test
    void spareStrikeCalculate() {
        // given
        frame.bowl(KnockdownPins.MAX);

        // when
        final FrameScore frameScore = frame.calculateScore(Spare.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(20));
    }

    @DisplayName("스트라이크와 거터와 거터를 점수 계산한다.")
    @Test
    void strikeGutterGutterCalculate() {
        // given
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.MIN);

        // when
        final FrameScore frameScore = frame.calculateScore(Strike.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(10));
    }

    @DisplayName("스트라이크와 거터와 미스를 점수 계산한다.")
    @Test
    void strikeGutterMissCalculate() {
        // given
        frame.bowl(KnockdownPins.MIN);
        frame.bowl(KnockdownPins.valueOf(5));

        // when
        final FrameScore frameScore = frame.calculateScore(Strike.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(15));
    }

    @DisplayName("스트라이크와 거터와 미스를 점수 계산한다.")
    @Test
    void strikeMissMissCalculate() {
        // given
        frame.bowl(KnockdownPins.valueOf(5));
        frame.bowl(KnockdownPins.valueOf(4));

        // when
        final FrameScore frameScore = frame.calculateScore(Strike.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("스트라이크와 스페어를 점수 계산한다.")
    @Test
    void strikeSpareCalculate() {
        // given
        frame.bowl(KnockdownPins.valueOf(5));
        frame.bowl(KnockdownPins.valueOf(5));

        // when
        final FrameScore frameScore = frame.calculateScore(Strike.SCORE);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(20));
    }

    @DisplayName("스트라이크와 스트라이크와 스트라이크를 점수 계산한다.")
    @Test
    void strikeStrikeStrikeCalculate() {
        // given
        frame.bowl(KnockdownPins.MAX);

        // when
        FrameScore frameScore = frame.calculateScore(Strike.SCORE);
        frameScore = frame.calculateScore(frameScore);

        // then
        assertThat(frameScore).isEqualTo(FrameScore.of(30));
    }

    @DisplayName("계산할 수 없는 값을 점수 시 무시한다.")
    @Test
    void ignoreCalculate() {
        // given
        frame.bowl(KnockdownPins.MIN);

        // when
        final FrameScore frameScore = frame.calculateScore(Gutter.SCORE);

        // then
        assertThat(frameScore).isEqualTo(Gutter.SCORE);
    }
}
