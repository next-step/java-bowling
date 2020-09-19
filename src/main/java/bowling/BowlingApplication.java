package bowling;

import bowling.domain.Bowlings;
import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.List;

public class BowlingApplication {

  public static void main(String[] args) {
    InputView inputView = new InputView();
    int numberOfPlayers = inputView.requestNumberOfPlayers();

    List<String> names = inputView.requestNames(numberOfPlayers);

    OutputView outputView = new OutputView(names);
    outputView.render();

    Bowlings bowlings = Bowlings.ofNames(names);

    while (bowlings.isPlaying()) {
      bowlings.roll(inputView.requestPinsOf(bowlings.nextPlayer()));
      outputView.render(bowlings);
    }

  }

}
