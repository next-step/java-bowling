package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("Player 테스트")
public class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @Test
    public void generatePlayerTest() {
        assertThatCode(() -> Player.of("ABC"));
    }

    @DisplayName("Player 생성 오류 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"ABCD", "KKKK"})
    public void generatePlayerAbnormalTest(final String name) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Player.of(name));
    }
}