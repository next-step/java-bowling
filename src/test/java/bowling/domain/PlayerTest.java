package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("Player 생성 테스트")
    @Test
    void create(){
        Player player = Player.from("test");

        assertThat(player).isEqualTo(Player.from("test"));
    }

    @DisplayName("Player 길이 테스트")
    @Test
    void length(){
        Player player = Player.from("test");

        assertThat(player.length()).isEqualTo(4);
    }
}
