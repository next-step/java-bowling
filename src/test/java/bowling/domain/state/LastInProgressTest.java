package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 프레임의 진행상태 테스트")
class LastInProgressTest {

    @DisplayName("마지막 프레임 진행 상태는 초기화시 아무것도 필요하지 않다")
    @Test
    void init() {
        assertThat(LastInProgress.init()).isInstanceOf(LastInProgress.class);
    }
}
