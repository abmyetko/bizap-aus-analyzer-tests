package com.capsilon.automation.aus.utils;

import static java.lang.Character.isAlphabetic;

public class Actions {

    public static String toUpperCaseForFirstLetter(String text) {
        StringBuilder builder = new StringBuilder(text);
        if (isAlphabetic(text.codePointAt(0)))
            builder.setCharAt(0, Character.toUpperCase(text.charAt(0)));
        for (int i = 1; i < text.length(); i++)
            if (isAlphabetic(text.charAt(i)) && !(isAlphabetic(text.charAt(i - 1))))
                builder.setCharAt(i, Character.toUpperCase(text.charAt(i)));
        return builder.toString();
    }

}
