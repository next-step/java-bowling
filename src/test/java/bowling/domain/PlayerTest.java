package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("플레이어 이름 제대로 입력하지 않은 경우")
    void testPlayerNameNullOrEmpty(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.of(input));
    }

    @Test
    @DisplayName("플레이어 이름 길이 길게 입력하는 경우")
    void testPlayerNameTooLong() {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.of("MONDS"));
    }

    @Test
    @DisplayName("플레이어 생성")
    void testPlayerCreate() {
        // given
        String name = "PJS";
        // when
        Player player = Player.of(name);
        // then
        assertThat(player.getName()).isEqualTo(name);
    }
}
