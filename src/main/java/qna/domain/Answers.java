package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers(Question question) {
        answers = question.getAnswers();
    }

    public Answers() {
    }

    public void checkUser(User loginUser) throws CannotDeleteException {
        int otherPersonCount = (int)answers.stream().filter(answer -> !answer.isOwner(loginUser)).count();
        if (otherPersonCount > 0 ){
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
