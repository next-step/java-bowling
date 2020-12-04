package step3.view;

import step3.domain.Frame;
import step3.domain.Player;

import java.util.List;

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
    public int getPitchesCount(Frame frame) {
        try{
            int pitchesCount = inputView.getPitchesCount(frame);
            return pitchesCount;
        }catch(Exception error){
            System.out.println(error.getMessage());
            return getPitchesCount(frame);
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
    public void drawFrame(Player player, List<String> marks) {
        try{
            resultView.drawFrame(player, marks);
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
