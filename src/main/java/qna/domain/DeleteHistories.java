package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(Question question, Answers answers) {
        deleteQuestiin(question);
        deleteAnswer(answers);
    }

    private void deleteQuestiin(Question question) {
        deleteHistories.add(new DeleteHistory(question));
    }

    public void deleteAnswer(Answers answers) {
        answers.getAnswers()
                .stream()
                .forEach(answer -> deleteHistories.add(new DeleteHistory(answer)));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
