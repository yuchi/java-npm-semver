package com.github.yuchi.semver;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.yuchi.semver.Range;
import com.github.yuchi.semver.Version;

@RunWith(Parameterized.class)
public class NotInRange extends BaseTest {

	protected String range;
	protected String version;
	protected boolean loose;

	public NotInRange(String range, String version, boolean loose) {
		this.range = range;
		this.version = version;
		this.loose = loose;
	}

	@Test
	public void works() throws Exception {
		Range range = Range.from(this.range, this.loose);
		Version version = Version.from(this.version, this.loose);

		if (range != null) {
			Assert.assertFalse(range.test(version));
		}
	}

	@Parameters(name = "{index}: ''{1}'' matches ''{0}'' (loose = {2})")
	public static Collection<Object[]> getParameters() {
		Object[] defaults = new Object[] {null, null, false};
		Object[][] parameters = new Object[][] {
			{"1.0.0 - 2.0.0", "2.2.3"},
			{"1.2.3+asdf - 2.4.3+asdf", "1.2.3-pre.2"},
			{"1.2.3+asdf - 2.4.3+asdf", "2.4.3-alpha"},
			{"^1.2.3+build", "2.0.0"},
			{"^1.2.3+build", "1.2.0"},
			{"^1.2.3", "1.2.3-pre"},
			{"^1.2", "1.2.0-pre"},
			{">1.2", "1.3.0-beta"},
			{"<=1.2.3", "1.2.3-beta"},
			{"^1.2.3", "1.2.3-beta"},
			{"=0.7.x", "0.7.0-asdf"},
			{">=0.7.x", "0.7.0-asdf"},
			{"1", "1.0.0beta", true},
			{"<1", "1.0.0beta", true},
			{"< 1", "1.0.0beta", true},
			{"1.0.0", "1.0.1"},
			{">=1.0.0", "0.0.0"},
			{">=1.0.0", "0.0.1"},
			{">=1.0.0", "0.1.0"},
			{">1.0.0", "0.0.1"},
			{">1.0.0", "0.1.0"},
			{"<=2.0.0", "3.0.0"},
			{"<=2.0.0", "2.9999.9999"},
			{"<=2.0.0", "2.2.9"},
			{"<2.0.0", "2.9999.9999"},
			{"<2.0.0", "2.2.9"},
			{">=0.1.97", "v0.1.93", true},
			{">=0.1.97", "0.1.93"},
			{"0.1.20 || 1.2.4", "1.2.3"},
			{">=0.2.3 || <0.0.1", "0.0.3"},
			{">=0.2.3 || <0.0.1", "0.2.2"},
			{"2.x.x", "1.1.3"},
			{"2.x.x", "3.1.3"},
			{"1.2.x", "1.3.3"},
			{"1.2.x || 2.x", "3.1.3"},
			{"1.2.x || 2.x", "1.1.3"},
			{"2.*.*", "1.1.3"},
			{"2.*.*", "3.1.3"},
			{"1.2.*", "1.3.3"},
			{"1.2.* || 2.*", "3.1.3"},
			{"1.2.* || 2.*", "1.1.3"},
			{"2", "1.1.2"},
			{"2.3", "2.4.1"},
			{"~2.4", "2.5.0"}, // >=2.4.0 <2.5.0
			{"~2.4", "2.3.9"},
			{"~>3.2.1", "3.3.2"}, // >=3.2.1 <3.3.0
			{"~>3.2.1", "3.2.0"}, // >=3.2.1 <3.3.0
			{"~1", "0.2.3"}, // >=1.0.0 <2.0.0
			{"~>1", "2.2.3"},
			{"~1.0", "1.1.0"}, // >=1.0.0 <1.1.0
			{"<1", "1.0.0"},
			{">=1.2", "1.1.1"},
			{"1", "2.0.0beta", true},
			{"~v0.5.4-beta", "0.5.4-alpha"},
			{"=0.7.x", "0.8.2"},
			{">=0.7.x", "0.6.2"},
			{"<0.7.x", "0.7.2"},
			{"<1.2.3", "1.2.3-beta"},
			{"=1.2.3", "1.2.3-beta"},
			{">1.2", "1.2.8"},
			{"^1.2.3", "2.0.0-alpha"},
			{"^1.2.3", "1.2.2"},
			{"^1.2", "1.1.9"},
			{"*", "v1.2.3-foo", true},
			// invalid ranges never satisfied!
			{"blerg", "1.2.3"},
			{"git+https://user:password0123@github.com/foo", "123.0.0", true},
			{"^1.2.3", "2.0.0-pre"}
		};

		return argsWithDefaults(defaults, parameters);
	}

}
