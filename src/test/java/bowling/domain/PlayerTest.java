package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PlayerTest {

    @DisplayName("사용자 이름이 영문자 3자가 아니라면, 예외가 발생해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "ab",
            "abcd",
            "ab1",

    })
    void create_givenInvalidName(String playerName) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player(playerName));
    }

    @DisplayName("투구를 진행하면, 해당 투구 내용이 게임에 반영되어야 한다.")
    @Test
    void hit() {
        Player player = new Player("abc");
        player.hit(2);
        player.hit(7);
        player.moveNextFrame();
        player.hit(0);
        player.hit(10);
        player.moveNextFrame();
        player.hit(10);
        player.moveNextFrame();

        assertThat(player.getGame()).isEqualTo(BowlingGameTestFixture.createBowlingGame(2, 7, 0, 10, 10));
    }

    @DisplayName("다음 프레임으로 넘어가면 현재 프레임도 바뀌어야 한다.")
    @Test
    void moveNextFrame() {
        Player player = new Player("abc");
        player.hit(10);
        assertThat(player.getGame().getCurrentFrame()).isEqualTo(player.getGame().get(0));

        player.moveNextFrame();
        assertThat(player.getGame().getCurrentFrame()).isEqualTo(player.getGame().get(1));
    }

    @DisplayName("프레임이 종료되었다면, true를 반환해야 한다.")
    @Test
    void isCurrentFrameEnded_givenEnded() {
        Player player = new Player("abc");
        player.hit(10);
        assertThat(player.isCurrentFrameEnded()).isTrue();
    }


    @DisplayName("프레임이 진행 중인 상태라면, false를 반환해야 한다.")
    @Test
    void isCurrentFrameEnded_givenOnGoing() {
        Player player = new Player("abc");
        player.hit(9);
        assertThat(player.isCurrentFrameEnded()).isFalse();
    }
}
