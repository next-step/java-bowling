package bowling;

import bowling.domain.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PlayerNameTest {
    @DisplayName("플레이어 이름이 알파벳 세 글자이면 성공")
    @Test
    void createTest() {
        String name = "YSH";

        assertThatCode(() -> {
            new PlayerName(name);
        }).doesNotThrowAnyException();
    }
}