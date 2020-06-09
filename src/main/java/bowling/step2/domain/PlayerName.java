/* Copyright (c) 2020 ZUM Internet, Inc.
 * All right reserved.
 * http://www.zum.com
 * This software is the confidential and proprietary information of ZUM
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with ZUM.
 *
 * Revision History
 * Author                    Date                     Description
 * ------------------       --------------            ------------------
 *   junil                2020-06-09
 */
package bowling.step2.domain;

import bowling.step2.exception.PlayerNameEmptyException;
import bowling.step2.exception.PlayerNameMaximumException;

import java.util.HashMap;

public class PlayerName {
    private static final HashMap<String, PlayerName> FACTORY = new HashMap<>();
    private final String value;

    private PlayerName (String value) {
        this.value = value;
    }

    public static PlayerName valueOf (String value) {
        validateEmpty(value);
        validateMaximumLength(value);
        return FACTORY.computeIfAbsent(value, PlayerName::new);
    }

    private static void validateEmpty (String value) {
        if (value == null || value.trim().equals("")) {
            throw new PlayerNameEmptyException();
        }
    }

    private static void validateMaximumLength (String value) {
        if (value.length() > 3) {
            throw new PlayerNameMaximumException();
        }
    }
}