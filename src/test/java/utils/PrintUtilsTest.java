package utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PrintUtilsTest {
    @Test
    void 입력받은_문자열을_프레임의_가운데로_정렬한다() {
        assertAll(
                () -> assertThat(PrintUtils.centralize("-|-")).isEqualTo("  -|- "),
                () -> assertThat(PrintUtils.centralize("-|1")).isEqualTo("  -|1 "),
                () -> assertThat(PrintUtils.centralize("X")).isEqualTo("  X   "),
                () -> assertThat(PrintUtils.centralize("9|/")).isEqualTo("  9|/ ")
        );
    }

    @Test
    void 마지막_프레임에서_보너스투구가_진행되면_PREFIX를_한_칸_줄인다() {
        assertThat(PrintUtils.centralize("X|X|X")).isEqualTo(" X|X|X");
    }
}
