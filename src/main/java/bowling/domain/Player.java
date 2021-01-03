package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created : 2020-12-16 오전 7:55
 * Developer : Seo
 */
public class Player {
    public static final String NAME_PATTERN = "^([A-z]{3})$";

    private final String name;
    private final Frames frames;

    public Player(String name) {
        validate(name);
        this.name = name;
        this.frames = new Frames();
    }

    private void validate(String name) {
        Pattern p = Pattern.compile(NAME_PATTERN);
        Matcher m = p.matcher(name);
        if (!m.matches()) {
            throw new IllegalArgumentException("3자 영문으로 입력해주십시요.");
        }
    }

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame stroke(int frameNo, Pins pins) {
        Frame frame = frames.get();
        Frame newFrame = frame.stroke(frameNo, pins);
        if (!frame.equals(newFrame)) {
            this.frames.add(newFrame);
        }
        return newFrame;
    }

    public void spare(int frameNo, Pins pins) {
        Frame frame = frames.get();
        Frame newFrame = frame.spare(frameNo, pins);
        if (!frame.equals(newFrame)) {
            this.frames.add(newFrame);
        }
    }
}
