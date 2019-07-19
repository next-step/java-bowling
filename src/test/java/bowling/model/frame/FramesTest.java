package bowling.model.frame;

import bowling.model.DownPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.model.DownPin.*;
import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("첫번째 프레임의 정보를 저장한다")
    @Test
    void saveHistory() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.getFrames()).hasSize(1);
    }

    @DisplayName("한 프레임을 끝낸 후 다음 프레임을 저장한다")
    @Test
    void saveBowlHistory_pinZeroAndZero_returnFramesTwo() {
        // given  && when
        Frames frames = Frames.initialize();
        frames.saveBowling(DOWN_ZERO);
        frames.saveBowling(DOWN_ZERO);
        List<Frame> result = frames.getFrames();

        // then
        assertThat(result).hasSize(2);
    }

    @DisplayName("두 프레임을 진행한다")
    @Test
    void saveBowlHistory_pinStrikeAndMiss() {
        // given
        DownPin firstBowl = DownPin.DOWN_ALL;
        DownPin secondBowl = DownPin.DOWN_ZERO;
        DownPin thirdBowl = DownPin.valueOf(MAX - 1);

        // when
        Frames frames = Frames.initialize();
        frames.saveBowling(firstBowl);
        frames.saveBowling(secondBowl);
        frames.saveBowling(thirdBowl);

        // then
        assertThat(frames.getFrames()).hasSize(3);
    }

    @DisplayName("모두 스트라이크의 결과를 반환한다")
    @Test
    void bowl_all_strike_success() {
        // given
        Frames frames = Frames.initialize();

        // when
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);
        frames.saveBowling(DOWN_ALL);

        Results result = frames.getResult();

        // then
        assertThat(result.getResults()).hasSize(10);
        assertThat(result.getScores()).containsExactly(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);
    }

    @DisplayName("모든 플레이를 했을 경우 게임을 종료하는데 성공한다")
    @Test
    void isGameOver_allBowl_isTrue() {
        // given && when
        Frames frames = Frames.initialize();
        IntStream.rangeClosed(1, 12)
                .forEach((ignore) -> frames.saveBowling(DOWN_ALL));

        // then
        assertThat(frames.isGameOver()).isTrue();
    }
}