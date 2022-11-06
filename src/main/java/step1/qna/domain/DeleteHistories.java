package step1.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(DeleteHistory deleteHistory, List<DeleteHistory> deleteHistories) {
        this.deleteHistories.add(deleteHistory);
        this.deleteHistories.addAll(deleteHistories);
    }

    public static DeleteHistories of(DeleteHistory deleteQuestionHistory,
        List<DeleteHistory> deleteAnswerHistories) {
        return new DeleteHistories(deleteQuestionHistory, deleteAnswerHistories);
    }

    public List<DeleteHistory> value() {
        return deleteHistories;
    }
}
