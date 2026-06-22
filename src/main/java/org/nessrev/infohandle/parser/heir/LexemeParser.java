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

public class LexemeParser extends TextParser {
  private final Logger logger = LogManager.getLogger();

  public LexemeParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    Pattern pattern = Pattern.compile(LEXEME_CHECK_REGEX);
    Matcher matcher = pattern.matcher(text);

    if (!matcher.find()) {
      return next(text);
    }

    TextComposite lexemes = new TextComposite(TextType.LEXEME);
    matcher.reset();

    while (matcher.find()) {
      lexemes.add(next(matcher.group()));
    }
    return lexemes;
  }

  @Override
  public boolean canParse(String text) {
    return text.matches(LEXEME_CHECK_REGEX);
  }
}
