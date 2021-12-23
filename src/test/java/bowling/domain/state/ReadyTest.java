package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {

    @Test
    @DisplayName("첫 번째 볼링공 던졌을 때 10개를 맞춘다면 스트라이크 반환 검증")
    void strikeTest() {
        Ready ready = new Ready();

        assertThat(ready.bowl(new Pins(10))).isEqualTo(new Strike());
    }

    @Test
    @DisplayName("첫 번째 볼링공 던졌을 때 10개를 못 맞춘다면 FirstBowl 반환 검증")
    void firstBowlTest() {
        Ready ready = new Ready();

        assertThat(ready.bowl(new Pins(5))).isEqualTo(new FirstBowl(new Pins(5)));
    }

}
