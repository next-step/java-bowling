package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("이름을 입력받을 수 있다.")
    @Test
    void inputPlayerName() {
        String name = "otk";
        Player expect = Player.of(name);

        Player actual = Player.of(name);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("각 플레이어는 자신의 frame을 갖고 있는다.")
    @Test
    void hasFrames() {
        String name = "otk";

        Player actual = Player.of(name);

        assertThat(actual).hasFieldOrProperty("frames");
        assertThat(actual.getStates().size()).isOne();
        assertThat(actual.getScores()).isEmpty();
    }
}