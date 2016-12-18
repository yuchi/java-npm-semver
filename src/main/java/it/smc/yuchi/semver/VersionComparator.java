package it.smc.yuchi.semver;

import java.util.Comparator;

public class VersionComparator implements Comparator<Version> {

	@Override
	public int compare(Version v1, Version v2) {
		return v1.compareTo(v2);
	}

}
