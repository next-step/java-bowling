package bowling;

import bowling.domain.Player;
import bowling.view.InputView;

public class JavaBowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Player player = askName(inputView);
        Play play = new Play(inputView, player);
        System.out.println(player);
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
