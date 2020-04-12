package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import bowling.domain.Player;
import bowling.view.InputView;

public class Play {
    private static final int FINAL_FRAME = 10;
    private static FinalFrame finalFrame = new FinalFrame();
    private static NormalFrame normalFrame = new NormalFrame();

    public Play(InputView inputView, Player player) {
        normalPlay(inputView, player);
        finalPlay(inputView, player);
    }

    private void finalPlay(InputView inputView, Player player) {
        try{
            int numberOfPin = inputView.askNumberOfPin(FINAL_FRAME);
            finalFrame.add(numberOfPin);
        } catch(NumberFormatException e){
            reRunFinal(inputView, player, e.getMessage());
        } catch (IllegalArgumentException e){
            reRunFinal(inputView, player, e.getMessage());
        } finally {
            isEndFinalFrame(inputView, player);
        }
    }

    private void isEndFinalFrame(InputView inputView, Player player) {
        if(!player.isEndFinalFrame(finalFrame)){
            finalPlay(inputView, player);
        }
    }

    private void reRunFinal(InputView inputView, Player player, String message) {
        System.out.println(message);
        finalFrame = new FinalFrame();
        finalPlay(inputView, player);
    }

    private void normalPlay(InputView inputView, Player player) {
        try{
            int numberOfPin = inputView.askNumberOfPin(player.currentFrame());
            normalFrame.add(numberOfPin);
        } catch(NumberFormatException e){
            initNormalFrame(e.getMessage());
        } catch (IllegalArgumentException e){
            initNormalFrame(e.getMessage());
        } finally {
            if(normalFrame.isNextFrame()){
                player.addFrame(normalFrame);
                normalFrame = new NormalFrame();
            }
            if (player.isEndNormalFrame()) return;
            normalPlay(inputView, player);
        }
    }

    private void initNormalFrame(String message) {
        System.out.println(message);
        normalFrame = new NormalFrame();
    }
}
