package com.github.yuchi.semver;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.yuchi.semver.Version;

/**
 * Tests for equality rules.
 *
 * @see https://github.com/npm/node-semver/blob/984491a/test/index.js#L75
 * @author Pier Paolo Ramon
 */
@RunWith(Parameterized.class)
public class Equality extends BaseTest {

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

	@Parameters(name = "{index}: ''{0}'' == ''{1}'' (loose = {2})")
	public static Collection<Object[]> getParameters() {
		Object[] defaults = new Object[] {null, null, false};
		Object[][] parameters = new Object[][] {
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
		};

		return argsWithDefaults(defaults, parameters);
	}

}
