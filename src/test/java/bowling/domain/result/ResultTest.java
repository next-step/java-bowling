package bowling.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    @DisplayName("마지막 프레임 인지 체크하는 로직 검증")
    void createTest() {
        Result result = new Result(10,"X");
        assertThat(result.isFinalResult()).isTrue();
    }
}
