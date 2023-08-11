# Stately

Stately is a library for managing different states of a content in Jetpack Compose.

| <img src="art/main.gif" width="170"/> | <img src="art/loading.gif" width="170"/> | <img src="art/loadingwithcontentcircle.gif" width="170"/> | <img src="art/loadingwithcontentprogress.gif" width="170"/> | <img title="" src="art/infostate.png" alt="" width="170"> |
| ------------------------------------- | ---------------------------------------- | --------------------------------------------------------- | ----------------------------------------------------------- | --------------------------------------------------------- |

## Usage

To use the code, first create an `LayoutState` instance with using `rememberContentState`. Then, use the `content parameter of layout state` to customize state.

```kotlin
val contentState = rememberContentState()

LaunchedEffect(Unit) {
  contentState.content = /* new state */
}
```

You can fill contentLayout, infoLayout, loadingLayout as you like.

```kotlin
Stately(
    modifier = Modifier.fillMaxSize(),
    layoutState = contentState,
    infoLayout = { model ->
        InfoState(
            modifier = Modifier.fillMaxSize(),
            model = model,
            retryButtonClicked = onRetryButtonClick
        )
    },
    loadingLayout = {
        LoadingState()
    },
    contentLayout = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SampleContent(modifier = Modifier.padding(8.dp))
        }
    },
)

```

## Including in your project

Add the JitPack repository to your project-level `build.gradle` file:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency to your app-level `build.gradle` file:

```
dependencies {
    implementation 'com.github.trendyol:stately:1.0.0'
}
```

Sync your project with Gradle files.

## License
```
MIT License

Copyright (c) 2023 Trendyol

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
```
