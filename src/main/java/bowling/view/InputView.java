package bowling.view;

import bowling.domain.Ball;
import bowling.domain.PlayerName;
import bowling.util.converter.Converter;
import bowling.view.printer.Printer;
import bowling.view.reader.Reader;
import bowling.view.validator.InputValidator;

public class InputView {
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public PlayerName inputPlayerName() {
        printer.printLine("플레이어 이름은 (3 english letters)?:");
        String playerName = reader.readLine();
        validator.validatePlayerName(playerName, "플레이어 이름");
        return new PlayerName(playerName);
    }

    public Ball inputBall(int frameIndex) {
        printer.printLine(frameIndex + 1 + "프레임 투구: ");
        String ball = reader.readLine();
        validator.validateBall(ball, "투구");
        return Ball.from(Converter.convertToInt(ball));
    }
}
