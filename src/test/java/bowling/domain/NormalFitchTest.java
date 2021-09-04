package bowling.domain;

import bowling.exception.OverTheMaxPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NormalFitchTest {

    @DisplayName("bowl 테스트")
    @Test
    void bowlTest() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(4));

        assertThat(fitch.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("exception 테스트(두핀이 10이 넘었을 때)")
    @Test
    void bowlExceptionTest() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(4));

        assertThatExceptionOfType(OverTheMaxPinsException.class)
                .isThrownBy(() -> {
                    fitch.bowl(Pins.of(7));
                }).withMessageMatching("두 핀의 합이 10이 넘습니다.");
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish() {
        Fitch fitch = new NormalFitch();

        assertThat(fitch.isFinish()).isFalse();

        fitch.bowl(Pins.of(4));
        assertThat(fitch.isFinish()).isFalse();

        fitch.bowl(Pins.of(3));
        assertThat(fitch.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(10));
        assertThat(fitch.isFinish()).isTrue();

    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(4));
        fitch.bowl(Pins.of(3));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Strike")
    @Test
    void isStrike() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("상태 Spare")
    @Test
    void isSpare() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));
        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter2() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss() {
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));
        fitch.bowl(Pins.of(0));
        assertThat(fitch.getState()).isEqualTo(State.MISS);
    }

}
