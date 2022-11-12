package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.pin.FallenPin;
import bowling.domain.player.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LaneTest {

    @DisplayName("Lane 이 투구하면, 다음 Frames 를 보관한다")
    @Test
    void bowl() {
        Lane lane = lane();
        lane.bowl(1);

        Lane expected = lane(frames(0).bowl(FallenPin.of(1)));
        assertThat(lane).isEqualTo(expected);
    }

    @DisplayName("모든 프레임이 끝났으면 참을 반환한다")
    @Test
    void isFramesFinished_true() {
        Lane lane = lane(frames(12));

        assertThat(lane.isFramesFinished()).isTrue();
    }

    @DisplayName("모든 프레임이 끝나지 않았으면 거짓을 반환한다")
    @Test
    void isFramesFinished_false() {
        assertThat(lane().isFramesFinished()).isFalse();
    }

    @DisplayName("현재 프레임이 끝났으면 참을 반환한다")
    @Test
    void isCurrentFrameFinished_true() {
        assertThat(lane(frames(1)).isCurrentFrameFinished()).isTrue();
    }

    @DisplayName("현재 프레임이 끝나지 않았으면 거짓을 반환한다")
    @Test
    void isCurrentFrameFinished_false() {
        Lane lane = lane(frames(1).bowl(FallenPin.of(1)));

        assertThat(lane.isCurrentFrameFinished()).isFalse();
    }

    private Lane lane() {
        return new Lane(playerName());
    }

    private Lane lane(Frames frames) {
        return new Lane(playerName(), frames);
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
