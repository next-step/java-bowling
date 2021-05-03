package bowling.view.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    private static final String DATA = "ksd";

    Row row;

    @BeforeEach
    void setUp() {
        row = Row.create();
    }

    @Test
    @DisplayName("create를 사용해서 빈 Row를 생성할 수 있다.")
    void create() {
        // given
        // when
        // then
        assertThat(row.row()).isEqualTo("");
    }

    @Test
    @DisplayName("우측정렬된 Cell을 추가해서 출력할 수 있다.")
    void addCellRight() {
        // given
        final Cell right = Cell.right(DATA);

        // when
        row.addCell(right);

        // then
        assertThat(row.row()).isEqualTo("|  ksd |" + System.lineSeparator());
    }

    @Test
    @DisplayName("중앙정렬된 Cell을 추가해서 출력할 수 있다.")
    void addCellCenter() {
        // given
        final Cell center = Cell.center(DATA);

        // when
        row.addCell(center);

        // then
        assertThat(row.row()).isEqualTo("| ksd  |" + System.lineSeparator());
    }
}
