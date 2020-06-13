package bowling.domain;

import bowling.step2.domain.Player;
import bowling.step2.exception.PlayerNameEmptyException;
import bowling.step2.exception.PlayerNameMaximumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @DisplayName("플레이어의 이름의 글자수가 3글자를 초과할 경우 PlayerNameMaximumException 발생")
    @ParameterizedTest
    @ValueSource(strings = {"1234", "12345"})
    void 이름_길이_검증_테스트 (String value) {
        assertThatExceptionOfType(PlayerNameMaximumException.class)
            .isThrownBy(() -> Player.valueOf(value));
    }

    @DisplayName("플레이어의 이름이 비어있을 경우 PlayerNameEmptyException 발생")
    @ParameterizedTest
    @MethodSource("provideEmptyValue")
    void 이름_빈칸_검증_테스트 (String value) {
        assertThatExceptionOfType(PlayerNameEmptyException.class)
            .isThrownBy(() -> Player.valueOf(value));
    }

    private static Stream<Arguments> provideEmptyValue () {
        String emptyValue = "";
        String nullValue = null;
        return Stream.of(
            Arguments.of(emptyValue),
            Arguments.of(nullValue)
        );
    }

    @DisplayName("객체를 재사용 하고 있는지 확인하는 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "123", "hji", "abc" })
    void 객체_재사용_테스트 (String value) {
        Player player = Player.valueOf(value);
        assertEquals(player, Player.valueOf(value));
    }
}
