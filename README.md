Java _npm_ SemVer
=================

This is a direct port [`node-semver`](https://github.com/npm/node-semver), great to have npm like semantics in a Java application.

Releases
--------

Available in the [Maven Central repository](http://search.maven.org/#artifactdetails%7Ccom.github.yuchi%7Cnpm-semver%7C1.0.0%7Cjar).

#### Maven configuration

```xml
<dependency>
  <groupId>com.github.yuchi</groupId>
  <artifactId>npm-semver</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Gradle configuration

```groovy
compile group: "com.github.yuchi", name: "npm-semver", version: "1.0.0"
// or
compile "com.github.yuchi:npm-semver:1.0.0"
```


Quick usage
-----------

```java
Version version = Version.from("1.2.3");
Range range = Range.from("^1.2.0");

range.test(version) // true
```


Publishing
----------

```
$ trash build
$ trash tmp
$ mkdir tmp
$ gradle clean preparePublish publish
$ gradle clean preparePublish publish # yes you need to launch it twice
```


License
-------

This library, *java-npm-semver*, is free software ("Licensed Software"); you can
redistribute it and/or modify it under the terms of the [GNU Lesser General
Public License](http://www.gnu.org/licenses/lgpl-2.1.html) as published by the
Free Software Foundation; either version 2.1 of the License, or (at your
option) any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; including but not limited to, the implied warranty of MERCHANTABILITY,
NONINFRINGEMENT, or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
Public License for more details.

You should have received a copy of the [GNU Lesser General Public
License](http://www.gnu.org/licenses/lgpl-2.1.html) along with this library; if
not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
Floor, Boston, MA 02110-1301 USA
