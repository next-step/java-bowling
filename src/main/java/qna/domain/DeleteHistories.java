package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories from(Question question) {
        if (question == null) {
            return new DeleteHistories(new ArrayList<>());
        }
        return new DeleteHistories(addDeleteHistories(question));
    }

    private static List<DeleteHistory> addDeleteHistories(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        List<Answer> answers = question.getAnswers();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    public List<DeleteHistory> deleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    public int size() {
        return deleteHistories.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
            "deleteHistories=" + deleteHistories +
            '}';
    }

}
