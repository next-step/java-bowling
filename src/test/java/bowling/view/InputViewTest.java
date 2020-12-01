package bowling.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("입력 테스트")
public class InputViewTest {

    @DisplayName("참여자 명수 입력")
    @Test
    public void enterMemberCount() {
        InputView inputView = new InputView(new Scanner("2"), new PrintWriter(new StringWriter()));

        assertThat(inputView.enterMemberCount()).isEqualTo(2);
    }

    @DisplayName("참여자 명수 입력시 문구")
    @Test
    public void enterMemberCountPhrase() {
        StringWriter output = new StringWriter();
        InputView inputView = new InputView(new Scanner("2"), new PrintWriter(output));

        inputView.enterMemberCount();

        assertThat(output.toString()).isEqualTo("How many people? \n");
    }

    @DisplayName("참여자 이름 입력")
    @Test
    public void enterMemberName() {
        InputView inputView = new InputView(new Scanner("ABC"), new PrintWriter(new StringWriter()));

        assertThat(inputView.enterMemberName(1)).isEqualTo("ABC");
    }

    @DisplayName("참여자 이름 입력시 문구")
    @Test
    public void enterMemberNamePhrase() {
        StringWriter output = new StringWriter();
        InputView inputView = new InputView(new Scanner("ABC"), new PrintWriter(output));

        inputView.enterMemberName(1);

        assertThat(output.toString()).isEqualTo("플레이어 1의 이름은(3 english letters)?: \n");
    }

    @DisplayName("참여자 투구 점수 입력")
    @Test
    public void enterScore() {
        InputView inputView = new InputView(new Scanner("3"), new PrintWriter(new StringWriter()));

        assertThat(inputView.enterScore("PJS")).isEqualTo(3);
    }

    @DisplayName("참여자 투구 점수 입력시 문구")
    @Test
    public void enterScorePhrase() {
        StringWriter output = new StringWriter();
        InputView inputView = new InputView(new Scanner("3"), new PrintWriter(output));

        inputView.enterScore("PJS");

        assertThat(output.toString()).isEqualTo("PJS's turn : \n");
    }
}