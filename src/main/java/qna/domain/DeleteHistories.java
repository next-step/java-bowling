package qna.domain;

import java.util.ArrayList;
import java.util.List;


public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(Question question) {
        ArrayList<DeleteHistory> list = new ArrayList<>();
        list.add(DeleteHistory.ofQuestion(question));
        for (Answer answer : question.getAnswers()) {
            list.add(DeleteHistory.ofAnswer(answer));
        }
        return new DeleteHistories(list);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
