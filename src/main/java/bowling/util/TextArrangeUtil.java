package bowling.util;

public class TextArrangeUtil {


  public static String arrange(String content, int length) {
    if (length < content.length()) {
      return content;
    }
    int contentLength = content.length();
    int preBlankLength = (length - contentLength) / 2;
    int postBlankPostLength = length - contentLength - preBlankLength;

    return addPostBlank(postBlankPostLength, addPreBlank(preBlankLength, content));
  }

  private static String addPostBlank(int postBlankPostLength, String content) {
    StringBuffer result = new StringBuffer();
    result.append(content);
    for (int i = 0; i < postBlankPostLength; i++) {
      result.append(" ");
    }
    return result.toString();
  }

  private static String addPreBlank(int preBlankLength, String content) {
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < preBlankLength; i++) {
      result.append(" ");
    }
    return result.append(content).toString();
  }
}
