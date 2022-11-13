package bowling.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {
    public static final int FRAME_COUNT = 10;

    private final User user;
    private Pitches pitches;

    public Frames(final User user) {
        this(user, new ArrayList<>());
    }

    public Frames(final User user, final List<Pitch> pitches) {
        this.user = user;
        this.pitches = new Pitches(pitches);
    }

    public List<Integer> getFrameNumbers() {
        return IntStream.rangeClosed(1, FRAME_COUNT).boxed().collect(toList());
    }

    public List<Pitch> getPitches() {
        return pitches.getPitches();
    }

    public List<Pitch> getPitches(Pitch lastPitch) {
        return pitches.getPitches(lastPitch);
    }

    public String getUserName() {
        return user.getName();
    }

    public Frames create() {
        pitches.add(getFrameNumbers().stream()
                                     .flatMap(number -> Frame.create(number).getPitches().stream())
                                     .collect(toList()));
        return this;
    }
}
