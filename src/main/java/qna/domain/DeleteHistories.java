package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(final List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public static DeleteHistories of(final Question question, final Answers answers) {
        return createHistory(question, answers);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    private static DeleteHistories createHistory(final Question question, final Answers answers) {
        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(createQuestionHistory(question));

        List<DeleteHistory> answerHistory = createAnswerHistory(answers);
        histories.addAll(answerHistory);
        return new DeleteHistories(histories);
    }

    private static DeleteHistory createQuestionHistory(final Question question) {
        return new DeleteHistory(question);
    }

    private static List<DeleteHistory> createAnswerHistory(final Answers answers) {
        return answers.getAnswers()
                      .stream()
                      .map(DeleteHistory::new)
                      .collect(Collectors.toList());
    }
}
