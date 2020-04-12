package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import bowling.domain.Player;
import bowling.view.InputView;

public class JavaBowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Player player = askName(inputView);

        player = play(inputView, player);

        System.out.println(player.getFrames());
    }

    private static Player play(InputView inputView, Player player) {
        while(true){
            NormalFrame normalFrame = new NormalFrame();
            int numberOfPin = inputView.askNumberOfPin(player.currentFrame());
            normalFrame.add(numberOfPin);
            if(normalFrame.isNextFrame()){
                player.addFrame(normalFrame);
                if(player.isEndNormalFrame()){
                    break;
                }
                continue;
            }
            numberOfPin = inputView.askNumberOfPin(player.currentFrame());
            normalFrame.add(numberOfPin);
            player.addFrame(normalFrame);
            if(player.isEndNormalFrame()){
                break;
            }
        }
        FinalFrame finalFrame = new FinalFrame();
        while(true) {
            int numberOfPin = inputView.askNumberOfPin(10);
            finalFrame.add(numberOfPin);
            player.addFrame(finalFrame);
            if(player.isEndFinalFrame()){
                break;
            }
        }

        return player;
    }

    private static Player askName(InputView inputView) {
        Player player = null;
        try{
            String name = inputView.askName();
            player = new Player(name);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            askName(inputView);
        }
        return player;
    }
}
