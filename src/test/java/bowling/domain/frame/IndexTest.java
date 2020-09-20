package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexTest {

    @Test
    @DisplayName("첫번째 인덱스 값 확인 테스트")
    void first_test() {
        assertThat(Index.first()).isEqualTo(Index.of(1));
    }

    @Test
    @DisplayName("인덱스 증가 increment() 메소드 확인 테스트")
    void increment_test() {
        assertThat(Index.first().increment()).isEqualTo(Index.of(2));
    }

    @Test
    @DisplayName("마지막 인덱스 값 확인 테스트")
    void isLast_test() {
        assertThat(Index.of(9).isLast()).isTrue();
    }
}
