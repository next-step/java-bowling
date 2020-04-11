package bowling.view;

public class InputView {
    private static final String WHAT_IS_YOUR_NAME = "플레이어 이름은(3 english letters)?: ";

    public String inputPlayerName() {
        System.out.print(WHAT_IS_YOUR_NAME);
        return "otk";
    }
}
