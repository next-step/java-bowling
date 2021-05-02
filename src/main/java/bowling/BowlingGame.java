package bowling;

import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BowlingGame {
    private static final int LAST_ROUND = 10;
    private LinkedList<Player> playerList;
    private int round = 1;

    public BowlingGame(LinkedList<Player> playerList) {
        this.playerList = playerList;
    }

    public void bowl(Pins pitch) {
        currentPlayer().bowl(pitch, round);
    }

    public Player currentPlayer() {
        Optional<Player> playerOptional = playerList.stream()
                .filter(player -> !player.isNthFrameFinished(round))
                .findFirst();
        if(playerOptional.isPresent()){
            return playerOptional.get();
        }
        round++;
        return currentPlayer();
    }

    public boolean isFinished() {
        return playerList.getLast().isNthFrameFinished(LAST_ROUND);
    }

    public List<PlayerDTO> exportPlayerDTOList() {
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for (Player player : playerList) {
            playerDTOList.add(player.exportPlayerDTO());
        }
        return playerDTOList;
    }
}
