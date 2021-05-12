package bowling.domain.player;

import bowling.domain.HitNumber;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FramesTest.lastFrames;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void 이름을입력받아_플레이어를생성한다() {
        Player player = Player.of("테스트");
        assertThat(player).isEqualTo(Player.of("테스트"));
        assertThat(player.isFinished()).isFalse();
    }

    @Test
    @DisplayName("핀을 치는 갯수를 입력받아 해당하는 프레임에서 진행한다.")
    void 볼을굴릴수있다() {
        Player player = Player.of("굴리기");
        player.bowl(3);
        Frames predict = Frames.of();
        predict.play(HitNumber.of(3));
        assertThat(player).isEqualTo(Player.of("굴리기", predict));
    }

    @Test
    void 프레임이_완료되었는지_확인할수있다() {
        Player player = Player.of("완료용", lastFrames());
        assertThat(player.isFinished()).isTrue();
    }
}
