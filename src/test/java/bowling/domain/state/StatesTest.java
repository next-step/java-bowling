package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatesTest {

    @Test
    @DisplayName("2번의 투구 후 정상적으로 mark 표시하는지 검증 테스트")
    void martTest() {
        States states = new States(new Ready());
        states.add(new Ready());

        states.add(new FirstBowl(new Pins(4)));
        states.add(new Spare(new Pins(4), new Pins(6)));

        assertThat(states.getMark()).isEqualTo("4|/");
    }
}
