package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;
    private final User loginUser;

    private Answers(List<Answer> answers, User loginUser) {
        this.answers = answers;
        this.loginUser = loginUser;
    }

    public static Answers of(List<Answer> answers, User loginUser) {
        return new Answers(answers,loginUser);
    }

    public void canDelete() throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.canDelete(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Answer> delete() throws CannotDeleteException {
        canDelete();
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

//    public Answers delete2() {
//        return answers.stream()
//                .map(Answer::delete)
//                .collect(Collector.of(Answers.of(answers,loginUser)));
//    }
}
