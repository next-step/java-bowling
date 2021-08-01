package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("두 번 투구하여 핀이 남은 경우의 상태 테스트")
class MissTest {

    @DisplayName("Miss 상태는 두 개의 쓰러진 핀 객체를 가지고 초기화한다")
    @Test
    void init() {
        assertThat(Miss.of(DownedPins.from(5), DownedPins.from(2))).isInstanceOf(Miss.class);
    }

    @DisplayName("Miss 상태에서 isMiss 테스트")
    @Test
    void isMiss() {
        Miss miss = Miss.of(DownedPins.from(5), DownedPins.from(2));

        assertThat(miss.isMiss()).isTrue();
    }
}
