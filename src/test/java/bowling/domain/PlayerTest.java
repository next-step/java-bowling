package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {
    public static Player PLAYER_AYM = Player.create("AYM");

    @DisplayName("플레이어 이름이 영어 3자일 경우 생성 검증")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "ABC", "aBc"})
    void create(String name) {
        // when & then
		assertThat(Player.create(name)).isNotNull();
    }

    @DisplayName("플레이어 이름이 영어 3자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "aB", "ABCD", "123", "김수민"})
    void createWithInvalidName(String invalidName) {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Player.create(invalidName));
    }
}
