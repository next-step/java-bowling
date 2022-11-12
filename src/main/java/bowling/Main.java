package bowling;

import bowling.domain.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanName());
    }
}
