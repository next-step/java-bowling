package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LastFrameTest {

    @DisplayName("nothing")
    @Test
    void nothing() {
        verify(Collections.emptyList(), false, 1, "");
    }

    @DisplayName("5 pins")
    @Test
    void fivePins() {
        verify(Collections.singletonList(5), false, 1, "5");
    }

    @DisplayName("miss, gutter")
    @Test
    void missWithGutter() {
        verify(Arrays.asList(5, 0), true, 1, "5|-");
    }

    @DisplayName("miss")
    @Test
    void miss() {
        verify(Arrays.asList(5, 4), true, 1, "5|4");
    }

    @DisplayName("1 strike, miss")
    @Test
    void oneStrikeMiss() {
        verify(Arrays.asList(10, 5, 3), true, 2, "X|5|3");
    }

    @DisplayName("1 spare, 5 pins")
    @Test
    void oneSpareFivePins() {
        verify(Arrays.asList(5, 5, 5), true, 2, "5|/|5");
    }

    @DisplayName("1 strike, spare")
    @Test
    void oneStrikeSpare() {
        verify(Arrays.asList(10, 5, 5), true, 2, "X|5|/");
    }

    @DisplayName("2 strike")
    @Test
    void twoStrike() {
        verify(Arrays.asList(10, 10), false, 2, "X|X");
    }

    @DisplayName("2 strike, 5 pins")
    @Test
    void twoStrikeFivePins() {
        verify(Arrays.asList(10, 10, 5), true, 3, "X|X|5");
    }

    @DisplayName("3 strike")
    @Test
    void threeStrike() {
        verify(Arrays.asList(10, 10, 10), true, 3, "X|X|X");
    }

    @DisplayName("LastFrame 생성")
    @Test
    void initialize() {
        // when & then
        assertThat(LastFrame.initialize()).isNotNull();
    }

    @DisplayName("LastFrame 의 index 검증")
    @Test
    void getIndex() {
        // given
        LastFrame lastFrame = LastFrame.initialize();
        // when & then
        assertThat(lastFrame.getIndex()).isEqualTo(FrameIndex.MAX_INDEX);
    }

    private void verify(List<Integer> pinNumbers, boolean isEnd, int stateSize, String symbol) {
        // given
        LastFrame lastFrame = LastFrame.initialize();
        // when
        for (int pinNumber : pinNumbers) {
            lastFrame.bowl(Pins.create(pinNumber));
        }
        // then
        assertAll(
                () -> assertThat(lastFrame.isEnd()).isEqualTo(isEnd),
                () -> assertThat(lastFrame.size()).isEqualTo(stateSize),
                () -> assertThat(lastFrame.symbol()).isEqualTo(symbol)
        );
    }
}
