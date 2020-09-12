package qna.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    public DeleteHistories(Question question, Answers answers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
    }

    public void addDeleteQuestionHistory(Question question) {
        // todo 여기
        // deleteHistories.add(question.delete());
    }

    public void addDeleteAnswersHistories(Answers answers) {
        deleteHistories.addAll(answers.delete());
    }

    public Collection<DeleteHistory> getCollection() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
