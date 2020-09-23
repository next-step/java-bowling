package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import bowling.ui.result.DisplayPlayerBowlingGrade;
import bowling.ui.result.DisplayRolledResult;

import static java.util.stream.Collectors.toList;

final class Player {
    static final String ERROR_MESSAGE = "영어 알파벳 대문자 3자만 등록 가능합니다.";
    public static final String NAME_REGEX = "^[A-Z]{3}$";
    private final String name;
    private final Rolling rolling;

    private Player(String name) {
        verifyName(name);
        this.name = name;
        this.rolling = new Rolling();
    }

    static Player of(String name){
        return new Player(name);
    }

    private void verifyName(String name) {
        if (null == name || !name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    void whileRollAndDisplay(int currentIndex, Function<String, Integer> playerFallenPinsService, Consumer<DisplayPlayerBowlingGrade> displayPlayerBowlingGradeConsumer){
        while(isPlayable() && rolling.hasNextFrameIndex(currentIndex)) {
            int fallenPins = playerFallenPinsService.apply(name);
            roll(fallenPins);
            displayPlayerBowlingGradeConsumer.accept(toDisplayPlayerBowlingGrade());
        }
    }

    public String getName() {
        return name;
    }

    Player roll(int fallenPins){
        rolling.roll(fallenPins);
        return this;
    }

    int getTotalFrameScore(){
        return rolling.getTotalFrameScore();
    }

    boolean isPlayable(){
        return rolling.isPlayable();
    }

    List<String> bowlingGrade(){
        return rolling.framesByRolledResults()
                      .stream()
                      .map(DisplayRolledResult::getDescription)
                      .collect(toList());
    }

    DisplayPlayerBowlingGrade toDisplayPlayerBowlingGrade(){
        return new DisplayPlayerBowlingGrade(name, rolling.framesByRolledResults());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return  name + " : " + bowlingGrade();
    }
}
