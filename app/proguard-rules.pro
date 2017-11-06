# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/xujianhui/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#包名不混合大小写
-dontusemixedcaseclassnames

#不跳过非公共的库的类
-dontskipnonpubliclibraryclasses

#混淆时记录日志
-verbose

#关闭预校验
-dontpreverify

#不优化输入的类文件
-dontoptimize

#保护注解
-keepattributes *Annotation*

#保持所有拥有本地方法的类名及本地方法名
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#保持Activity中View及其子类入参的方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}

#Parcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

#R文件的静态成员
-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn android.support.**

#keep相关注解
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**


-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class com.squareup.okhttp.** {*;}
-keep class okio.** {*;}

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** {*;}

-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** {*; }

-dontwarn  org.apache.http.**
-keep class org.apache.http.** {*; }

-dontwarn android.net.http.**
-keep class android.net.http.** {*; }

-dontwarn com.baidu.location.**
-keep class com.baidu.location.** {*;}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
   protected void handleIntent(android.content.Intent);
}

-keepclassmembers class * extends android.app.Activity {
   public *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable
-keep public class * implements java.io.Serializable {*;}


-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep class com.myfashionlife.base.adapter.** {*;}
-keep class com.myfashionlife.manager.** {*;}
-keep class com.myfashionlife.db.** {*;}
-keep class com.myfashionlife.listener.** {*;}
-keep class com.myfashionlife.manager.** {*;}
-keep class com.myfashionlife.net.** {*;}
-keep class com.myfashionlife.test.** {*;}
-keep class com.myfashionlife.ui.** {*;}
-keep class com.myfashionlife.util.** {*;}
-keep class com.myfashionlife.widget.** {*;}
-keep class com.myfashionlife.ui.** {*;}
-keep class com.myfashionlife.ui.** {*;}
-keep class com.myfashionlife.ui.** {*;}

