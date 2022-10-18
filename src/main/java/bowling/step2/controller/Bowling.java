package bowling.step2.controller;

import bowling.step2.domain.Player;
import bowling.step2.domain.PlayersDTO;
import bowling.step2.dto.CountOfFallenPinsDTO;
import bowling.step2.dto.PlayerDTO;
import bowling.step2.view.input.InputView;
import bowling.step2.view.output.ResultView;

import java.util.stream.IntStream;

public class Bowling {
    private static final int MAX_COUNT_OF_FRAME = 10;
    
    public void run() {
        int countOfPlayer = InputView.inputCountOfPlayer();
        PlayersDTO playersDTO = InputView.inputPlayers(countOfPlayer);
        
        ResultView.printDisplaySet();
        ResultView.printAllPlayerFramesDisplay(playersDTO);
        
        playBowl(playersDTO);
    }
    
    private void playBowl(final PlayersDTO playersDTO) {
        IntStream.range(0, MAX_COUNT_OF_FRAME)
                .forEach(count -> allPlayBowl(playersDTO));
    }
    
    private void allPlayBowl(final PlayersDTO playersDTO) {
        playersDTO.getPlayers()
                .forEach(player -> runFrames(playersDTO, player));
    }
    
    private void runFrames(final PlayersDTO playersDTO, final Player player) {
        boolean isRunningCurrentFrame = true;
        while (isRunningCurrentFrame) {
            isRunningCurrentFrame = runCurrentFrame(playersDTO, player);
        }
    }
    
    private boolean runCurrentFrame(final PlayersDTO playersDTO, final Player player) {
        try {
            final CountOfFallenPinsDTO countOfFallenPinsDTO = InputView.countOfFallenPins(new PlayerDTO(player));
            boolean isRunningCurrentFrame = player.bowl(countOfFallenPinsDTO);
            ResultView.printAllPlayerFramesDisplay(playersDTO);
            return isRunningCurrentFrame;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return runCurrentFrame(playersDTO, player);
        }
    }
}
