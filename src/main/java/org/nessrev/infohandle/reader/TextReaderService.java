package org.nessrev.infohandle.reader;

import org.nessrev.infohandle.exception.TextException;

public interface TextReaderService {
  String readText(String filename) throws TextException;
}
