package bowling.domain;

import bowling.exception.OverTheMaxPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalPitchTest {

    @DisplayName("exception 테스트(두핀이 10이 넘었을 때)")
    @Test
    void bowlExceptionTest() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(4));

        assertThatExceptionOfType(OverTheMaxPinsException.class)
                .isThrownBy(() -> {
                    pitch.bowl(Pins.of(7));
                }).withMessageMatching("두 핀의 합이 10이 넘습니다.");
    }

    @DisplayName("안쳤을 때는 False")
    @Test
    void isFirstFitchNotFinish() {
        Pitch finalPitch = new FinalPitch();
        assertThat(finalPitch.isFinish()).isFalse();
    }

    @DisplayName("스페어나 스트라이크 아니고, 두번 다 쳤을 때는 정상 종료  테스트")
    @Test
    void isNormalFinish() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(4));
        finalPitch.bowl(Pins.of(3));
        assertThat(finalPitch.isFinish()).isTrue();
    }

    @DisplayName("마지막 frame 은 첫번째 구에 스트라이크 쳤을 때 False")
    @Test
    void isStrikeFinish() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이면 스트라이크 이고 종료 안함")
    @Test
    void isSecondStrikeFinish() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 스페어면 종료 안함")
    @Test
    void isSpareFinish() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(6));
        finalPitch.bowl(Pins.of(4));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 0 이고 두번째구 10 이면 스페어 이고 종료 안함")
    @Test
    void isSpareFinish2() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(0));
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이고 보너스볼 10 이면  종료")
    @Test
    void isBonusBowlFinish() {
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        finalPitch.bowl(Pins.of(10));
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isTrue();
    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(4));
        pitch.bowl(Pins.of(3));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("연속 Strike 두번후  Normal 테스트")
    @Test
    void isNormal2() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("첫번째 Strike, 두번째 gutter , 세번째 Strike")
    @Test
    void isStrike() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));
        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("합산 10점 Spare 후 Strike")
    @Test
    void isStrike2() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("두번째 구에서 Spare 후 Strike")
    @Test
    void isStrike3() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("10점 연속 세번일 때 Strike")
    @Test
    void isStrike4() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("연속 Strike 두번 후 일반 볼 Test")
    @Test
    void isStrike5() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("첫번째 구 normal, 두번째 구 normal 일때 Spare 테스트")
    @Test
    void isSpare() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(3));
        pitch.bowl(Pins.of(7));
    }

    @DisplayName("첫번째 구 gutter, 두번째 구 10일때 Spare 테스트")
    @Test
    void isSpare2() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));
        assertThat(pitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("첫번째 구 Gutter 테스트")
    @Test
    void isGutter() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("두번째 구 Gutter")
    @Test
    void isGutter2() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("Spare 후 Gutter 테스트")
    @Test
    void isGutter3() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("두번째구 10 으로 Spare 후 보너스 프레임 에서 Gutter 테스트")
    @Test
    void isGutter4() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));
        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss() {
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(0));
        assertThat(pitch.getState()).isEqualTo(State.MISS);

    }
}
