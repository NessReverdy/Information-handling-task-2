package org.nessrev.infohandle.service;

import org.nessrev.infohandle.entity.TextComponent;

import java.util.List;

public interface CommonTaskService {
  List<TextComponent> findSentencesWithMostRepeatedWord(TextComponent textComponent);
  List<TextComponent> sortSentencesByLetterCount(char letter);
  List<TextComponent> swapFirstAndLastLexemes(TextComponent textComponent);

}
