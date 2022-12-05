package bowling.domain;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Player {
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z]{3}$");
    private final String name;
    private final Frames frames;

    public Player(String name) {
        if (!pattern.matcher(name).matches()) {
            throw new IllegalArgumentException("이름은 3글자, 영문이어야 합니다.");
        }
        this.name = name;
        this.frames = new Frames();
    }

    public String getName() {
        return name;
    }

    public boolean bowl(Pin pin) {
        return frames.bowl(pin.pinToPoint());
    }

    public List<List<Integer>> getPointsOfFrames() {
        return frames.getPointsOfFrames()
                .stream()
                .map(points -> points.stream()
                        .map(Point::getPoint)
                        .collect(toUnmodifiableList()))
                .collect(toUnmodifiableList());
    }

    public List<Integer> getTotalPointsOfFrames() {
        return frames.getTotalPointsOfFrames()
                .stream()
                .map(Point::getPoint)
                .collect(toUnmodifiableList());
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

}
