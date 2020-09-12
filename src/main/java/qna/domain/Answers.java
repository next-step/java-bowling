package qna.domain;

import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {

    public static final String ERR_HAS_ANOTHER_PERSON_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers ofQuestion(Question question) {
        return new Answers(question.getAnswers());
    }

    public void delete(User loginUser) throws CannotDeleteException {
        verifyAnotherUserAnswer(loginUser);
        answers.forEach(Answer::deleteAtNow);
    }

    public void verifyAnotherUserAnswer(User loginUser) throws CannotDeleteException {
        if (isNotLoginUser(loginUser)) {
            throw new CannotDeleteException(ERR_HAS_ANOTHER_PERSON_ANSWER);
        }
    }

    private boolean isNotLoginUser(User loginUser) {
        return answers.stream()
                      .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public List<Answer> getCollection() {
        return answers;
    }
}
