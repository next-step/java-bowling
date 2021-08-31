package qna.domain;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean deletableBy(User loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }
}
