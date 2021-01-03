package bowling;

import bowling.domain.Bowling;
import bowling.domain.Players;
import bowling.view.InputView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static void main(String[] args) {
        Players players = InputView.getPlayers();
        Bowling bowling = new Bowling(players);
        bowling.games();
    }
}
