package step4.view;

import step4.domain.GameHistories;
import step4.domain.Player;

import java.util.List;

public class ConsoleViewImpl implements View{
    private final InputView inputView;
    private final ResultView resultView;

    public ConsoleViewImpl(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    @Override
    public String getPlayerName(int index) {
        try{
            String playerName = inputView.getPlayerName(index);
            Player.isValidName(playerName);
            return playerName;
        }catch(IllegalArgumentException error){
            System.out.println(error.getMessage());
            return getPlayerName(index);
        }
    }

    @Override
    public int getPitchesCount(Player player) {
        try{
            return inputView.getPitchesCount(player);
        }catch(Exception error){
            System.out.println(error.getMessage());
            return getPitchesCount(player);
        }
    }

    @Override
    public int getNumberOfParticipants() {
        try{
            return inputView.getNumberOfParticipants();
        }catch(Exception error){
            System.out.println(error.getMessage());
            return getNumberOfParticipants();
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
    public void drawFrame(GameHistories histories) {
        try{
            resultView.drawFrame(histories);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void drawEmptyLine(List<String> names) {
        try{
            resultView.drawEmptyLine(names);
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
    }
}
