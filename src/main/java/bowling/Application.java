package bowling;

import bowling.domain.Bowling;
import bowling.domain.Players;
import bowling.domain.frame.Frames;
import bowling.view.InputView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static void main(String[] args) {
        Players players = InputView.getPlayers();
        Frames frames = new Frames();
        Bowling bowling = new Bowling(players, frames);
        bowling.game();
    }
}
