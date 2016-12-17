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
 * Tests for simple comparisons.
 *
 * @see https://github.com/npm/node-semver/blob/984491a/test/index.js#L22
 * @author Pier Paolo Ramon
 */
@RunWith(Parameterized.class)
public class Comparison {

	protected String a;
	protected String b;
	protected boolean loose;

	public Comparison(String a, String b, boolean loose) {
		this.a = a;
		this.b = b;
		this.loose = loose;
	}

	@Test
	public void gt() {
		Version a = new Version(this.a, loose);
		Version b = new Version(this.b, loose);

		String message = this.a + " > " + this.b;

		Assert.assertTrue(message, a.compareTo(b) > 0);
	}

	@Test
	public void eq() {
		Version a1 = new Version(this.a, loose);
		Version a2 = new Version(this.a, loose);
		Version b1 = new Version(this.b, loose);
		Version b2 = new Version(this.b, loose);

		String messageA = this.a + " should eq itself";
		String messageB = this.b + " should eq itself";

		Assert.assertTrue(messageA, a1.compareTo(a2) == 0);
		Assert.assertTrue(messageB, b1.compareTo(b2) == 0);
	}

	@Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"0.0.0", "0.0.0-foo"},
			{"0.0.1", "0.0.0"},
			{"1.0.0", "0.9.9"},
			{"0.10.0", "0.9.0"},
			{"0.99.0", "0.10.0"},
			{"2.0.0", "1.2.3"},
			{"v0.0.0", "0.0.0-foo", true},
			{"v0.0.1", "0.0.0", true},
			{"v1.0.0", "0.9.9", true},
			{"v0.10.0", "0.9.0", true},
			{"v0.99.0", "0.10.0", true},
			{"v2.0.0", "1.2.3", true},
			{"0.0.0", "v0.0.0-foo", true},
			{"0.0.1", "v0.0.0", true},
			{"1.0.0", "v0.9.9", true},
			{"0.10.0", "v0.9.0", true},
			{"0.99.0", "v0.10.0", true},
			{"2.0.0", "v1.2.3", true},
			{"1.2.3", "1.2.3-asdf"},
			{"1.2.3", "1.2.3-4"},
			{"1.2.3", "1.2.3-4-foo"},
			{"1.2.3-5-foo", "1.2.3-5"},
			{"1.2.3-5", "1.2.3-4"},
			{"1.2.3-5-foo", "1.2.3-5-Foo"},
			{"3.0.0", "2.7.2+asdf"},
			{"1.2.3-a.10", "1.2.3-a.5"},
			{"1.2.3-a.b", "1.2.3-a.5"},
			{"1.2.3-a.b", "1.2.3-a"},
			{"1.2.3-a.b.c.10.d.5", "1.2.3-a.b.c.5.d.100"},
			{"1.2.3-r2", "1.2.3-r100"},
			{"1.2.3-r100", "1.2.3-R2"}
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
