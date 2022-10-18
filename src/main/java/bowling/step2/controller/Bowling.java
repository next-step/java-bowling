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
        int countOfPlayer = InputView.inputCountOfPlayer();
        final Player player = InputView.inputPlayer();
        ResultView.printPlayerFramesDisplay(new PlayerDTO(player));
        
        playBowl(player);
    }
    
    private void playBowl(final Player player) {
        for (int countOfFrame = MIN_COUNT_OF_FRAME; countOfFrame <= MAX_COUNT_OF_FRAME; countOfFrame++) {
            // 여기서 플레이어 리스트 돌기
            runFrames(player, countOfFrame);
        }
    }
    
    private void runFrames(final Player player, final int countOfFrame) {
        boolean isRunningCurrentFrame = true;
        while (isRunningCurrentFrame) {
            isRunningCurrentFrame = runCurrentFrame(player, countOfFrame, true);
        }
    }
    
    private boolean runCurrentFrame(final Player player, final int countOfFrame, boolean isRunningCurrentFrame) {
        try {
            final CountOfFallenPinsDTO countOfFallenPinsDTO = InputView.countOfFallenPins(countOfFrame);
            isRunningCurrentFrame = player.bowl(countOfFallenPinsDTO);
            ResultView.printPlayerFramesDisplay(new PlayerDTO(player));
            return isRunningCurrentFrame;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return runCurrentFrame(player, countOfFrame, isRunningCurrentFrame);
        }
    }
}
