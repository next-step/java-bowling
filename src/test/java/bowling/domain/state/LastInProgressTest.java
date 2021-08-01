package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 프레임의 진행상태 테스트")
class LastInProgressTest {

    @DisplayName("마지막 프레임 진행 상태는 초기화시 아무것도 필요하지 않다")
    @Test
    void init() {
        assertThat(LastInProgress.init()).isInstanceOf(LastInProgress.class);
    }

    @DisplayName("마지막 프레임의 진행 상태는 최대 3번까지 투구가 가능하다")
    @MethodSource
    @ParameterizedTest
    void downPins(List<DownedPins> play) {
        LastInProgress lastInProgress = LastInProgress.init();

        play.forEach(downedPins -> {
            lastInProgress.downPins(downedPins);
            assertThat(lastInProgress.isEnd()).isFalse();
        });

        assertThat(lastInProgress.isEnd()).isTrue();
    }
}
