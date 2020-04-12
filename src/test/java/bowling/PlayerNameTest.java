package bowling;

import bowling.domain.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerNameTest {
    @DisplayName("플레이어 이름이 알파벳 세 글자이면 성공")
    @Test
    void createTest() {
        String name = "YSH";

        assertThatCode(() -> {
            new PlayerName(name);
        }).doesNotThrowAnyException();
    }

    @DisplayName("플레이어 이름의 길이가 3이 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"Soojin", "Yuqi", "Soyeon"})
    void throwExceptionWhenGreaterThanThree(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new PlayerName(name);
        });
    }

    @DisplayName("플레이어 이름이 영문이 아니면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"@@@", "123", "홍홍홍"})
    void throwExceptionWhenNotEnglishName(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new PlayerName(name);
        });
    }
}