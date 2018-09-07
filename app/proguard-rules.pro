# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose

# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify

# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses

# 避免混淆泛型
-keepattributes Signature

# 保护注解
-keepattributes *Annotation*

-ignorewarnings

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*


# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn org.apache.http.**
-keep class org.apache.http.** { *;}

#rx
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }
-dontwarn com.jakewharton.rxbinding.**
-keep class com.jakewharton.rxbinding2.** { *; }


#okhttp3.x
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.* { *;}
-dontwarn okio.**

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#okhttp
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**

#gson
-dontwarn com.google.gson.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.JsonObject { *; }
-keep class com.google.** { *; }

#stetho 用于Google浏览器网络请求抓取的工具类,facebook下产品
-dontwarn com.facebook.**
-keep class com.facebook.**{*;}

#android
-dontwarn android.content.pm.**
-keep class android.content.pm.** {*;}

#rx
-dontwarn rx.**
-keep class rx.** { *; }
-dontwarn com.jakewharton.rxbinding.**
-keep class com.jakewharton.rxbinding.** { *; }
-dontwarn rx.android.**
-keep class rx.android.** { *; }
#glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.** { *; }

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

-assumenosideeffects class android.util.Log {

public static *** d(...);

public static *** v(...);

public static *** i(...);

public static *** e(...);

public static *** w(...);

}

#EventBus
-dontwarn
-keepclassmembers class ** {
    public void onEvent*(**);
}
-dontwarn
-keepclassmembers class ** {
public void xxxxxx(**);
}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


#glide
-dontwarn
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn
-keep class com.zero.yoga.bean.response.** { *; }

-dontwarn
-keep class com.zero.yoga.internet.** { *; }

#retrofit2.x
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Retrofit
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

# okhttp
-dontwarn okio.**

# 保留 R 下面的资源
-keep class **.R$* {*;}
-keepclassmembers class **.R$* {
    public static <fields>;
}




