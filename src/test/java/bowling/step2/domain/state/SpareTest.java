package bowling.step2.domain.state;

import bowling.step2.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {
    public Spare spare;
    
    @BeforeEach
    void setUp() {
        spare = new Spare(new Score(4));
    }
    
    @Test
    @DisplayName("투구 시 예외")
    void bowl_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> spare.bowl(1))
                .withMessage("더이상 투구할 수 없습니다.");
    }
    
    @Test
    @DisplayName("프레임 종료")
    void frame_finish() {
        assertThat(spare.isFinished()).isTrue();
    }
    
    @Test
    @DisplayName("Spare 인지 확인")
    void is_spare() {
        assertThat(spare.isSpare()).isTrue();
    }
    
    @Test
    @DisplayName("display 반환 값 확인")
    void display() {
        assertThat(spare.display()).isEqualTo("4|/");
    }
}