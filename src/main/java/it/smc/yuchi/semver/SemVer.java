package it.smc.yuchi.semver;

public class SemVer {

	public static boolean isOutside(
			Object version, Object range, Direction dir, boolean loose)
		throws Exception {

		Version _version = Version.from(version, loose);
		Range _range = Range.from(range, loose);

		return _range.isOutside(_version, dir);
	}

}
