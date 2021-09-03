package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalStateTest {

    @DisplayName("bowl 테스트")
    @Test
    void bowlTest(){
        NormalState normalState = new NormalState();

        normalState.bowl(Pins.of(4));

        assertThat(normalState.getFirstPin()).isEqualTo(Pins.of(4));
    }

    @DisplayName("안쳤을 때는 False, 한번 만 첬을 때는 False, 두번 다 쳤을 때는 True 테스트")
    @Test
    void isNormalFinish(){
        NormalState normalState = new NormalState();

        assertThat(normalState.isFinish()).isFalse();

        normalState.bowl(Pins.of(4));
        assertThat(normalState.isFinish()).isFalse();

        normalState.bowl(Pins.of(3));
        assertThat(normalState.isFinish()).isTrue();
    }

    @DisplayName("스트라이크 쳤을 때는 바로 True")
    @Test
    void isStrikeFinish(){
        NormalState normalState = new NormalState();

        normalState.bowl(Pins.of(10));
        assertThat(normalState.isFinish()).isTrue();

    }
}
