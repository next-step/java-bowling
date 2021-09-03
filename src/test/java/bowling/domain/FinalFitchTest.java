package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFitchTest {
    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        Fitch finalFitch = new FinalFitch();

        assertThat(finalFitch.isFinish()).isFalse();

        finalFitch.bowl(Pins.of(4));
        assertThat(finalFitch.isFinish()).isFalse();

        finalFitch.bowl(Pins.of(3));
        assertThat(finalFitch.isFinish()).isTrue();
    }

    @DisplayName("마지막 frame 은 첫번째 구에 스트라이크 쳤을 때 False")
    @Test
    void isStrikeFinish(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 두번째 구에 스트라이크 이면 False")
    @Test
    void isSecondStrikeFinish(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 스페어면 종료 안함")
    @Test
    void isSpareFinish(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(6));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(4));
        assertThat(finalFitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 0 이고 두번째구 10 이면 스페어 이고 종료 안함")
    @Test
    void isSpareFinish2(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(0));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이면 스트라이크 이고 종료 안함")
    @Test
    void isStrikeFinish2(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이고 보너스볼 10 이면  종료")
    @Test
    void isBonusBowlFinish(){
        Fitch finalFitch = new FinalFitch();

        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isFalse();
        finalFitch.bowl(Pins.of(10));
        assertThat(finalFitch.isFinish()).isTrue();
    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(4));
        fitch.bowl(Pins.of(3));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("첫번째 Strike, 두번째 gutter , 세번째 Strike")
    @Test
    void isStrike(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("합산 10점 Spare 후 Strike")
    @Test
    void isStrike2(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("두번째 구에서 Spare 후 Strike")
    @Test
    void isStrike3(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("10점 연속 세번일 때 Strike")
    @Test
    void isStrike4(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("상태 Spare")
    @Test
    void isSpare(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(5));
        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter2(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter3(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss(){
        Fitch fitch = new FinalFitch();

        fitch.bowl(Pins.of(0));
        fitch.bowl(Pins.of(0));
        assertThat(fitch.getState()).isEqualTo(State.MISS);

    }
}
