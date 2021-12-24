package bowling.domain.state.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.end.Miss;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

    private Miss miss;

    @BeforeEach
    void init() {

        miss = new Miss();
    }

    @Test
    @DisplayName("Miss 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(miss.getPrintMark()).isEqualTo("-");
    }

}