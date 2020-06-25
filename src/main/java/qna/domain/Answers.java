package qna.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isAllOwner(User other) {
        for (Answer answer : answers) {
            if (!answer.isOwner(other)) {
                return false;
            }
        }
        return true;
    }

    public List<DeleteHistory> deleteAllAndGetHistory() {
        return answers.stream().map(Answer::deleteAndGetHistory).collect(Collectors.toList());
    }
}
