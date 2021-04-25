package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("스트라이크 또는 핀 5개씩 쓰러뜨릴 때 각각 12번, 21번까지 플레이 가능하다")
    @ParameterizedTest
    @CsvSource(value = {"10, 12", "5, 21"})
    void isPlayable(int countOfDownPin, int playCount) {
        Frames frames = Frames.newInstance();
        frames.init();

        for (int i = 0; i < playCount; i++) {
            assertThat(frames.isPlayable()).isTrue();
            frames.play(countOfDownPin);
        }
        assertThat(frames.isPlayable()).isFalse();
    }

    @DisplayName("스트라이크를 치면 다음 라운드로 이동한다")
    @Test
    void play_strike() {
        // given
        Frames frames = Frames.newInstance();
        frames.init();

        // when
        frames.play(10);

        // then
        assertThat(frames.getRound()).isEqualTo(2);
    }

    @DisplayName("스트라이크가 아닐때 투구를하면 현재 라운드에 머문다")
    @Test
    void play_not_strike() {
        // given
        Frames frames = Frames.newInstance();
        frames.init();

        // when
        frames.play(1);

        // then
        assertThat(frames.getRound()).isEqualTo(1);
    }
}
