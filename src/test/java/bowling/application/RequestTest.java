package bowling.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    @Test
    @DisplayName("요청 객체 비교")
    void equalsToRequest() {
        // give
        Request request = new Request("KSJ", 10);
        Request expectedRequest = new Request("KSJ", 10);
        // when
        boolean same = request.equals(expectedRequest);
        // then
        assertThat(same).isTrue();
    }
}
