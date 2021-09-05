package bowling.domain;

import bowling.exception.OverTheMaxPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NormalPitchTest {

    @DisplayName("bowl 테스트")
    @Test
    void bowlTest() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(4));

        assertThat(pitch.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("exception 테스트(두핀이 10이 넘었을 때)")
    @Test
    void bowlExceptionTest() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(4));

        assertThatExceptionOfType(OverTheMaxPinsException.class)
                .isThrownBy(() -> {
                    pitch.bowl(Pins.of(7));
                }).withMessageMatching("두 핀의 합이 10이 넘습니다.");
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNotFirstFinish() {
        Pitch pitch = new NormalPitch();

        assertThat(pitch.isFinish()).isFalse();
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(4));
        pitch.bowl(Pins.of(3));
        assertThat(pitch.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(10));
        assertThat(pitch.isFinish()).isTrue();

    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrike2Finish() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));
        assertThat(pitch.isFinish()).isTrue();

    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(4));
        pitch.bowl(Pins.of(3));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Strike")
    @Test
    void isStrike() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("상태 Spare")
    @Test
    void isSpare() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("첫번째 구 Gutter")
    @Test
    void isGutter() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("두번 째 구 Gutter")
    @Test
    void isGutter2() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss() {
        Pitch pitch = new NormalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(0));
        assertThat(pitch.getState()).isEqualTo(State.MISS);
    }

}
