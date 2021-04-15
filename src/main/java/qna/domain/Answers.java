package qna.domain;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteAll(User user) {
    }

    public boolean isdeleted() {
        return false;
    }
}
