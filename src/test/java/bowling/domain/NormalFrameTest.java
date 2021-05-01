package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @Test
    @DisplayName("start 프레임 생성")
    void startFrame() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when then
        assertThat(startFrame.number()).isEqualTo(1);
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
        assertThat(startFrame.isSpare()).isTrue();
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

    @Test
    @DisplayName("다음 프레임 생성")
    void next() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(5));
        startFrame.pitch(new Pitch(3));
        Frame nextFrame = startFrame.next();

        // then
        assertThat(nextFrame.number()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 프레임 두 번 조회 시, 생성된 기존 프레임 반환")
    void next_createdFrame() {
        // given
        NormalFrame startFrame = NormalFrame.first();

        // when
        startFrame.pitch(new Pitch(5));
        startFrame.pitch(new Pitch(3));
        Frame nextFrame = startFrame.next();
        Frame nextFrame2 = startFrame.next();

        // then
        assertThat(nextFrame).isEqualTo(nextFrame2);
    }

    @Test
    @DisplayName("프레임이 종료되지 않은 경우, 다음 프레임을 생성하거나 조회할 수 없음")
    void next_frameIsNotFinished() {
        // given
        NormalFrame frameWithPitch = NormalFrame.first();
        NormalFrame frameWithoutPitch = NormalFrame.first();

        // when
        frameWithPitch.pitch(new Pitch(5));

        // then
        assertThatIllegalStateException()
                .isThrownBy(frameWithPitch::next)
                .withMessageMatching("종료되지 않은 프레임입니다. 다음 프레임을 시작할 수 없습니다.");
        assertThatIllegalStateException()
                .isThrownBy(frameWithoutPitch::next)
                .withMessageMatching("종료되지 않은 프레임입니다. 다음 프레임을 시작할 수 없습니다.");
    }

    @Test
    @DisplayName("스트라이크 점수판 출력")
    void scoreBoards_strike() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(10));

        // then
        assertThat(normalFrame.getScoreBoards().size()).isEqualTo(1);
        assertThat(normalFrame.getScoreBoards()).isEqualTo(Collections.singletonList("X"));
    }

    @Test
    @DisplayName("스페어 점수판 출력")
    void scoreBoards_spare() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(3));

        // then
        assertThat(normalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(normalFrame.getScoreBoards()).isEqualTo(Arrays.asList("7", "/"));
    }

    @Test
    @DisplayName("미스 점수판 출력")
    void scoreBoards_miss() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(2));

        // then
        assertThat(normalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(normalFrame.getScoreBoards()).isEqualTo(Arrays.asList("7", "2"));
    }

    @Test
    @DisplayName("거터 점수판 출력")
    void scoreBoards_gutter() {
        // given
        NormalFrame normalFrame = NormalFrame.first();
        NormalFrame normalFrame2 = NormalFrame.first();
        NormalFrame normalFrame3 = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(0));

        normalFrame2.pitch(new Pitch(0));
        normalFrame2.pitch(new Pitch(7));

        normalFrame3.pitch(new Pitch(0));
        normalFrame3.pitch(new Pitch(0));

        // then
        assertThat(normalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(normalFrame.getScoreBoards()).isEqualTo(Arrays.asList("7", "-"));

        assertThat(normalFrame2.getScoreBoards().size()).isEqualTo(2);
        assertThat(normalFrame2.getScoreBoards()).isEqualTo(Arrays.asList("-", "7"));

        assertThat(normalFrame3.getScoreBoards().size()).isEqualTo(2);
        assertThat(normalFrame3.getScoreBoards()).isEqualTo(Arrays.asList("-", "-"));
    }

    @Test
    @DisplayName("점수계산 - 프레임 미종료")
    void score_notFinished() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));

        // then
        assertThat(normalFrame.score()).isEqualTo(7);
    }

    @Test
    @DisplayName("점수계산 - 프레임 종료 및 다음 프레임 미시작")
    void score_finishedAndHasNotNext() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(2));

        // then
        assertThat(normalFrame.score()).isEqualTo(9);
    }

    @Test
    @DisplayName("점수계산 - 프레임 종료 및 다음 프레임 시작")
    void score_finishedAndHasNext() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(2));
        normalFrame.next();

        // then
        assertThat(normalFrame.score()).isEqualTo(9);
    }

    @Test
    @DisplayName("점수계산 - 프레임 종료 및 다음 프레임 투구")
    void score_finishedAndNextFramePitch() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(2));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(5));

        // then
        assertThat(normalFrame.score()).isEqualTo(9);
    }
    
    @Test
    @DisplayName("점수계산 - 스페어에 의한 보너스점수")
    void score_spareBonus() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(7));
        normalFrame.pitch(new Pitch(3));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(4));

        // then
        assertThat(normalFrame.score()).isEqualTo(14);
    }
    
    @Test
    @DisplayName("점수계산 - 스트라이크에 의한 보너스점수")
    void score_strikeBonus() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(10));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(2));
        next.pitch(new Pitch(7));

        // then
        assertThat(normalFrame.score()).isEqualTo(19);
    }

    @Test
    @DisplayName("더블 스트라이크 보너스점수")
    void doubleStrike() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(10));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(10));

        Frame next2 = next.next();
        next2.pitch(new Pitch(7));

        // then
        assertThat(normalFrame.score()).isEqualTo(27);
    }

    @Test
    @DisplayName("트리플 스트라이크 보너스점수")
    void tripleStrike() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(10));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(10));

        Frame next2 = next.next();
        next2.pitch(new Pitch(10));

        // then
        assertThat(normalFrame.score()).isEqualTo(30);
    }
    
    @Test
    @DisplayName("스트라이크 보너스는 최대 3프레임까지만 적용")
    void limitStrikeBonus() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.pitch(new Pitch(10));

        Frame next = normalFrame.next();
        next.pitch(new Pitch(10));

        Frame next2 = next.next();
        next2.pitch(new Pitch(10));

        Frame next3 = next2.next();
        next3.pitch(new Pitch(6));

        // then
        assertThat(normalFrame.score()).isEqualTo(30);
    }

}