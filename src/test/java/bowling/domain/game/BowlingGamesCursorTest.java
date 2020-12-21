package bowling.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesCursorTest {

    private BowlingGamesCursor bowlingGamesCursor;

    @BeforeEach
    void setUp() {
        bowlingGamesCursor = BowlingGamesCursor.of();
    }

    @Test
    @DisplayName("커서가 잘 생성되는지 확인한다.")
    void createCursor() {
        assertThat(bowlingGamesCursor).isEqualTo(BowlingGamesCursor.of());
    }

    @Test
    @DisplayName("사람에 대한 커서가 잘 이동하는지 확인한다.")
    void increasePeopleCursor() {
        bowlingGamesCursor.increasePeopleCursor();
        assertThat(bowlingGamesCursor.getPeopleCursor()).isEqualTo(1);
        assertThat(bowlingGamesCursor.getFrameNumberCursor()).isEqualTo(0);
    }

    @Test
    @DisplayName("프레임에 대한 커서가 잘 이동하는지 확인한다.")
    void increaseFrameNumberCursor() {
        bowlingGamesCursor.increaseFrameNumberCursor();
        assertThat(bowlingGamesCursor.getPeopleCursor()).isEqualTo(0);
        assertThat(bowlingGamesCursor.getFrameNumberCursor()).isEqualTo(1);
    }

    @Test
    @DisplayName("사람에 대한 커서가 리셋되는지 확인한다.")
    void resetPeopleCursor() {
        bowlingGamesCursor.increasePeopleCursor();
        assertThat(bowlingGamesCursor.getPeopleCursor()).isEqualTo(1);
        assertThat(bowlingGamesCursor.getFrameNumberCursor()).isEqualTo(0);

        bowlingGamesCursor.resetPeopleCursor();
        assertThat(bowlingGamesCursor.getPeopleCursor()).isEqualTo(0);
        assertThat(bowlingGamesCursor.getFrameNumberCursor()).isEqualTo(0);
    }

}