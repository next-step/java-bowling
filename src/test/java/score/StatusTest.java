package score;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @ParameterizedTest
    @CsvSource(value = {"10,10,STRIKE", "5,10,SPARE", "5,6,MISS", "0,5,GUTTER"})
    void 점수로_상태값_찾기(int score, int sum, String statusName) {
        Status status = Status.findStatus(score, sum);

        assertThat(status).isEqualTo(Status.valueOf(statusName));
    }
}