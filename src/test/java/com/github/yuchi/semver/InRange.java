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
public class InRange extends BaseTest {

	protected String range;
	protected String version;
	protected boolean loose;

	public InRange(String range, String version, boolean loose) {
		this.range = range;
		this.version = version;
		this.loose = loose;
	}

	@Test
	public void works() throws Exception {
		Range range = Range.from(this.range, this.loose);
		Version version = Version.from(this.version, this.loose);

		Assert.assertTrue(range.test(version));
	}

	@Parameters(name = "{index}: ''{1}'' matches ''{0}'' (loose = {2})")
	public static Collection<Object[]> getParameters() {
		Object[] defaults = new Object[] {null, null, false};
		Object[][] parameters = new Object[][] {
			{"1.0.0 - 2.0.0", "1.2.3"},
			{"^1.2.3+build", "1.2.3"},
			{"^1.2.3+build", "1.3.0"},
			{"1.2.3-pre+asdf - 2.4.3-pre+asdf", "1.2.3"},
			{"1.2.3pre+asdf - 2.4.3-pre+asdf", "1.2.3", true},
			{"1.2.3-pre+asdf - 2.4.3pre+asdf", "1.2.3", true},
			{"1.2.3pre+asdf - 2.4.3pre+asdf", "1.2.3", true},
			{"1.2.3-pre+asdf - 2.4.3-pre+asdf", "1.2.3-pre.2"},
			{"1.2.3-pre+asdf - 2.4.3-pre+asdf", "2.4.3-alpha"},
			{"1.2.3+asdf - 2.4.3+asdf", "1.2.3"},
			{"1.0.0", "1.0.0"},
			{">=*", "0.2.4"},
			{"", "1.0.0"},
			{"*", "1.2.3"},
			{"*", "v1.2.3", true},
			{">=1.0.0", "1.0.0"},
			{">=1.0.0", "1.0.1"},
			{">=1.0.0", "1.1.0"},
			{">1.0.0", "1.0.1"},
			{">1.0.0", "1.1.0"},
			{"<=2.0.0", "2.0.0"},
			{"<=2.0.0", "1.9999.9999"},
			{"<=2.0.0", "0.2.9"},
			{"<2.0.0", "1.9999.9999"},
			{"<2.0.0", "0.2.9"},
			{">= 1.0.0", "1.0.0"},
			{">=  1.0.0", "1.0.1"},
			{">=   1.0.0", "1.1.0"},
			{"> 1.0.0", "1.0.1"},
			{">  1.0.0", "1.1.0"},
			{"<=   2.0.0", "2.0.0"},
			{"<= 2.0.0", "1.9999.9999"},
			{"<=  2.0.0", "0.2.9"},
			{"<    2.0.0", "1.9999.9999"},
			{"<\t2.0.0", "0.2.9"},
			{">=0.1.97", "v0.1.97", true},
			{">=0.1.97", "0.1.97"},
			{"0.1.20 || 1.2.4", "1.2.4"},
			{">=0.2.3 || <0.0.1", "0.0.0"},
			{">=0.2.3 || <0.0.1", "0.2.3"},
			{">=0.2.3 || <0.0.1", "0.2.4"},
			{"||", "1.3.4"},
			{"2.x.x", "2.1.3"},
			{"1.2.x", "1.2.3"},
			{"1.2.x || 2.x", "2.1.3"},
			{"1.2.x || 2.x", "1.2.3"},
			{"x", "1.2.3"},
			{"2.*.*", "2.1.3"},
			{"1.2.*", "1.2.3"},
			{"1.2.* || 2.*", "2.1.3"},
			{"1.2.* || 2.*", "1.2.3"},
			{"*", "1.2.3"},
			{"2", "2.1.2"},
			{"2.3", "2.3.1"},
			{"~2.4", "2.4.0"}, // >=2.4.0 <2.5.0
			{"~2.4", "2.4.5"},
			{"~>3.2.1", "3.2.2"}, // >=3.2.1 <3.3.0,
			{"~1", "1.2.3"}, // >=1.0.0 <2.0.0
			{"~>1", "1.2.3"},
			{"~> 1", "1.2.3"},
			{"~1.0", "1.0.2"}, // >=1.0.0 <1.1.0,
			{"~ 1.0", "1.0.2"},
			{"~ 1.0.3", "1.0.12"},
			{">=1", "1.0.0"},
			{">= 1", "1.0.0"},
			{"<1.2", "1.1.1"},
			{"< 1.2", "1.1.1"},
			{"~v0.5.4-pre", "0.5.5"},
			{"~v0.5.4-pre", "0.5.4"},
			{"=0.7.x", "0.7.2"},
			{"<=0.7.x", "0.7.2"},
			{">=0.7.x", "0.7.2"},
			{"<=0.7.x", "0.6.2"},
			{"~1.2.1 >=1.2.3", "1.2.3"},
			{"~1.2.1 =1.2.3", "1.2.3"},
			{"~1.2.1 1.2.3", "1.2.3"},
			{"~1.2.1 >=1.2.3 1.2.3", "1.2.3"},
			{"~1.2.1 1.2.3 >=1.2.3", "1.2.3"},
			{"~1.2.1 1.2.3", "1.2.3"},
			{">=1.2.1 1.2.3", "1.2.3"},
			{"1.2.3 >=1.2.1", "1.2.3"},
			{">=1.2.3 >=1.2.1", "1.2.3"},
			{">=1.2.1 >=1.2.3", "1.2.3"},
			{">=1.2", "1.2.8"},
			{"^1.2.3", "1.8.1"},
			{"^0.1.2", "0.1.2"},
			{"^0.1", "0.1.2"},
			{"^1.2", "1.4.2"},
			{"^1.2 ^1", "1.4.2"},
			{"^1.2.3-alpha", "1.2.3-pre"},
			{"^1.2.0-alpha", "1.2.0-pre"},
			{"^0.0.1-alpha", "0.0.1-beta"}
		};

		return argsWithDefaults(defaults, parameters);
	}

}
