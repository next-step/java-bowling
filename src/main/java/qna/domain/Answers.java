package qna.domain;

import qna.CannotDeleteException;
import qna.ExistTheOthersAnswerException;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers){
        return new Answers(answers);
    }

    public int countAnswers(){
        return answers.size();
    }

    public void delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        checkAnswers(loginUser);
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory.Builder()
                                                 .contentType(ContentType.ANSWER)
                                                 .contentId(answer.getId())
                                                 .deletedBy(answer.getWriter())
                                                 .createDate(LocalDateTime.now())
                                                 .build());
        }
    }

    private void checkAnswers(User loginUser) throws CannotDeleteException {
        if (isExistOthersAnswer(loginUser)) {
            throw new ExistTheOthersAnswerException();
        }
    }

    private boolean isExistOthersAnswer(User loginUser) {
        return answers.stream()
                      .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
