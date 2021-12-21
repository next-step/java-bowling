package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("거터면 - 아니면 쓰러뜨린 핀 개수 표시 테스트")
    void getDescTest(){
        assertThat(new Miss(Pins.knockedDown(5), Pins.knockedDown(4)).getDesc()).isEqualTo("4");
        assertThat(new Miss(Pins.knockedDown(5), Pins.knockedDown(0)).getDesc()).isEqualTo("-");
    }
}
