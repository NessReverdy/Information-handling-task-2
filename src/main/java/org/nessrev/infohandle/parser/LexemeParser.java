package org.nessrev.infohandle.parser;

import org.nessrev.infohandle.entity.CharLeaf;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.type.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends TextParser {
  private static final Pattern LEXEME_CHECK_PATTERN = Pattern.compile("\\S+");

  public LexemeParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    Matcher matcher = LEXEME_CHECK_PATTERN.matcher(text);

    if (!canParse(text)) {
      return next(text);
    }

    TextComposite lexemes = new TextComposite(TextType.LEXEME);
    matcher.reset();

    int lastIndex = 0;

    while (matcher.find()) {
      if (matcher.start() > lastIndex) {
        String separator = text.substring(lastIndex, matcher.start());

        for (char symbol : separator.toCharArray()) {
          lexemes.add(new CharLeaf(symbol));
        }
      }
      lexemes.add(next(matcher.group()));
      lastIndex = matcher.end();
    }

    if (lastIndex < text.length()) {
      String remaining = text.substring(lastIndex);

      for (char symbol : remaining.toCharArray()) {
        lexemes.add(new CharLeaf(symbol));
      }
    }
    return lexemes;
  }

  @Override
  public boolean canParse(String text) {
    return LEXEME_CHECK_PATTERN.matcher(text).find();
  }
}
