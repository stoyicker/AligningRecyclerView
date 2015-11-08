AligningRecyclerView
====================
master [![Build Status](https://travis-ci.org/stoyicker/AligningRecyclerView.svg?branch=master)](https://travis-ci.org/stoyicker/AligningRecyclerView)
------

Download
--------
[![Release](https://img.shields.io/github/release/stoyicker/AligningRecyclerView.svg?label=gradle)](https://jitpack.io/#stoyicker/AligningRecyclerView)
[![Release](https://img.shields.io/github/release/stoyicker/AligningRecyclerView.svg?label=maven)](https://jitpack.io/#stoyicker/AligningRecyclerView)

**Gradle**
```groovy
repositories {
    maven { url "https://jitpack.io" }
}

compile 'com.github.stoyicker:aligningrecyclerview:(insert latest version)'
```
**Maven**
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>

<dependency>
  <groupId>com.github.stoyicker</groupId>
  <artifactId>aligningrecyclerview</artifactId>
  <version>(insert latest version)</version>
</dependency>
```

Compatible with API 7 and higher.

Pull requests are welcome. See the [guidelines for contributing](https://github.com/Stoyicker/AligningRecyclerView/blob/master/CONTRIBUTING.md "CONTRIBUTING.md").

Usage
-----
Just replace your RecyclerViews with AlignRecyclerViews and you're good to go! By default the
scrolling will be synchronized both vertically and horizontally, but you can modify this
behaviour using `AligningRecyclerView#setAlignOrientation(final @AlignOrientation int
orientation)`. You can also set this attribute via a custom XML namespace using the `alignOrientation` attribute.

License
-------
AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano

AligningRecyclerView is licensed under a
Creative Commons Attribution 4.0 International License.

You should have received a copy of the license along with this
work. If not, see [http://creativecommons.org/licenses/by/4.0/](http://creativecommons.org/licenses/by/4.0/).

**Open source licenses: See [THIRD_PARTY.md](https://github.com/Stoyicker/AligningRecyclerView/blob/master/THIRD_PARTY.md "THIRD_PARTY.md")**
