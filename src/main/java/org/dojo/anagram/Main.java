package org.dojo.anagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {

  private static final String FILE_PATH =
      "/Users/thomasheniart/workspace/dojo/java-anagram-kata/wordlist.txt";
  private static final String SPACE = " ";

  public static void main(String[] args) {
    try (Stream<String> fileStream = Files.lines(Paths.get(FILE_PATH))) {
      var main = new Main(fileStream);
      main.run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private final Stream<String> fileStream;
  private String[] anagrams;

  private Main(Stream<String> fileStream) {
    this.fileStream = fileStream;
  }

  private void run() {
    processFileStream();

    var max = maxAnagrams();
    System.out.println(max);
  }

  private int maxAnagrams() {
    return Arrays.stream(this.anagrams)
        .max(
            (s1, s2) -> {
              var s1Length = s1.split(SPACE).length;
              var s2Length = s2.split(SPACE).length;

              var res = -1;

              if (s1Length > s2Length) {
                res = 1;
              } else if (s1Length == s2Length) {
                res = 0;
              }

              return res;
            })
        .map(s -> s.split(SPACE).length)
        .orElse(0);
  }

  private void processFileStream() {
    Map<String, StringBuilder> resultMap = new HashMap<>();
    this.fileStream.forEach(
        s -> {
          var chars = s.toCharArray();
          Arrays.sort(chars);
          String key = new String(chars);
          StringBuilder current = resultMap.get(key);
          resultMap.put(
              key,
              Objects.isNull(current) ? new StringBuilder(s) : current.append(SPACE).append(s));
        });

    this.anagrams =
        resultMap.values().stream()
            .map(StringBuilder::toString)
            .filter(s -> s.contains(SPACE))
            .toArray(String[]::new);
  }
}
