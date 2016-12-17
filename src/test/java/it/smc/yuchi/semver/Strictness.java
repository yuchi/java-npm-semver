package it.smc.yuchi.semver;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Strictness {

	protected String loose;
	protected String strict;

	public Strictness(String loose, String strict) {
		this.loose = loose;
		this.strict = strict;
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsWhenStrictOnLoose() throws Exception {
		new Version(this.loose, false);
	}

	@Test
	public void formattedLooseEqualsStrict() {
		Version loose = new Version(this.loose, true);

		Assert.assertEquals(loose.toString(), this.strict);
	}

	@Test
	public void looseEqualsStrict() {
		Version loose = new Version(this.loose, true);
		Version strict = new Version(this.strict, false);

		Assert.assertTrue(loose.compareTo(strict) == 0);
	}

	@Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"=1.2.3", "1.2.3"},
			{"01.02.03", "1.2.3"},
			{"1.2.3-beta.01", "1.2.3-beta.1"},
			{"   =1.2.3", "1.2.3"},
			{"1.2.3foo", "1.2.3-foo"}
		});
	}

}
