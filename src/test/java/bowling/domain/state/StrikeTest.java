package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("첫번째 투구에서 모든 핀을 쓰러뜨린 상태 테스트")
class StrikeTest {

    @DisplayName("Strike 는 초기화시 어떤 정보도 필요 없다")
    @Test
    void init() {
        assertThat(Strike.init()).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike 상태는 Clean 상태다")
    @Test
    void isClean() {
        Strike strike = Strike.init();

        assertThat(strike.isClean()).isTrue();
    }
}
