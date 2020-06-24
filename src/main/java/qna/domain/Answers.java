package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private static final int USER_CHECK_COUNT = 1;
    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    private void isExistOtherOwner(User user) throws CannotDeleteException {
        if (answers.stream()
                .filter(answer -> !answer.isOwner(user))
                .count() >= USER_CHECK_COUNT) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void deleteALL(User user, DeleteHistories deleteHistories) throws CannotDeleteException {
        isExistOtherOwner(user);
        answers.stream()
                .forEach(answer -> {
                    answer.setDeleted(true);
                    deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
                });
    }

}
