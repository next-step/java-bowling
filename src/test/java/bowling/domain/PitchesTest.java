package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class PitchesTest {

    @Test
    @DisplayName("핀 처리횟수 계산 - 투구실행을 하지 않은 경우")
    void pinDownCount_empty() {
        // given
        Pitches pitches = new Pitches();

        // when then
        assertThat(pitches.pinDownCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("핀 처리횟수 계산 - 투구를 실행한 경우")
    void pinDownCount() {
        // given
        Pitches pitches = new Pitches();
        pitches.add(new Pitch(1));
        pitches.add(new Pitch(9));

        // when then
        assertThat(pitches.pinDownCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("투구횟수 계산")
    void count() {
        // given
        Pitches pitchesNonPitch = new Pitches();
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(1));
        pitches.add(new Pitch(9));

        // then
        assertThat(pitchesNonPitch.count()).isEqualTo(0);
        assertThat(pitches.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("스트라이크")
    void strike() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(10));

        // then
        assertThat(pitches.isStrike()).isTrue();
    }

    @Test
    @DisplayName("Miss")
    void miss() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(3));
        pitches.add(new Pitch(2));

        // then
        assertThat(pitches.isFinished()).isTrue();
        assertThat(pitches.isMiss()).isTrue();
    }

    @Test
    @DisplayName("스페어 갯수")
    void spare() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(7));

        // then
        assertThat(pitches.spare()).isEqualTo(3);
    }

    @Test
    @DisplayName("투구 실행 - null 입력")
    void pitch_null() {
        // given
        Pitches pitches = new Pitches();

        // when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pitches.add(null))
                .withMessageMatching("투구 정보를 입력해 주세요.");
    }

    @Test
    @DisplayName("핀 처리 횟수가 남은 횟수를 초과할 때")
    void pitch_greaterThanSpareCount() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(3));

        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pitches.add(new Pitch(8)))
                .withMessageMatching("핀 처리횟수가 남은 핀수를 초과합니다. 투구정보를 확인해 주세요.");
    }

    @Test
    @DisplayName("종료된 프레임 - 스트라이크로 인한 종료")
    void pitch_finishedByStrike() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(10));

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> pitches.add(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

    @Test
    @DisplayName("종료된 프레임 - 스페어처리로 인한 종료")
    void pitch_finishedBySpare() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(7));
        pitches.add(new Pitch(3));

        // then
        assertThatIllegalStateException()
                .isThrownBy(() -> pitches.add(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

    @Test
    @DisplayName("스트라이크 점수판 출력")
    void scoreBoards_strike() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(10));

        // then
        assertThat(pitches.getScoreBoards().size()).isEqualTo(1);
        assertThat(pitches.getScoreBoards()).isEqualTo(Collections.singletonList("X"));
    }

    @Test
    @DisplayName("스페어 점수판 출력")
    void scoreBoards_spare() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(7));
        pitches.add(new Pitch(3));

        // then
        assertThat(pitches.getScoreBoards().size()).isEqualTo(2);
        assertThat(pitches.getScoreBoards()).isEqualTo(Arrays.asList("7", "/"));
    }

    @Test
    @DisplayName("미스 점수판 출력")
    void scoreBoards_miss() {
        // given
        Pitches pitches = new Pitches();

        // when
        pitches.add(new Pitch(7));
        pitches.add(new Pitch(2));

        // then
        assertThat(pitches.getScoreBoards().size()).isEqualTo(2);
        assertThat(pitches.getScoreBoards()).isEqualTo(Arrays.asList("7", "2"));
    }

    @Test
    @DisplayName("거터 점수판 출력")
    void scoreBoards_gutter() {
        // given
        Pitches pitches = new Pitches();
        Pitches pitches2 = new Pitches();
        Pitches pitches3 = new Pitches();

        // when
        pitches.add(new Pitch(7));
        pitches.add(new Pitch(0));

        pitches2.add(new Pitch(0));
        pitches2.add(new Pitch(7));

        pitches3.add(new Pitch(0));
        pitches3.add(new Pitch(0));

        // then
        assertThat(pitches.getScoreBoards().size()).isEqualTo(2);
        assertThat(pitches.getScoreBoards()).isEqualTo(Arrays.asList("7", "-"));

        assertThat(pitches2.getScoreBoards().size()).isEqualTo(2);
        assertThat(pitches2.getScoreBoards()).isEqualTo(Arrays.asList("-", "7"));

        assertThat(pitches3.getScoreBoards().size()).isEqualTo(2);
        assertThat(pitches3.getScoreBoards()).isEqualTo(Arrays.asList("-", "-"));
    }
}