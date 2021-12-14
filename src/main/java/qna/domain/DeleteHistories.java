package qna.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistories extends FirstClassCollection<DeleteHistory> {

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        super(deleteHistories);
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        if (deleteHistories == null) {
            throw new IllegalArgumentException("invalid list: cannot be null");
        }

        return new DeleteHistories(deleteHistories);
    }

    public DeleteHistories prepend(DeleteHistory deleteHistory) {
        if (deleteHistory == null) {
            throw new IllegalArgumentException("invalid delete history: cannot be null");
        }

        return new DeleteHistories(Stream.concat(Stream.of(deleteHistory), stream())
                .collect(Collectors.toList()));
    }
}
