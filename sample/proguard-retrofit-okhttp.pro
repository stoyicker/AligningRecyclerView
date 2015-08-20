##---------------Begin: proguard configuration for Retrofit and OkHttp----------
# Retrofit and OkHttp
-dontwarn rx.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepattributes Signature
-keepattributes *Annotation*
##---------------End: proguard configuration for Retrofit and OkHttp----------