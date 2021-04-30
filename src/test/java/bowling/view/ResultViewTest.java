package bowling.view;

import bowling.domain.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultViewTest {
    @DisplayName("스트라이크는 X로 출력")
    @Test
    void strike() {
        Frame frame = new Frame(1);
        frame.pitch(10);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("X");
    }

    @DisplayName("스페어는 N|/로 출력")
    @Test
    void spare() {
        Frame frame = new Frame(1);
        frame.pitch(8);
        frame.pitch(2);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("8|/");
    }

    @DisplayName("오픈은 N|N로 출력")
    @Test
    void open() {
        Frame frame = new Frame(1);
        frame.pitch(4);
        frame.pitch(2);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("4|2");
    }

    @DisplayName("마지막 프레임, 스트라이크는 X로 출력")
    @Test
    void final_strike() {
        Frame frame = new Frame(10);
        frame.pitch(10);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("X");
    }

    @DisplayName("마지막 프레임, 더블은 X|X로 출력")
    @Test
    void final_double() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(10);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("X|X");
    }

    @DisplayName("마지막 프레임, 터키는 X|X|X로 출력")
    @Test
    void final_turkey() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(10);
        frame.pitch(10);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("X|X|X");
    }

    @DisplayName("마지막 프레임, 스페어 후 오픈은 N|/|N로 출력")
    @Test
    void final_spare_open() {
        Frame frame = new Frame(10);
        frame.pitch(7);
        frame.pitch(3);
        frame.pitch(0);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("7|/|-");
    }

    @DisplayName("마지막 프레임, 스페어 후 스트라이크는 N|/|X로 출력")
    @Test
    void final_spare_spare() {
        Frame frame = new Frame(10);
        frame.pitch(7);
        frame.pitch(3);
        frame.pitch(10);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("7|/|X");
    }

    @DisplayName("마지막 프레임, 스트라이크 후 스페어는 X|N|X로 출력")
    @Test
    void final_strike_spare() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(8);
        frame.pitch(2);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("X|8|/");
    }

    @DisplayName("마지막 프레임, 오픈은 N|N로 출력")
    @Test
    void final_open() {
        Frame frame = new Frame(10);
        frame.pitch(4);
        frame.pitch(2);

        ResultView resultView = new ResultView();
        assertThat(resultView.frameResult(frame)).isEqualTo("4|2");
    }
}
