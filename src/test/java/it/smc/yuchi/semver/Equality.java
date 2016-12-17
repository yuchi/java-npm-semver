package it.smc.yuchi.semver;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for equality rules.
 *
 * @see https://github.com/npm/node-semver/blob/984491a/test/index.js#L75
 * @author Pier Paolo Ramon
 */
@RunWith(Parameterized.class)
public class Equality {

	protected String a;
	protected String b;
	protected boolean loose;

	public Equality(String a, String b, boolean loose) {
		this.a = a;
		this.b = b;
		this.loose = loose;
	}

	@Test
	public void eq() {
		Version a = new Version(this.a, loose);
		Version b = new Version(this.b, loose);

		String message = this.a + " should eq " + this.b;

		Assert.assertTrue(message, a.compareTo(b) == 0);
	}

	@Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"1.2.3", "v1.2.3", true},
			{"1.2.3", "=1.2.3", true},
			{"1.2.3", "v 1.2.3", true},
			{"1.2.3", "= 1.2.3", true},
			{"1.2.3", " v1.2.3", true},
			{"1.2.3", " =1.2.3", true},
			{"1.2.3", " v 1.2.3", true},
			{"1.2.3", " = 1.2.3", true},
			{"1.2.3-0", "v1.2.3-0", true},
			{"1.2.3-0", "=1.2.3-0", true},
			{"1.2.3-0", "v 1.2.3-0", true},
			{"1.2.3-0", "= 1.2.3-0", true},
			{"1.2.3-0", " v1.2.3-0", true},
			{"1.2.3-0", " =1.2.3-0", true},
			{"1.2.3-0", " v 1.2.3-0", true},
			{"1.2.3-0", " = 1.2.3-0", true},
			{"1.2.3-1", "v1.2.3-1", true},
			{"1.2.3-1", "=1.2.3-1", true},
			{"1.2.3-1", "v 1.2.3-1", true},
			{"1.2.3-1", "= 1.2.3-1", true},
			{"1.2.3-1", " v1.2.3-1", true},
			{"1.2.3-1", " =1.2.3-1", true},
			{"1.2.3-1", " v 1.2.3-1", true},
			{"1.2.3-1", " = 1.2.3-1", true},
			{"1.2.3-beta", "v1.2.3-beta", true},
			{"1.2.3-beta", "=1.2.3-beta", true},
			{"1.2.3-beta", "v 1.2.3-beta", true},
			{"1.2.3-beta", "= 1.2.3-beta", true},
			{"1.2.3-beta", " v1.2.3-beta", true},
			{"1.2.3-beta", " =1.2.3-beta", true},
			{"1.2.3-beta", " v 1.2.3-beta", true},
			{"1.2.3-beta", " = 1.2.3-beta", true},
			{"1.2.3-beta+build", " = 1.2.3-beta+otherbuild", true},
			{"1.2.3+build", " = 1.2.3+otherbuild", true},
			{"1.2.3-beta+build", "1.2.3-beta+otherbuild"},
			{"1.2.3+build", "1.2.3+otherbuild"},
			{"  v1.2.3+build", "1.2.3+otherbuild"}
		})
		.stream()
		.map(args -> {
			if (args.length == 2) {
				return new Object[] { args[0], args[1], false };
			}
			else {
				return args;
			}
		})
		.collect(Collectors.toList());
	}

}
