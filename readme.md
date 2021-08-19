### 新增module配置步骤
1、gradle.properties 
文件内添加控制module是app还是lib标记开关 eg:isCartCanRunAsApp=true

2.module下的build.gradle
1）标记module是app还是lib
if (isCartCanRunAsApp.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
2）如果是app，则添加包名
if (isCartCanRunAsApp.toBoolean()) {
    applicationId "com.example.home"
}
3）module是app时，需要额外指定清单文件，activity不管是app还是lib，都需要在清单内声明
sourceSets {
    main {
        if (isCartCanRunAsApp.toBoolean()) {
            manifest.srcFile 'src/main/moduleManifest/AndroidManifest.xml'
        } else {
            manifest.srcFile 'src/main/AndroidManifest.xml'
        }
    }
}
4）依赖通用库
apply plugin: 'kotlin-kapt'
defaultConfig {
    kapt {
        arguments {
            arg("AROUTER_MODULE_NAME", project.getName())
        }
    }
}

implementation project(":commonlib")
kapt 'com.alibaba:arouter-compiler:1.5.1'
5）使用viewding
viewBinding{
    enabled=true
}