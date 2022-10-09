package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoTest {
    public static final No NO = new No(9);
    
    @Test
    @DisplayName("넘버 증가")
    void increase_no() {
        assertThat(NO.increase()).isEqualTo(10);
    }
    
    @Test
    @DisplayName("마지막 프레임 반환 차례인지 확인")
    void check_next_final_frame() {
        assertThat(NO.isNextFinalFrame()).isTrue();
    }
}