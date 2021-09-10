package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    private static final Pattern NAME_VALID_PATTERN = Pattern.compile("^[a-zA-Z]{1,3}$");

    private final String username;
    private List<Frame> frames;

    public Game(String username) {
        Matcher nameMatcher = NAME_VALID_PATTERN.matcher(username);
        if (!nameMatcher.matches()) {
            throw new IllegalArgumentException("잘못된 유형의 이름이 들어왔습니다.");
        }

        this.username = username;

        initFrames();
    }

    public void initFrames() {
        this.frames = IntStream.range(0, 9)
                .mapToObj((index) -> new NormalFrame())
                .collect(Collectors.toList());
        this.frames.add(new LastFrame());
    }

    public void play(Shot shot) {
        if (isFinished()) {
            throw new IllegalStateException("Game is already finished");
        }

        Frame currentFrame = frames.stream().filter((frame -> !frame.isFinished()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All frame is finished"));

        currentFrame.playShot(shot);
    }

    public boolean isFinished() {
        return !this.frames
                .stream()
                .anyMatch((frame) -> !frame.isFinished());
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public int getCurrentFrameIndex() {
        return (int)frames.stream().filter((frame -> frame.isFinished())).count();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public String getUsername() {
        return username;
    }
}
