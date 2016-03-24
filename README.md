AligningRecyclerView [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AligningRecyclerView-green.svg?style=true)](https://android-arsenal.com/details/1/2801)
====================
master [![Build Status](https://travis-ci.org/stoyicker/AligningRecyclerView.svg?branch=master)](https://travis-ci.org/stoyicker/AligningRecyclerView)
------

Download
--------
[![Release](https://jitpack.io/v/Stoyicker/AligningRecyclerView.svg)](https://jitpack.io/#Stoyicker/AligningRecyclerView)

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

Compatible with API 9 and higher.

Pull requests are welcome. See the [guidelines for contributing](https://github.com/Stoyicker/AligningRecyclerView/blob/master/CONTRIBUTING.md "CONTRIBUTING.md").

Usage
-----
Just replace your RecyclerViews with AlignRecyclerViews and you're good to go!

ProGuard
--------
No additional configuration is required.

I can't touch slave views until they're idle. Please fix!
---------------------------------------------------------
First of all, note that this behavior is intended and not going to be fixed. **You can only scroll an AligningRecyclerView manually if it is not scrolling or is scrolling because you manually requested so (rather than because of following another AligningRecyclerView)**. This said, let's take a look at why:
- **Case 1: Trying to scroll an AligningRecyclerView that is bound to another one where a scrolling was initiated with a two-way binding**: This case would pop an infinite mutual notification between the views, which is obviously not desirable.
- **Case 2: Trying to scroll an AligningRecyclerView that is bound to another one where a scrolling was initiated with a one-way binding**: This case can be solved in extremely different ways (like breaking the binding on touch, breaking it and re-adding it later), so I prefer to default to what I consider the simplest one. You are free to override [OnScrollListenerManagerOnItemTouchListener](https://github.com/stoyicker/AligningRecyclerView/blob/master/library/src/main/java/aligningrecyclerview/OnScrollListenerManagerOnItemTouchListener.java "OnScrollListenerManagerOnItemTouchListener") (which is public for this purpose) and make your adjustments:
```java
if (rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
  ...
}
```

License
-------
AligningRecyclerView (c) by Jorge Antonio Diaz-Benito Soriano

AligningRecyclerView is licensed under a
Creative Commons Attribution 4.0 International License.

You should have received a copy of the license along with this
work. If not, see [http://creativecommons.org/licenses/by/4.0/](http://creativecommons.org/licenses/by/4.0/).

**Open source licenses: See [THIRD_PARTY.md](https://github.com/Stoyicker/AligningRecyclerView/blob/master/THIRD_PARTY.md "THIRD_PARTY.md")**
