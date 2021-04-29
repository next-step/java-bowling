package bowling.controller;

import bowling.domain.NumberOfPlayer;
import bowling.domain.Player;
import bowling.dto.PlayerDTO;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.frame.NormalFrame.FIRST_FRAME_NO;
import static bowling.domain.frame.NormalFrame.LAST_FRAME_NO;

public class BowlingController {
    private NumberOfPlayer numberOfPlayer;
    private List<Player> playerList = new ArrayList<>();

    public void run() {
        numberOfPlayer = new NumberOfPlayer(InputView.requestNumberOfPeople());
        for(int i = 1; i <= numberOfPlayer.number(); i++) {
            playerList.add(new Player(InputView.requestPlayerName(i)));
        }

        ResultView.printBoard(playerDTOList());
        for (int i = FIRST_FRAME_NO; i <= LAST_FRAME_NO; i++) {
            playRound(i);
        }
    }

    private List<PlayerDTO> playerDTOList() {
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for(Player player : playerList) {
            playerDTOList.add(player.exportPlayerDTO());
        }
        return playerDTOList;
    }

    private void playRound(int frameNo) {
        for(Player player : playerList) {
            playBowling(player, frameNo);
        }
    }

    private void playBowling(Player player, int frameNo) {
        while(!player.isNthFrameFinished(frameNo)){
            player.bowl(InputView.requestFallingPins(player.name()), frameNo);
            ResultView.printBoard(playerDTOList());
        }
    }
}
