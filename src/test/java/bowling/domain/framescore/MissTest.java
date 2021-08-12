package bowling.domain.framescore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {
    @DisplayName("Miss는 항상 출력한다.")
    @Test
    void isShowScoreValue() {
        assertThat(Miss.instance().isShowScoreValue())
                .isTrue();
    }

    @DisplayName("Miss의 isCompleted는 항상 True다.")
    @Test
    void isCompleted() {
        assertThat(Miss.instance().isCompleted())
                .isTrue();
    }
}