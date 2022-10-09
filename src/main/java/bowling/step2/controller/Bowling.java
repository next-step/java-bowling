package bowling.step2.controller;

import bowling.step2.domain.Player;
import bowling.step2.dto.CountOfFallenPinsDTO;
import bowling.step2.dto.PlayerDTO;
import bowling.step2.view.input.InputView;
import bowling.step2.view.output.ResultView;

public class Bowling {
    private static final int MAX_COUNT_OF_FRAME = 10;
    private static final int MIN_COUNT_OF_FRAME = 1;
    
    public void run() {
        final Player player = InputView.inputPlayer();
        ResultView.printPlayerFramesDisplay(new PlayerDTO(player));
        
        playBowl(player);
    }
    
    private void playBowl(final Player player) {
        for (int countOfFrame = MIN_COUNT_OF_FRAME; countOfFrame <= MAX_COUNT_OF_FRAME; countOfFrame++) {
            runFrames(player, countOfFrame);
        }
    }
    
    private void runFrames(final Player player, final int countOfFrame) {
        boolean isRunningCurrentFrame = true;
        while (isRunningCurrentFrame) {
            isRunningCurrentFrame = isRunningCurrentFrame(player, countOfFrame, true);
        }
    }
    
    private boolean isRunningCurrentFrame(final Player player, final int countOfFrame, boolean isRunningCurrentFrame) {
        try {
            final CountOfFallenPinsDTO countOfFallenPinsDTO = InputView.countOfFallenPins(countOfFrame);
            isRunningCurrentFrame = player.bowl(countOfFallenPinsDTO);
            ResultView.printPlayerFramesDisplay(new PlayerDTO(player));
            return isRunningCurrentFrame;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isRunningCurrentFrame(player, countOfFrame, isRunningCurrentFrame);
        }
    }
}
