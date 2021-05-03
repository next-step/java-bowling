package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.FrameNumber;
import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BowlingGameTest {
    @Test
    @DisplayName("생성자 테스트")
    void When_New_Then_Created() {
        Player player = new Player("abc");
        assertThat(new BowlingGame(player)).isEqualTo(new BowlingGame(player));
    }

    @Test
    @DisplayName("플레이어 이름을 잘 가지고 있는지 테스트")
    void When_Player_Then_Player() {
        Player player = new Player("abc");
        assertThat(new BowlingGame(player).player()).isEqualTo(player);
    }

    @Test
    @DisplayName("현재 Frame 번호를 잘 가지고 있는지 테스트")
    void When_FrameNumber_Then_FrameNumber() {
        Player player = new Player("abc");
        assertThat(new BowlingGame(player).frameNumber()).isEqualTo(new FrameNumber(1));
    }
}
