package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @Test
    @DisplayName("객체 비교")
    void compareWithPlayerObjectTest() {
        Player player = new Player("KSJ");
        Player player1 = new Player("KSJ");

        assertThat(player).isEqualTo(player1);
    }

    @Test
    @DisplayName("플레이어 이름 길이 예외처리")
    void validateLengthByNameTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player player = new Player("KSJOO");
        });

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Player player1 = new Player("JS");
        });
    }
}
