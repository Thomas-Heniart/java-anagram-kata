package org.dojo.anagram;

import java.util.HashMap;
import java.util.Map;

public class Utils {
  public static Map<Character, Integer> charactersAndCountForWord(String word) {
    Map<Character, Integer> charactersAndCount = new HashMap<>();
    word.chars()
        .forEach(
            (int character) -> {
              charactersAndCount.putIfAbsent((char) character, 0);
              charactersAndCount.computeIfPresent((char) character, (key, val) -> val + 1);
            });
    return charactersAndCount;
  }
}
