package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answerList) {
        this.answers = new ArrayList<>(answerList);
    }

    public int size() {
        return this.answers.size();
    }

    public void delete(User questionUser) throws CannotDeleteException {
        long count = answers.stream().filter(answer -> !answer.getWriter().equalsNameAndEmail(questionUser)).count();
        if (count > 0) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        answers.forEach(Answer::delete);
    }
}
