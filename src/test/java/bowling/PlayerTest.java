package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("플레이어테스트")
    public void playerTest() {
        Player player = new Player("KPJ");
        assertThat(player.toString()).isEqualTo("KPJ");
    }

    @Test
    @DisplayName("자릿수테스트")
    public void checkLettersTest() {
        String name = "1234";
        assertThatThrownBy(() -> new Player(name) ).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("3글자까지만 입력가능합니다.");
    }
}
