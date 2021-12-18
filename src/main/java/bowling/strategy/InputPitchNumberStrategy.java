package bowling.strategy;

import bowling.view.InputView;

public class InputPitchNumberStrategy implements PitchNumberStrategy {

    @Override
    public int generate(int bound) {
        int number = InputView.inputPitchNumber();
        validateBound(number, bound);
        return number;
    }

    private void validateBound(int number, int bound) {
        if (number > bound) {
            throw new IllegalArgumentException("입력 가능 숫자 범위를 벗어났습니다.");
        }
    }
}
