package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("플레이어가 투구하면, 다음 Frames 를 보관한다")
    @Test
    void bowl() {
        Player player = player();
        player.bowl(1);

        Player expected = player(frames(0).bowl(FallenPin.of(1)));
        assertThat(player).isEqualTo(expected);
    }

    @DisplayName("모든 프레임이 끝났으면 참을 반환한다")
    @Test
    void isFramesFinished_true() {
        Player player = player(frames(12));

        assertThat(player.isFramesFinished()).isTrue();
    }

    @DisplayName("모든 프레임이 끝나지 않았으면 거짓을 반환한다")
    @Test
    void isFramesFinished_false() {
        assertThat(player().isFramesFinished()).isFalse();
    }

    @DisplayName("현재 프레임이 끝났으면 참을 반환한다")
    @Test
    void isCurrentFrameFinished_true() {
        assertThat(player(frames(1)).isCurrentFrameFinished()).isTrue();
    }

    @DisplayName("현재 프레임이 끝나지 않았으면 거짓을 반환한다")
    @Test
    void isCurrentFrameFinished_false() {
        Player player = player(frames(1).bowl(FallenPin.of(1)));

        assertThat(player.isCurrentFrameFinished()).isFalse();
    }

    private Player player() {
        return new Player(playerName());
    }

    private Player player(Frames frames) {
        return new Player(playerName(), frames);
    }

    private PlayerName playerName() {
        return new PlayerName("ABC");
    }

    private Frames frames(int tries) {
        Frames result = Frames.init();
        for (int i = 0; i < tries; i++) {
            result = result.bowl(FallenPin.of(10));
        }
        return result;
    }
}
