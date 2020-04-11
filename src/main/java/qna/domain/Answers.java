package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers(Question question) {
        answers = (List<Answer>) question.getAnswers();
    }

    public void checkUser(User loginUser) throws CannotDeleteException {
        if (isOtherPersonWrite(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOtherPersonWrite(User loginUser) {
        return answers.stream()
                .noneMatch(answer -> answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete() {
        answers.forEach(answer -> answer.delete());
    }

    public List<DeleteHistory> deleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        return answers.stream()
                .map(answer->DeleteHistory.deleteAnswer(answer))
                .collect(Collectors.toList());
    }

    public void deleteAfterCheck(User loginUser) throws CannotDeleteException {
        checkUser(loginUser);
        delete();
    }
}
