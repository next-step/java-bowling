package bowling.view;

import bowling.domain.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {

    private InputView inputView;

    @DisplayName("이름을 입력받을 수 있다.")
    @Test
    void inputPlayerName() {
        String name = "otk";
        PlayerName expect = new PlayerName(name);
        inputView = new InputView(createInputStream(name));

        PlayerName actual = inputView.inputPlayerName();

        assertThat(actual).isEqualTo(expect);
    }

    ByteArrayInputStream createInputStream(String name) {
        return new ByteArrayInputStream(name.getBytes());
    }
}