package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("두 번 투구하여 핀이 모두 쓰러진 상태에 대한 테스트")
class SpareTest {

    @DisplayName("Spare 상태는 첫번째 투구 정보를 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Spare.from(DOWNED_PINS_5)).isInstanceOf(Spare.class);
    }

    @DisplayName("Spare 상태는 Clean 상태다")
    @Test
    void isClean() {
        Spare spare = Spare.from(DOWNED_PINS_5);

        assertThat(spare.isClean()).isTrue();
    }
}
