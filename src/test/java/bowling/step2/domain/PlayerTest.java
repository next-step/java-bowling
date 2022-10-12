package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerTest {
    public Player player = new Player("SJH");
    
    @Test
    @DisplayName("현재 프레임이 계속 진행중인지 확인")
    void is_current_frame_running() {
        assertAll(
                () -> assertThat(player.bowl(new CountOfFallenPinsDTO("4"))).isTrue(),
                () -> assertThat(player.bowl(new CountOfFallenPinsDTO("5"))).isFalse()
        );
    }
}