# Bread Crumb View
Easy to use & Lightweight Bread Crumb View

![sample_video](assets/sample.gif)

## Usage

Add BreadCrumbView to your XML

```xml

    <com.holoo.library.breadcrumb.BreadCrumbView
        android:id="@+id/bread_crumb_view"
        android:layout_width="match_parent"
        app:arrow_drawable="@drawable/ic_lens_black_24dp" // you can change arrow drawable as you want
        android:layout_height="60dp" />
```

Add your breadcrumbs to View

```java

        val list = mutableListOf<BreadCrumb>()

        list.add(BreadCrumb.Builder().setTitle("Crumb 1").build())
        list.add(BreadCrumb.Builder().setTitle("Crumb 2").build())
        list.add(BreadCrumb.Builder().setTitle("Crumb 3").build())

        findViewById<BreadCrumbView>(R.id.bread_crumb_view).setBreadCrumbList(list)
```

You can Handle Bread Crumb Click like this
```java
  findViewById<BreadCrumbView>(R.id.bread_crumb_view).setListener(object : BreadCrumbView.Listener {
            override fun onBreadCrumbSelected(crumb: BreadCrumb) {
           // Your Logic Here
        })

```

You can Handle back button like this

```java
   override fun onBackPressed() {
        if (!findViewById<BreadCrumbView>(R.id.bread_crumb_view).goBack())
            super.onBackPressed()
    }
```



## Download

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```groovy
    dependencies {
	    implementation 'com.github.msayan:bread-crumb-view:$last_version'
	    }
```

## License

    MIT License

    Copyright (c) 2018 Mehmet Ayan

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.