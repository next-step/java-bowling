package qna.domain;


import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }
}
