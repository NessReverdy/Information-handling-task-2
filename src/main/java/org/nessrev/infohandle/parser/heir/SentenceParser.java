package org.nessrev.infohandle.parser.heir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.Parser;
import org.nessrev.infohandle.parser.TextParser;
import org.nessrev.infohandle.type.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextParser {
  private final Logger logger = LogManager.getLogger();
  private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_MATCH_REGEX);

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

    TextComposite paragraph = new TextComposite(TextType.SENTENCE_LIST);
    Matcher matcher = SENTENCE_PATTERN.matcher(text);

    while (matcher.find()) {
      String sentence = matcher.group();

      TextComposite sentenceComponent =
        new TextComposite(TextType.SENTENCE);

      TextComponent component = next(sentence);

      if (component != null) {
        sentenceComponent.add(component);
        paragraph.add(sentenceComponent);
      }
    }

    return paragraph;
  }

  @Override
  public boolean canParse(String text) {
    return text.matches(SENTENCE_CHECK_REGEX);
  }
}
