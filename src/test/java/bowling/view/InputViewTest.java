package bowling.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("입력 테스트")
public class InputViewTest {
    @DisplayName("참여자 이름 입력")
    @Test
    public void enterMemberName() {
        InputView inputView = new InputView(new Scanner("ABC"), new PrintWriter(new StringWriter()));

        assertThat(inputView.enterMemberName()).isEqualTo("ABC");
    }

    @DisplayName("참여자 이름 입력시 문구")
    @Test
    public void enterMemberNamePhrase() {
        StringWriter output = new StringWriter();
        InputView inputView = new InputView(new Scanner("ABC"), new PrintWriter(output));

        inputView.enterMemberName();

        assertThat(output.toString()).isEqualTo("플레이어 이름은(3 english letters)?: \n");
    }

    @DisplayName("참여자 투구 점수 입력")
    @Test
    public void enterScore() {
        InputView inputView = new InputView(new Scanner("3"), new PrintWriter(new StringWriter()));

        assertThat(inputView.enterScore(1)).isEqualTo(3);
    }

    @DisplayName("참여자 투구 점수 입력시 문구")
    @Test
    public void enterScorePhrase() {
        StringWriter output = new StringWriter();
        InputView inputView = new InputView(new Scanner("3"), new PrintWriter(output));

        inputView.enterScore(1);

        assertThat(output.toString()).isEqualTo("1프레임 투구 : \n");
    }
}