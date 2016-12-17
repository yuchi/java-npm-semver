package it.smc.yuchi.semver;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class BaseTest {

	protected static Collection<Object[]> argsWithDefaults(
		Object[] defaults, Object[] ...argsList) {

		return Arrays.stream(argsList)
			.map(args -> {
				if (args.length != defaults.length) {
					int i = args.length;
					args = Arrays.copyOf(args, defaults.length);

					for (; i < defaults.length; ++i) {
						args[i] = defaults[i];
					}
				}

				return args;
			})
			.collect(Collectors.toList());
	}

}
