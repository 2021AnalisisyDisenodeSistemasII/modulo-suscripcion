package com.povsal.starbank.utils.converter;

import java.io.IOException;
import java.util.Map;

public interface JSONConverter<K, T> extends Converter {
	Map<K, T> getAll() throws IOException;
}
