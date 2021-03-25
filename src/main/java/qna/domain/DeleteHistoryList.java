package qna.domain;

import java.util.ArrayList;
import java.util.List;


public class DeleteHistoryList {

    private final List<DeleteHistory> deleteHistoryList;

    public DeleteHistoryList(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    public static DeleteHistoryList of(Question question) {
        ArrayList<DeleteHistory> list = new ArrayList<>();
        list.add(DeleteHistory.ofQuestion(question));
        for (Answer answer : question.getAnswerList()) {
            list.add(DeleteHistory.ofAnswer(answer));
        }
        return new DeleteHistoryList(list);
    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return deleteHistoryList;
    }
}
