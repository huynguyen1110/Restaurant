package com.hivetech.ine2.htine2.service;

import org.springframework.stereotype.Service;

@Service
public class Converter {
    public String convertName(String name) {
        char[] charArray = name.toCharArray();
        boolean foundSpace = true;
        String afterNameConvert = "";
        int countSpase = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                countSpase = 0;
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                    afterNameConvert += String.valueOf(charArray[i]);
                } else {
                    afterNameConvert += String.valueOf(charArray[i]);
                }
            } else {
                foundSpace = true;
                countSpase++;
                if (countSpase < 2) {
                    afterNameConvert += String.valueOf(charArray[i]);
                }
            }
        }
        return afterNameConvert;
    }
}
