# Example 01: Android app without backend

## References

- [Official Android Developer trainings](https://developer.android.com/courses)
- [Official Android guides for user interface and navigation](https://developer.android.com/guide/topics/ui)
- [Official Android guides for UI controls e.g. buttons](https://developer.android.com/guide/topics/ui/controls/button)

## Conceptual Simplifications

- The example has a very simplified [manual dependency injection (DI)](https://developer.android.com/training/dependency-injection/manual). The DI frameworks like e.g. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) are not used. For more info see: [Dependency injection in Android](https://developer.android.com/training/dependency-injection)
- The example interacts with UI widgets directly by using [findViewById()](https://developer.android.com/reference/android/app/Activity#findViewById(int)) and widget methods like [setText()](https://developer.android.com/reference/android/widget/TextView#setText(java.lang.CharSequence)). Concepts like [Model-View-ViewModel (MVVM)](https://www.journaldev.com/20292/android-mvvm-design-pattern), [data binding](https://developer.android.com/topic/libraries/data-binding), [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), or [RxAndroid](https://github.com/ReactiveX/RxAndroid) are not used.
- The navigation between activities is done via [Intents](https://developer.android.com/guide/components/intents-filters). The [Navigation component](https://developer.android.com/guide/navigation) is not used.
