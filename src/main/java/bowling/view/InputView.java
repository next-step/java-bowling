package bowling.view;


import bowling.domain.Player;
import bowling.utils.InputUtils;

public class InputView {
    public static String inputPlayerName() {
        return InputUtils.scan("플레이어 이름은(3 english letters)?: ");
    }

    public static int inputScore(Player player, int frameIndex) {
        String score = InputUtils.scan(player.getName() + "'s " + frameIndex + "프레임 투구: ");
        return Integer.parseInt(score);
    }

    public static int inputNumberOfPlayers() {
        String countOfPlayers = InputUtils.scan("How many people? ");
        return Integer.parseInt(countOfPlayers);
    }
}
