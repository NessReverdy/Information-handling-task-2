package org.nessrev.infohandle.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.type.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextParser {
  private final Logger logger = LogManager.getLogger();
  private static final Pattern SENTENCE_MATCH_PATTERN = Pattern.compile(".*?[.!?…](?:\\s+|$)");

  public SentenceParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    if (!canParse(text)) {
      return next(text);
    }

    logger.info("Parsing sentences");

    TextComposite paragraph = new TextComposite(TextType.SENTENCE);
    Matcher matcher = SENTENCE_MATCH_PATTERN.matcher(text);

    while (matcher.find()) {
      String sentence = matcher.group();
      TextComponent component = next(sentence);

      if (component != null) {
        paragraph.add(component);
      }
    }

    return paragraph;
  }

  @Override
  public boolean canParse(String text) {
    return text.matches(SENTENCE_CHECK_REGEX);
  }
}
