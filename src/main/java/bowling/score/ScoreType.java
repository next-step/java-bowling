package bowling.score;

import bowling.pitch.Pitch;

import java.util.function.Function;

public enum ScoreType {
    STRIKE((pitch) -> "X"),
    SPARE((pitch) -> "/"),
    GUTTER((pitch) -> "-"),
    MISS((pitch) -> String.valueOf(pitch.getScore())),
    NORMAL((pitch) -> String.valueOf(pitch.getScore()));

    private final Function<Pitch, String> signatureFunction;

    private ScoreType(Function<Pitch, String> signatureFunction) {
        this.signatureFunction = signatureFunction;
    }

    public String getSignature(Pitch pitch) {
        return signatureFunction.apply(pitch);
    }
}
