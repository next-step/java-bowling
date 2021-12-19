package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }
    public static DeleteHistories of(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>(buildDeleteHistories(question));

        return new DeleteHistories(deleteHistories);
    }

    public static List<DeleteHistory> buildDeleteHistories(Question question) {
        if (Objects.isNull(question)) {
            return Collections.EMPTY_LIST;
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.of(question));
        deleteHistories.addAll(buildAnswerDeleteHistories(question.getAnswers()));

        return deleteHistories;
    }

    public static List<DeleteHistory> buildAnswerDeleteHistories(Answers answers) {
        if (Objects.isNull(answers) || answers.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<Answer> answerList = answers.getAnswers();
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answerList) {
            deleteHistories.add(DeleteHistory.of(answer));
        }

        return deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

}
