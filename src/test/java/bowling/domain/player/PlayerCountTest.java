package bowling.domain.player;

import bowling.exception.InvalidPlayerCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerCountTest {
    @DisplayName("플레이어 횟수 객체를 생성할 수 있다.")
    @Test
    void create() {
        PlayerCount expect = PlayerCount.valueOf("2");

        PlayerCount actual = PlayerCount.valueOf("2");

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("플레이어 횟수를 1 미만으로 입력하면 커스텀 예외를 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidPlayerCounts")
    void validPlayerCount(String count) {
        assertThatExceptionOfType(InvalidPlayerCountException.class).isThrownBy(
                () -> PlayerCount.valueOf(count)
        );
    }

    static Stream<String> invalidPlayerCounts() {
        return Stream.of("0", "-1");
    }
}