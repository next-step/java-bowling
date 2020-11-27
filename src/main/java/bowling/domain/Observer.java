package bowling.domain;

public interface Observer<T> {
    void update(Subject<T> subject);
}

