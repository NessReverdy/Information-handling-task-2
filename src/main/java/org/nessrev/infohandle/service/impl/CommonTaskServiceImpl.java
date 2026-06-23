package org.nessrev.infohandle.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.service.CommonTaskService;
import org.nessrev.infohandle.type.TextType;

import java.util.*;

public class CommonTaskServiceImpl implements CommonTaskService {
  private static final Logger logger = LogManager.getLogger();

  private final TextComponent text;

  public CommonTaskServiceImpl(TextComponent text) {
    this.text = text;
  }

  @Override
  public List<TextComponent> findSentencesWithMostRepeatedWord(TextComponent textComponent) {
    logger.info("Start searching sentences with most common words");
    List<TextComponent> sentences = findAllSentences(textComponent);

    logger.debug("Sentences received: {}", sentences.size());
    Map<String, Set<TextComponent>> wordToSentences = new HashMap<>();

    for (TextComponent sentence : sentences) {
      Set<String> words = extractWords(sentence);

      for (String word : words) {
        wordToSentences
          .computeIfAbsent(word, key -> new HashSet<>())
          .add(sentence);
      }
    }

    logger.debug("Words collected: {}", wordToSentences.size());
    int maxCount = wordToSentences.values()
      .stream()
      .mapToInt(Set::size)
      .max()
      .orElse(0);

    List<String> mostRepeatedWords = wordToSentences.entrySet()
      .stream()
      .filter(entry -> entry.getValue().size() == maxCount)
      .map(Map.Entry::getKey)
      .toList();

    logger.info("Most repeated word(s): {}", mostRepeatedWords);
    logger.info("Maximum sentence count with same word: {}", maxCount);

    Set<TextComponent> result = new HashSet<>();
    wordToSentences.values()
      .stream()
      .filter(sentencesSet -> sentencesSet.size() == maxCount)
      .forEach(result::addAll);

    return new ArrayList<>(result);
  }

  @Override
  public List<TextComponent> sortSentencesByLetterCount(char letter) {
    logger.info("Starting sorting sentences by letter count. Letter: '{}'", letter);

    List<TextComponent> sentences = new ArrayList<>();

    collectSentences(text, sentences);

    logger.debug("Found {} sentences for sorting", sentences.size());

    List<TextComponent> sortedSentences = sentences.stream()
      .sorted(Comparator.comparingInt(sentence -> countLetter(sentence, letter)))
      .toList();

    logger.info("Sorting completed successfully");

    for (TextComponent sentence : sortedSentences) {
      logger.debug("Sentence: '{}', count of '{}': {}",
        sentence.getValue(),
        letter,
        countLetter(sentence, letter));
    }

    return sortedSentences;
  }

  @Override
  public List<TextComponent> swapFirstAndLastLexemes(TextComponent textComponent) {
    return List.of();
  }

  private Set<String> extractWords(TextComponent sentence) {
    Set<String> words = new HashSet<>();

    collectWords(sentence, words);

    logger.debug("Words: {}", words);

    return words;
  }

  private void collectWords(TextComponent component, Set<String> words) {

    if (component.getType() == TextType.WORD) {

      String word = component.getValue()
        .replaceAll("[^a-zA-Zа-яА-ЯёЁ']", "")
        .toLowerCase();

      if (!word.isBlank()) {
        words.add(word);
      }

      return;
    }

    if (!component.isLeaf()) {
      for (TextComponent child : component.getChildren()) {
        collectWords(child, words);
      }
    }
  }

  private List<TextComponent> findAllSentences(TextComponent component) {
    List<TextComponent> sentences = new ArrayList<>();
    if (component.getType() == TextType.SENTENCE) {
      sentences.add(component);
      return sentences;
    }

    for (TextComponent child : component.getChildren()) {
      sentences.addAll(findAllSentences(child));
    }
    return sentences;
  }

  private int countLetter(TextComponent sentence, char letter) {
    String value = sentence.getValue();

    return (int) value.chars()
      .filter(ch -> ch == Character.toLowerCase(letter)
        || ch == Character.toUpperCase(letter))
      .count();
  }

  private void collectSentences(TextComponent component,
                                List<TextComponent> sentences) {

    if (component.getType() == TextType.SENTENCE) {
      sentences.add(component);
      return;
    }

    if (!component.isLeaf()) {
      for (TextComponent child : component.getChildren()) {
        collectSentences(child, sentences);
      }
    }
  }
}
