package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {

    private final List<Answer> answers;
    private final User loginUser;
    public Answers(List<Answer> answers,User loginUser) {
        this.answers = answers;
        this.loginUser = loginUser;
    }

    public void canDelete() throws CannotDeleteException {
        for (Answer answer: this.answers) {
            answer.canDelete(loginUser);
        }
    }
    public void delete() {
        answers.stream()
//                .filter(answer -> answer.canDelete(this.loginUser))
                .forEach(Answer::delete);
    }
}
