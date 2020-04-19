package bowling.view;

import bowling.domain.frame.FrameNumber;
import bowling.domain.pin.BowlCount;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;
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
        int index = 1;
        Player expect = Player.of(name);
        inputView = new InputView(createInputStream(name));

        Player actual = inputView.inputPlayerName(index);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("투구수를 입력받을 수 있다.")
    @Test
    void inputBowlCount() {
        String bowlCount = "5";
        BowlCount expect = new BowlCount(bowlCount);
        inputView = new InputView(createInputStream(bowlCount));

        BowlCount actual = inputView.inputBowlCount(FrameNumber.ofFirst());

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("플레이어 수를 입력받을 수 있다.")
    @Test
    void inputPlayerCount() {
        String playerCount = "2";
        PlayerCount expect = PlayerCount.valueOf(playerCount);
        inputView = new InputView(createInputStream(playerCount));

        PlayerCount actual = inputView.inputPlayerCount();

        assertThat(actual).isEqualTo(expect);
    }

    ByteArrayInputStream createInputStream(String name) {
        return new ByteArrayInputStream(name.getBytes());
    }
}