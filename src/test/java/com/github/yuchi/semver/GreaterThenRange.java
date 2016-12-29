package com.github.yuchi.semver;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.yuchi.semver.Direction;
import com.github.yuchi.semver.SemVer;

@RunWith(Parameterized.class)
public class GreaterThenRange extends BaseTest {

	protected String range;
	protected String version;
	protected boolean loose;

	public GreaterThenRange(String range, String version, boolean loose) {
		this.range = range;
		this.version = version;
		this.loose = loose;
	}

	@Test
	public void works() throws Exception {
		Assert.assertTrue(SemVer.isOutside(
			version, range, Direction.HIGH, loose));
	}

	@Parameters(name = "{index}: gt(''{0}'', ''{1}'', loose = {2})")
	public static Collection<Object[]> getParameters() {
		Object[] defaults = new Object[] {null, null, false};
		Object[][] parameters = new Object[][] {
			{"~1.2.2", "1.3.0"},
			{"~0.6.1-1", "0.7.1-1"},
			{"1.0.0 - 2.0.0", "2.0.1"},
			{"1.0.0", "1.0.1-beta1"},
			{"1.0.0", "2.0.0"},
			{"<=2.0.0", "2.1.1"},
			{"<=2.0.0", "3.2.9"},
			{"<2.0.0", "2.0.0"},
			{"0.1.20 || 1.2.4", "1.2.5"},
			{"2.x.x", "3.0.0"},
			{"1.2.x", "1.3.0"},
			{"1.2.x || 2.x", "3.0.0"},
			{"2.*.*", "5.0.1"},
			{"1.2.*", "1.3.3"},
			{"1.2.* || 2.*", "4.0.0"},
			{"2", "3.0.0"},
			{"2.3", "2.4.2"},
			{"~2.4", "2.5.0"}, // >=2.4.0 <2.5.0
			{"~2.4", "2.5.5"},
			{"~>3.2.1", "3.3.0"}, // >=3.2.1 <3.3.0
			{"~1", "2.2.3"}, // >=1.0.0 <2.0.0
			{"~>1", "2.2.4"},
			{"~> 1", "3.2.3"},
			{"~1.0", "1.1.2"}, // >=1.0.0 <1.1.0
			{"~ 1.0", "1.1.0"},
			{"<1.2", "1.2.0"},
			{"< 1.2", "1.2.1"},
			{"1", "2.0.0beta", true},
			{"~v0.5.4-pre", "0.6.0"},
			{"~v0.5.4-pre", "0.6.1-pre"},
			{"=0.7.x", "0.8.0"},
			{"=0.7.x", "0.8.0-asdf"},
			{"<0.7.x", "0.7.0"},
			{"~1.2.2", "1.3.0"},
			{"1.0.0 - 2.0.0", "2.2.3"},
			{"1.0.0", "1.0.1"},
			{"<=2.0.0", "3.0.0"},
			{"<=2.0.0", "2.9999.9999"},
			{"<=2.0.0", "2.2.9"},
			{"<2.0.0", "2.9999.9999"},
			{"<2.0.0", "2.2.9"},
			{"2.x.x", "3.1.3"},
			{"1.2.x", "1.3.3"},
			{"1.2.x || 2.x", "3.1.3"},
			{"2.*.*", "3.1.3"},
			{"1.2.*", "1.3.3"},
			{"1.2.* || 2.*", "3.1.3"},
			{"2", "3.1.2"},
			{"2.3", "2.4.1"},
			{"~2.4", "2.5.0"}, // >=2.4.0 <2.5.0
			{"~>3.2.1", "3.3.2"}, // >=3.2.1 <3.3.0
			{"~1", "2.2.3"}, // >=1.0.0 <2.0.0
			{"~>1", "2.2.3"},
			{"~1.0", "1.1.0"}, // >=1.0.0 <1.1.0
			{"<1", "1.0.0"},
			{"1", "2.0.0beta", true},
			{"<1", "1.0.0beta", true},
			{"< 1", "1.0.0beta", true},
			{"=0.7.x", "0.8.2"},
			{"<0.7.x", "0.7.2"}
		};

		return argsWithDefaults(defaults, parameters);
	}

}
