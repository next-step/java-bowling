package bowling.state;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    private final Ready ready = new Ready();

    @Test
    void 처음_상태이며_10개를_쓰러뜨리면_스트라이크를_반환() {
        assertThat(ready.bowl(10)).isInstanceOf(Strike.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void 처음_상태이며_10개_이외를_쓰러뜨리면_첫번째투구_반환(int pinCount) {
        assertThat(ready.bowl(pinCount)).isInstanceOf(FirstBowl.class);
        //FirstBowl의 상태는 검증하지 않아도 되는가??
    }
}
