说明：

1.（2016年7月7日18:00:09）增加了刷新库，在build.gradle(Project:souguuser)内添加了下面的内容。
在build.gradle(Module:spp)里面增加了compile 'com.github.Aspsine:SwipeToLoadLayout:v1.0.2'；
 allprojects {
        repositories {
            jcenter()
            //這是在刷新庫的時候用到的compile 'com.github.Aspsine:SwipeToLoadLayout:v1.0.2'
            maven { url "https://jitpack.io" }
        }
    }
 /*进度条，对话框*/
    compile 'com.lsjwzh:materialloadingprogressbar:0.5.8-RELEASE'

 2.在测试一下，待会删除