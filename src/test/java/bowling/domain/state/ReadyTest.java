package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("첫번째로 쓰리진 핀의 개수가 10개가 아니면 firstBowl반환")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9})
    void bowlToFirstBowlTest(int count) {
        Ready ready = new Ready();
        assertThat(ready.bowl(Pin.from(count))).isEqualTo(new FirstBowl(Pin.from(count)));
    }

    @DisplayName("쓰러진 갯수가 10개라면 Strike 반환")
    @Test
    void bowlToStrikeTest() {
        Ready ready = new Ready();
        assertThat(ready.bowl(Pin.from(10))).isEqualTo(new Strike());
    }

}
