
> ####  **请注明出处http://blog.csdn.net/qq_23179075/article/details/77343623**
----------
> ### **描述:<font color="#FF4567">  一个通用的数据加载界面,可以显示空布局,加载时布局,错误布局...**
----------


### **使用方式:在build.gradle中加入以下代码**
```java
compile 'com.zhengliang:LoadDdataLayout:1.0.1'
```


----------


### **使用方式xml:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.bunny.android.testapplication.MainActivity">
    <com.bunny.android.library.LoadDataLayout
        android:id="@+id/ldl"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <ListView
            android:id="@+id/lv"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.bunny.android.library.LoadDataLayout>
</RelativeLayout>
```
### **java代码中需要绑定布局**
```ldl.setBindView(lv);```

### **效果:**
#### **默认效果:(自带效果,没有加动画和图片)**
	
![效果.gif](http://upload-images.jianshu.io/upload_images/2909848-9dc4a3a4dd90b8e8.gif?imageMogr2/auto-orient/strip)	


----------


### **手动设置图片和提示文字:**
``` xml 
    <com.bunny.android.library.LoadDataLayout
        android:id="@+id/ldl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:ldl_loading_tv="不要急,等我加载完成..."
        app:ldl_empty_tv="看啥看,什么也没有..."
        app:ldl_error_tv="oh no! 好像出错了..."
        app:ldl_loading_img="@mipmap/ajax_loader"
        app:ldl_empty_img="@drawable/ic_empty"
        app:ldl_error_img="@drawable/ic_error">
        <ListView
            android:id="@+id/lv"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.bunny.android.library.LoadDataLayout>
```
### **效果:**
![效果.gif](http://upload-images.jianshu.io/upload_images/2909848-1b87f96ac49e4842.gif?imageMogr2/auto-orient/strip)


----------


### **自定义属性说明:**
| xml自定义属性| 功能|
| ------------- |:-------------|
|ldl_loading_tv |加载中提示文字 |
|ldl_empty_tv |空布局提示文字 |
|ldl_error_tv |错误布局提示文字 |
|ldl_loading_img |加载中显示的图片 |
|ldl_empty_img |空布局显示的图片 |
|ldl_error_img |错误布局显示的图片 |

### **java中对应代码:**
#### **加载中布局设置样式重载函数:**
``` 
    /**
     * 显示默认样式
     */
    public void showLoading();
    
    /**
     * 设置显示文字
     * @param s 提示文字
     */
    public void showLoading(String s);
    /**
     * 设置图片
     * @param callBack 设置图片回调接口
     */
    public void showLoading(SetImgCallBack callBack);
    /**
     * @param s 提示文字
     * @param callBack 设置图片回调接口
     */
    public void showLoading(String s, SetImgCallBack callBack)
```

#### **空布局设置样式重载函数(参数同上): `showEmpty();`** 
#### **错误布局设置样式重载函数(参数同上): `showError();`** 


----------


### **一般加载数据时都会显示一个 gif 动画，当然直接在xml设置动画是不行的,如上图，这时候就可以通过java代码来设置图片:(例子中动画用Glide来加载的)**
``` java
        ldl.showLoading(new LoadDataLayout.SetImgCallBack() {
            @Override
            public void setImg(ImageView img) {
                Glide.with(MainActivity.this)
                        .load(R.mipmap.ajax_loader)
                        .asGif()
                        .into(img);
            }
        });
```
### **效果:**

![123.gif](http://upload-images.jianshu.io/upload_images/2909848-c278ba2b5af5af2c.gif?imageMogr2/auto-orient/strip)


----------


### **如果觉得自带的几个布局不满足您的需求，你也可以自定义这个三个布局。自己写好三个布局设置到对应的属性即可...【哎, 主要是我也找不到好看的素材,宝宝心里苦！！！】**

```
    <com.bunny.android.library.LoadDataLayout
        android:id="@+id/ldl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:ldl_loading_layout="@layout/layout_loading"
        app:ldl_empty_layout="@layout/layout_empty"
        app:ldl_error_layout="@layout/layout_error"
        >
        <ListView
            android:id="@+id/lv"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.bunny.android.library.LoadDataLayout>
```
### **自定义属性说明:**
| xml自定义属性| 功能|
| ------------- |:-------------|
| ldl_loading_layout | 加载中的布局  |
| ldl_empty_layout | 空布局 |
| ldl_error_layout | 错误布局  |

#### **当然设置了这个布局属性, 对应的设置文字和图片的属性也就没用了!**

#### **个人CSDN地址:http://blog.csdn.net/qq_23179075**
