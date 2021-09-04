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

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        Pitch finalPitch = new FinalPitch();

        assertThat(finalPitch.isFinish()).isFalse();

        finalPitch.bowl(Pins.of(4));
        assertThat(finalPitch.isFinish()).isFalse();

        finalPitch.bowl(Pins.of(3));
        assertThat(finalPitch.isFinish()).isTrue();
    }

    @DisplayName("마지막 frame 은 첫번째 구에 스트라이크 쳤을 때 False")
    @Test
    void isStrikeFinish(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 두번째 구에 스트라이크 이면 False")
    @Test
    void isSecondStrikeFinish(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 스페어면 종료 안함")
    @Test
    void isSpareFinish(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(6));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(4));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 0 이고 두번째구 10 이면 스페어 이고 종료 안함")
    @Test
    void isSpareFinish2(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(0));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이면 스트라이크 이고 종료 안함")
    @Test
    void isStrikeFinish2(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이고 보너스볼 10 이면  종료")
    @Test
    void isBonusBowlFinish(){
        Pitch finalPitch = new FinalPitch();

        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isFalse();
        finalPitch.bowl(Pins.of(10));
        assertThat(finalPitch.isFinish()).isTrue();
    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(4));
        pitch.bowl(Pins.of(3));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("첫번째 Strike, 두번째 gutter , 세번째 Strike")
    @Test
    void isStrike(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("합산 10점 Spare 후 Strike")
    @Test
    void isStrike2(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.SPARE);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("두번째 구에서 Spare 후 Strike")
    @Test
    void isStrike3(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.SPARE);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("10점 연속 세번일 때 Strike")
    @Test
    void isStrike4(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("연속 두번후 Strike 일반 볼 Test")
    @Test
    void isStrike5(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);

        pitch.bowl(Pins.of(10));

        assertThat(pitch.getState()).isEqualTo(State.STRIKE);

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Spare")
    @Test
    void isSpare(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));
        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("상태 Spare 후 보너스 프레임 에서 Normal Test")
    @Test
    void isSpare2(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));
        assertThat(pitch.getState()).isEqualTo(State.SPARE);

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Spare 후 보너스 프레임 에서 Gutter Test")
    @Test
    void isSpare3(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(10));
        assertThat(pitch.getState()).isEqualTo(State.SPARE);

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter2(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter3(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.NORMAL);

        pitch.bowl(Pins.of(5));

        assertThat(pitch.getState()).isEqualTo(State.SPARE);

        pitch.bowl(Pins.of(0));

        assertThat(pitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss(){
        Pitch pitch = new FinalPitch();

        pitch.bowl(Pins.of(0));
        pitch.bowl(Pins.of(0));
        assertThat(pitch.getState()).isEqualTo(State.MISS);

    }
}
