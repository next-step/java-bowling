package step4.view;

import step4.domain.Player;

public interface InputView {

    String getPlayerName(int index);

    int getPitchesCount(Player player);

    int getNumberOfParticipants();
}
