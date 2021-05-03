package bowling.view.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

    private static final String DATA = "ksd";

    @Test
    @DisplayName("right를 사용하면 우측 정렬된 Cell이 반환된다.")
    void right() {
        // given
        // when
        final Cell right = Cell.right(DATA);

        // then
        assertThat(right.getAlign()).isEqualTo(Align.RIGHT);
    }

    @Test
    @DisplayName("right를 사용하면 중앙 정렬된 Cell이 반환된다.")
    void center() {
        // given
        // when
        final Cell center = Cell.center(DATA);

        // then
        assertThat(center.getAlign()).isEqualTo(Align.CENTER);
    }

    @Test
    @DisplayName("data를 사용하면 cell에 저장된 data가 반환된다.")
    void data() {
        // given
        // when
        final Cell cell = Cell.right(DATA);

        // then
        assertThat(cell.data()).isEqualTo("ksd");
    }

    @Test
    @DisplayName("cell에 저장된 data가 없는경우 빈 문자열이 반환된다.")
    void nullData() {
        // given
        // when
        final Cell cell = Cell.right(null);

        // then
        assertThat(cell.data()).isEqualTo("");
    }
}
