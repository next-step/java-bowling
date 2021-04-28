package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.*;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers,User loginUsers) throws CannotDeleteException {
        owenerValidate(answers, loginUsers);

        this.answers = answers;
    }

    private void owenerValidate(List<Answer> answers, User loginUser) throws CannotDeleteException {
        boolean isOwner = answers.stream()
                .anyMatch(answer -> answer.isOwner(loginUser));

        if (!isOwner) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void deletedAnswers(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            answer.setDeleted(true);

            deleteHistories.add(
                    new DeleteHistory(
                            ContentType.ANSWER,
                            answer.getId(),
                            answer.getWriter(),
                            LocalDateTime.now()));
        }
    }
}
