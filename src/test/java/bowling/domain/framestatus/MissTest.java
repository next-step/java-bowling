package bowling.domain.framestatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MissTest {

    @Test
    @DisplayName("미스 객체 생성")
    void createMiss() {
        Miss miss = new Miss(7);
        Miss miss1 = new Miss(3, 5);
        assertThat(miss.getCurrentScore()).isEqualTo(7);
        assertThat(miss1.getCurrentScore()).isEqualTo(5);
        assertThat(miss1.getPreScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("미스 객체 생성시 예외처리")
    void exceptCreateMiss() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Miss(10));
        assertThatIllegalArgumentException().isThrownBy(() -> new Miss(7, 6));
    }
}
