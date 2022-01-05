package bowling.engine;

public interface ShotsBuilder {
    Shots build();
    boolean validate();
}
