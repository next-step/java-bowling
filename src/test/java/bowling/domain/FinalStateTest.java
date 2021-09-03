package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalStateTest {
    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        State finalState = new FinalState();

        assertThat(finalState.isFinish()).isFalse();

        finalState.bowl(Pins.of(4));
        assertThat(finalState.isFinish()).isFalse();

        finalState.bowl(Pins.of(3));
        assertThat(finalState.isFinish()).isTrue();
    }

    @DisplayName("마지막 frame 은 첫번째 구에 스트라이크 쳤을 때 False")
    @Test
    void isStrikeFinish(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 두번째 구에 스트라이크 이면 False")
    @Test
    void isSecondStrikeFinish(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();

    }

    @DisplayName("마지막 frame 은 스페어면 종료 안함")
    @Test
    void isSpareFinish(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(6));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(4));
        assertThat(finalState.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 0 이고 두번째구 10 이면 스페어 이고 종료 안함")
    @Test
    void isSpareFinish2(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(0));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이면 스트라이크 이고 종료 안함")
    @Test
    void isStrikeFinish2(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();

    }

    @DisplayName("첫번째구 10 이고 스트라이크, 두번째구 10 이고 보너스볼 10 이면  종료")
    @Test
    void isBonusBowlFinish(){
        State finalState = new FinalState();

        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isFalse();
        finalState.bowl(Pins.of(10));
        assertThat(finalState.isFinish()).isTrue();
    }
}
