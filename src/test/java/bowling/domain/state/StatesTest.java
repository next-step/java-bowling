package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatesTest {

    @Test
    @DisplayName("2번의 투구 후 정상적으로 mark 표시하는지 검증 테스트")
    void martTest() {

        List<State> tempStates = new ArrayList<>();
        tempStates.add(new FirstBowl(new Pins(4)));
        tempStates.add(new Spare(new Pins(4), new Pins(6)));

        States states = new States(tempStates);

        assertThat(states.getMark()).isEqualTo("4|/");
    }
}
