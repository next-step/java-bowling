package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerFrameTest {

    @Test
    @DisplayName("스트라이크일 경우 다시 roll 불가능")
    void testWhenStrikeRollOneTime() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        playerFrame.roll(value -> 10, () -> {});
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }

    @Test
    @DisplayName("스페어일 경우 다시 roll 불가능")
    void testWhenRollSpare() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        while (playerFrame.canRoll()) {
            playerFrame.roll(value -> 5, () -> {});
        }
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }

    @Test
    @DisplayName("스트라이크가 아닐 경우 roll 다시 가능")
    void testWhenRollNotStrike() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        playerFrame.roll(value -> 5, () -> {});
        // then
        assertThat(playerFrame.canRoll()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임인 경우 스트라이크 3번 연속 가능")
    void testWhenStrikeRollAgain_finalFrame() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        for (int i = 0; i < 9; i++) {
            playerFrame.nextFrame();
        }
        while (playerFrame.canRoll()) {
            playerFrame.roll(value -> 10, () -> {});
        }
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임인 경우 스페어 처리 후 한 번 더 가능")
    void testWhenSpareRollAgain_finalFrame() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        for (int i = 0; i < 9; i++) {
            playerFrame.nextFrame();
        }
        playerFrame.canRoll();
        playerFrame.roll(value -> 3, () -> {});
        playerFrame.canRoll();
        playerFrame.roll(value -> 7, () -> {});
        playerFrame.canRoll();
        playerFrame.roll(value -> 10, () -> {});
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임인 경우 오픈 일 경우 보너스 X")
    void testWhenOpenNotBonus_finalFrame() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        for (int i = 0; i < 9; i++) {
            playerFrame.nextFrame();
        }
        playerFrame.canRoll();
        playerFrame.roll(value -> 3, () -> {});
        playerFrame.canRoll();
        playerFrame.roll(value -> 4, () -> {});
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }

    @Test
    @DisplayName("스트라이크일 경우 마킹만 받고 점수는 결과는 나중에")
    void testWhenStrike_iteratorMarkingOnly() {
        // given
        PlayerFrame playerFrame = new PlayerFrame(Player.of("PJS"));
        // when
        playerFrame.canRoll();
        playerFrame.roll(value -> 3, () -> {});
        playerFrame.canRoll();
        playerFrame.roll(value -> 4, () -> {});
        // then
        assertThat(playerFrame.canRoll()).isFalse();
    }
}
