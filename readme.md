# ArouterBase

使用Arouter完成组件化项目搭建示例（单项目多模块）


# 示例说明

- ### 1.模块划分：
- 壳模块（app），首页模块（home），购物车模块（cart），我的模块（mine），用户模块（user），通用库（commonlib）
- ### 2.模块关系：
- app依赖home、cart、mine、commonlib
- home、cart、mine、user分别依赖commonlib
- ### 3.模块说明：
- app：壳模块，用户打包所有模块
- home：首页，可以从cart提供的服务中获取购物车内容，且可以添加到购物车
- cart：购物车，提供外部操作购物车服务接口实现
- mine：我的，从user模块提供的服务中获取登录信息
- user：用户模块，提供外部查询用户服务接口实现
- commonlib：通用库，包含通用依赖库，通用方法，服务接口
- ### 4.如何新增业务模块
- 1.gradle.properties 文件内添加控制module是app还是lib标记开关 
```
eg:isCartCanRunAsApp=true
```
- 2.module下的build.gradle 
- 1）标记module是app还是lib 
```
if (isCartCanRunAsApp.toBoolean()) {
	apply plugin: 'com.android.application'
} else {
	apply plugin: 'com.android.library'
}
```
- 2）如果是app，则添加包名 
```
if (isCartCanRunAsApp.toBoolean()) { 
applicationId "com.example.home"
} 
```
- 3）module是app时，需要额外指定清单文件，若新增activity，则不管是app还是lib，都需要在清单内声明 
```
sourceSets { 
main { 
if (isCartCanRunAsApp.toBoolean()) {
manifest.srcFile 'src/main/moduleManifest/AndroidManifest.xml'
} else { 
manifest.srcFile 'src/main/AndroidManifest.xml' }
}
}
```
- 4）依赖通用库 
```
implementation project(":commonlib") 
kapt 'com.alibaba:arouter-compiler:1.5.1' 
```
- 5）添加kapt插件
```
apply plugin: 'kotlin-kapt' 
defaultConfig {
kapt { arguments { arg("AROUTER_MODULE_NAME", project.getName()) } }
}
```
- 6）使用viewding
```
viewBinding{ enabled=true }
```


