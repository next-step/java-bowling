package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void getName() {
        Player player = new Player("Jack");
        assertThat(player.getName()).isEqualTo("Jack");
    }
}
