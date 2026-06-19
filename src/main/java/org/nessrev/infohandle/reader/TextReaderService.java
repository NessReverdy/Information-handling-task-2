package org.nessrev.infohandle.reader;

import org.nessrev.infohandle.exception.TextException;

import java.util.List;

public interface TextReaderService {
  List<String> readText(String filename) throws TextException;
}
