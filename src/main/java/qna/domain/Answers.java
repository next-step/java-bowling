package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete() {
        final List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (final Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }

        return deleteHistories;
    }

    public boolean isOwner(User writer) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }
}