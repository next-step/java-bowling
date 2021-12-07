package bowling.view;

public interface InputView {
    void questionPlayerName();
    String playerName();
    void questionPinNumber(int frame);
    int pinNumber();
}
