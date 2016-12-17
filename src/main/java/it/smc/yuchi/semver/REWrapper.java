package it.smc.yuchi.semver;

import java.util.regex.Pattern;

public class REWrapper {

	protected REWrapper(String raw) {
		this(raw, false);
	}

	protected REWrapper(String raw, boolean global) {
		_raw = raw;
		_global = global;
	}

	public boolean matches(CharSequence input) {
		return Pattern.matches(_raw, input);
	}

	public Pattern getPattern() {
		if (_pattern == null) {
			_pattern = Pattern.compile(_raw);
		}

		return _pattern;
	}

	@Override
	public String toString() {
		return _raw;
	}

	private boolean _global = false;
	private Pattern _pattern = null;
	private String _raw = null;

}
