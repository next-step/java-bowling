package bowling.view;

import java.util.List;

public interface InputView {

    int receiveNumberOfPlayers();

    List<String> receivePlayerNames(int numberOfPlayers);

    int receiveNumberOfKnockedDownPins(String playerName);

}
