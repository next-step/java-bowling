package step2.view;

import step2.domain.GameHistories;
import step2.domain.Player;
import step2.domain.dto.PlayerDTO;

public class ConsoleViewImpl implements View{
    private final InputView inputView;
    private final ResultView resultView;

    public ConsoleViewImpl(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    @Override
    public String getPlayerName() {
        try{
            String playerName = inputView.getPlayerName();
            Player.isValidName(playerName);
            return playerName;
        }catch(IllegalArgumentException error){
            System.out.println(error.getMessage());
            return getPlayerName();
        }
    }

    @Override
    public void drawHeaderFrame(int frameNo, int pitchesCount) {
        try{
            resultView.drawHeaderFrame(frameNo, pitchesCount);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void drawFrames(PlayerDTO playerDTO) {
        try{
            resultView.drawFrames(playerDTO);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void drawFrames(GameHistories histories) {
        try{
            resultView.drawFrames(histories);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void drawEmptyLine(String name) {
        try{
            resultView.drawEmptyLine(name);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }
}
