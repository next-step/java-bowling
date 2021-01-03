package bowling.domain.player;


import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import bowling.domain.state.Miss;
import bowling.domain.state.None;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-16 오전 7:53
 * Developer : Seo
 */
class PlayerTest {
    @DisplayName("사용자명 유효성 체크")
    @Test
    void invalid_name() {
        assertThatThrownBy(() -> new Player("a"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Player("123"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Player("가나다"))
                .withFailMessage("3자 영문으로 입력해주십시요.")
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(new Player("ABC")).isNotNull().isInstanceOf(Player.class);
    }

    @Test
    void stroke() {
        Player player = new Player("aaa");
        Frame frame = player.stroke(0, new Pins(10));
        assertThat(frame.getState()).isInstanceOf(None.class);
    }

    @Test
    void stroke_miss() {
        Player player = new Player("aaa");
        Frame frame = player.stroke(0, new Pins(9));
        assertThat(frame.getState()).isInstanceOf(Miss.class);
    }
}
