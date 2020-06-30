package bowling.domain.player;

import bowling.exception.PlayerCountOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerCountTest {

    @DisplayName("PlayerCount 생성 실패: -1 보다 작은 수")
    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void createFailure(final int count) {
        assertThatExceptionOfType(PlayerCountOutOfRangeException.class)
                .isThrownBy(() -> PlayerCount.of(count));
    }

    @DisplayName("플레이어 수를 반환")
    @Test
    void create() {
        assertThat(PlayerCount.of(1).getCount())
                .isEqualTo(1);
    }

    @DisplayName("논리적 동치성 비교")
    @Test
    void equals() {
        assertThat(PlayerCount.of(1))
                .isEqualTo(PlayerCount.of(1));
    }
}
