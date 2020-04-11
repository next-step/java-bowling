package bowling.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {

    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    @DisplayName("이름을 입력받을 수 있다.")
    @Test
    void inputPlayerName() {
        String expect = "otk";

        String actual = inputView.inputPlayerName();

        assertThat(actual).isEqualTo(expect);
    }

}