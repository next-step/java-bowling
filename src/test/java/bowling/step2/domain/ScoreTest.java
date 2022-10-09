package bowling.step2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScoreTest {
    public Score score;
    
    @BeforeEach
    void setUp() {
        score = new Score(6);
    }
    
    @Test
    @DisplayName("점수 더하기")
    void add() {
        assertThat(score.add(3)).isEqualTo(9);
    }
    
    @Test
    @DisplayName("핀이 전부 쓰러졌는지 확인")
    void check_all_fallen_pins() {
        assertThat(score.isAllFallenPins(4)).isTrue();
    }
    
    @Test
    @DisplayName("쓰러진 핀의 개수가 최대 핀 개수 초과 시 예외")
    void count_of_max_pins_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> score.isAllFallenPins(5))
                .withMessage("핀 최대 개수를 초과하였습니다. 다시 입력해주세요.");
    }
    
    @Test
    @DisplayName("display 반환 값 확인")
    void display() {
        assertThat(score.display()).isEqualTo("6");
    }
}