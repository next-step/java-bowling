package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("첫번째 타구로 10개 핀이 쓰러지면 Strike 반환 테스트")
    void afterBowlStrikeTest() {
        Ready ready = new Ready();
        assertThat(ready.bowl(10)).isEqualTo(new Strike());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7,8})
    @DisplayName("첫번째 타구로 10개 핀이 쓰러지지 않으면 FirstBowl 객체 반환 테스트")
    void afterBowlFirstBowlTest(int knockedDownPin) {
        Ready ready = new Ready();
        assertThat(ready.bowl(knockedDownPin)).isEqualTo(new FirstBowl(new Pins(knockedDownPin)));
    }
}
