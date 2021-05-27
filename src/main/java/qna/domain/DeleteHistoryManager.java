package qna.domain;

public class DeleteHistoryManager {
  private final Answers answers;
  private final Question question;

  public DeleteHistoryManager(Answers answers, Question question) {
    this.answers = answers;
    this.question = question;
  }

  public void deleteProcess(boolean isDeleted, DeleteHistories deleteHistories) {
    deleteQuestion(isDeleted, deleteHistories);
    deleteAnswer(isDeleted, deleteHistories);
  }

  private void deleteQuestion(boolean isDeleted, DeleteHistories deleteHistories) {
    question.setDeleted(isDeleted);
    deleteHistories.add(deleteHistories.createQuestionDeleteHistory(question));
  }

  private void deleteAnswer(boolean isDeleted, DeleteHistories deleteHistories) {
    answers.of()
        .stream()
        .map(answer -> deleteHistories.createAnswerDeleteHistory(answer.setDeleted(isDeleted)))
        .forEachOrdered(deleteHistories::add);
  }
}
