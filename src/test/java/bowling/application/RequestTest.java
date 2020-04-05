package bowling.application;

import bowling.domain.state.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    @Test
    @DisplayName("요청 객체 비교")
    void equalsToRequest() {
        // give
        Request request = new Request("KSJ");
        Request expectedRequest = new Request("KSJ");
        // when
        boolean same = request.equals(expectedRequest);
        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("볼링핀 데이터 담기")
    void requestPins() {
        Request request = new Request("KSJ").bowlFallenPins(new Pin(10));
        Request nextRequest = request.bowlFallenPins(new Pin(3));

        assertThat(request.getFallenPins().getFallenPins()).isEqualTo(10);
        assertThat(nextRequest.getFallenPins().getFallenPins()).isEqualTo(3);
    }
}
