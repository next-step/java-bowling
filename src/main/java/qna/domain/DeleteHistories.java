package qna.domain;

import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories){
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories){
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
