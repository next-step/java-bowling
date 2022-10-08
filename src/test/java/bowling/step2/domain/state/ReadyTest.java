package bowling.step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    public static final Ready READY = new Ready();
    
    @Test
    @DisplayName("스트라이크")
    void strike() {
        State state = READY.bowl(0);
        assertThat(state).isExactlyInstanceOf(Strike.class);
    }
    
    @Test
    @DisplayName("노멀")
    void normal() {
        State state = READY.bowl(3);
        assertThat(state).isExactlyInstanceOf(Normal.class);
    }
}
