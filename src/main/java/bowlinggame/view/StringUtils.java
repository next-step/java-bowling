package bowlinggame.view;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

	public static String join(List<String> values, String separator) {
		return values.stream()
				.collect(Collectors.joining(separator));
	}

	public static String rightAlign(String value, int length) {
		return String.format("%" + length + "s", value);
	}

	public static String centerAlign(String value, int size) {
		int left = (size - value.length()) / 2;
		int right = size - left - value.length();
		final char PADDING = ' ';
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < left; i++) {
			result.append(PADDING);
		}
		result.append(value);
		for (int i = 0; i < right; i++) {
			result.append(PADDING);
		}
		return result.toString();
	}
}
