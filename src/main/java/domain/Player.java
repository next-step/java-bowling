package domain;

import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public class Player {
	private String name;

	public Player(String name) {
		checkArgument(StringUtils.isAlpha(name));
		checkArgument(name.length() <= 3);
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
