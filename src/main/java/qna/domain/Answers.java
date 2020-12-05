package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public DeleteHistories deleteSelf(User loginUser, LocalDateTime deleteDate) throws CannotDeleteException {
        checkHavingOtherAnswerWriter(loginUser);

        return answers.stream()
                .map(answer -> answer.deleteSelf(deleteDate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), DeleteHistories::new));
    }

    private void checkHavingOtherAnswerWriter(User loginUser) throws CannotDeleteException {
        boolean hasOtherAnswerWriter = answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
        if (hasOtherAnswerWriter) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
