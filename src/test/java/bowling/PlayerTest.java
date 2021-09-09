package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    void plyerOverNameSize() {
        assertThatThrownBy(
                () -> Player.of("kkkk")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void plyerNamePass() {
        Player player = Player.of("kkk");
        assertThat(player.getName()).isEqualTo("kkk");
    }
}
