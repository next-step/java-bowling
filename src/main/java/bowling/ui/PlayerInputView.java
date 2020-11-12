package bowling.ui;

public class PlayerInputView extends ConsoleInputView<String> {
    private static final String PLAYER_NAME_PHRASE = "플레이어 이름은(3 english letters)?";

    @Override
    public String getConsoleInput() {
        return SCANNER.nextLine();
    }

    @Override
    public void print() {
        System.out.println(PLAYER_NAME_PHRASE);
    }
}
