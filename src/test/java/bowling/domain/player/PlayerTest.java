package bowling.domain.player;

import bowling.domain.HitNumber;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FramesTest.lastFrames;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.of("테스트");
    }

    @Test
    void 이름을입력받아_플레이어를생성한다() {
        assertThat(player).isEqualTo(Player.of("테스트"));
        assertThat(player.isFinished()).isFalse();
    }

    @Test
    @DisplayName("핀을 치는 갯수를 입력받아 해당하는 프레임에서 진행한다.")
    void 볼을굴릴수있다() {
        player.bowlWithNext(HitNumber.of(3));
        Frames predict = Frames.of();
        predict.playWithNext(HitNumber.of(3));
        assertThat(player).isEqualTo(Player.of("테스트", predict));
    }

    @Test
    void 프레임이_완료되었는지_확인할수있다() {
        Player test = Player.of("완료용", lastFrames());
        assertThat(test.isFinished()).isTrue();
    }

    @Test
    void 프레임진행중() {
        assertThat(player.bowlWithNext(HitNumber.of(3))).isFalse();
    }

    @Test
    void 프레임완료_다음프레임으로진행() {
        assertThat(player.bowlWithNext(HitNumber.of(3))).isFalse();
        assertThat(player.bowlWithNext(HitNumber.of(7))).isTrue();
    }
}
