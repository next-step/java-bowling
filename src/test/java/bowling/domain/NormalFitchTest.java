package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFitchTest {

    @DisplayName("bowl 테스트")
    @Test
    void bowlTest(){
        NormalFitch normalState = new NormalFitch();

        normalState.bowl(Pins.of(4));

        assertThat(normalState.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        NormalFitch normalState = new NormalFitch();

        assertThat(normalState.isFinish()).isFalse();

        normalState.bowl(Pins.of(4));
        assertThat(normalState.isFinish()).isFalse();

        normalState.bowl(Pins.of(3));
        assertThat(normalState.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish(){
        NormalFitch normalState = new NormalFitch();

        normalState.bowl(Pins.of(10));
        assertThat(normalState.isFinish()).isTrue();

    }

    @DisplayName("상태 Normal")
    @Test
    void isNormal(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(4));
        fitch.bowl(Pins.of(3));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Strike")
    @Test
    void isStrike(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.STRIKE);
    }

    @DisplayName("상태 Spare")
    @Test
    void isSpare(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));
        fitch.bowl(Pins.of(10));

        assertThat(fitch.getState()).isEqualTo(State.SPARE);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);
    }

    @DisplayName("상태 Gutter")
    @Test
    void isGutter2(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(5));

        assertThat(fitch.getState()).isEqualTo(State.NORMAL);

        fitch.bowl(Pins.of(0));

        assertThat(fitch.getState()).isEqualTo(State.GUTTER);
    }

    @DisplayName("상태 Miss")
    @Test
    void isMiss(){
        Fitch fitch = new NormalFitch();

        fitch.bowl(Pins.of(0));
        fitch.bowl(Pins.of(0));
        assertThat(fitch.getState()).isEqualTo(State.MISS);

    }

}
