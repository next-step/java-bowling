package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("스트라이크 생성")
    void bowlStrike() {
        State ready = new Ready();
        assertThat(ready.bowl(10)).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("FirstBowl 생성")
    void bowlFirstBowl() {
        State ready = new Ready();
        assertThat(ready.bowl(9)).isInstanceOf(FirstBowl.class);
    }

}