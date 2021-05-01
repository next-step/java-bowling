package bowling.domain;

import bowling.domain.engine.frame.FrameNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.initialize("aaa");
    }

    @Test
    @DisplayName("프레임 번호를 입력 받으면 그 프레임에서 투구를 종료했는지 여부를 반환한다.")
    void returnFrameFinishedGivenFrameNumber() {
        assertThat(player.isPlayedFrameOf(FrameNumber.firstFrame())).isFalse();

        player.roll(RollResult.of(10));

        assertThat(player.isPlayedFrameOf(FrameNumber.firstFrame())).isTrue();
    }
}
