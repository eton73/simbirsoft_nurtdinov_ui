package com.simbirsoft.helpers;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class AlphabetConverter {

  /**
   * Перевод 10-значной строки, состоящей из цифр, в слово, состоящее из 5 букв английского алфавита
   * Например: 0001252667 = abzap
   */
  public static String getValue(String code) {
    String[] codes = code.split("");
    Character[] name = new Character[5];
    Map<Integer, Character> englishAlphabet = AlphabetDictionary.englishAlphabet;

    int index = 0;
    for (int i = 0; i < 9; i = i + 2) {
      String codeStr = (codes[i] + codes[i + 1]);
      int codeInt = Integer.parseInt(codeStr);

      if (!englishAlphabet.containsKey(codeInt)) {
        codeInt = (codeInt - englishAlphabet.size());
      }
      if (!englishAlphabet.containsKey(codeInt)) {
        codeInt = (codeInt - englishAlphabet.size());
      }
      if (!englishAlphabet.containsKey(codeInt)) {
        codeInt = (codeInt - englishAlphabet.size());
      }

      name[index] = englishAlphabet.get(codeInt);
      index++;
    }

    return Arrays.stream(name).map(String::valueOf).collect(Collectors.joining());
  }

}
