package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void write(Question question) {
        writeForQuestion(question);
        writeForAnswers(question.getAnswers());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    private void writeForQuestion(Question question) {
        if (Objects.isNull(question)) {
            return;
        }

        deleteHistories.add(DeleteHistory.of(question));
    }

    private void writeForAnswers(Answers answers) {
        if (Objects.isNull(answers) || answers.isEmpty()) {
            return;
        }

        List<Answer> answerList = answers.getAnswers();
        for(Answer answer : answerList) {
            deleteHistories.add(DeleteHistory.of(answer));
        }
    }


}
