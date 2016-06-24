>前言：近来学习了Android Material Design 兼容库，为了把这个弄懂，才有了这篇博客，这里先推荐两篇博客：1.[Android Material Design 兼容库的使用详解](http://www.jianshu.com/p/1e6eed09d48b)
2.[Android应用Design Support Library完全使用实例 ](http://www.open-open.com/lib/view/open1433385856119.html)
第一篇博客是这个兼容库的详细解析，我参考了里面的许多内容，第二篇是兼容库的大致介绍，如果你能把这两篇全部弄懂，我这篇也没有必要看了。说了这么多，开始正文吧，大家有什么问题或建议，欢迎留言交流。

文章所用的示例地址：[[mecury/MeterialDesignSupportLibrary]](https://github.com/mecury/MeterialDesignSupportLibrary)

## 1.概述
在2015年，google官方升级了Design Support Library，为开发者增加了不少可用的开发控件。
## 2综述
支持Android 2.1以上设备。
Gradle build script dependency：
>compile 'com.android.support:design:22.2.0' //可修改版本号为自己匹配

Design Support Library包含8个控件，具体如下：
+  android.support.design.widget.TextInputLayout
**强大带提示的MD风格的EditText**
+  android.support.design.widget.FloatingActionButton
**MD风格的圆形按钮，来自于ImageView**
+android.support.design.widget.Snackbar
**类似Toast，添加了简单的单个Action**
+  android.support.design.widget.TabLayout
**选项卡**
+  android.support.design.widget.NavigationView
**DrawerLayout的SlideMenu**
+  android.support.design.widget.CoordinatorLayout
**超级FrameLayout**
+ android.support.design.widget.AppBarLayout
**MD风格的滑动Layout**
+ android.support.design.widget.CollapsingToolbarLayout
**可折叠MD风格ToolbarLayout**

**注意事项：
这里需要添加额外的命名空间appNs（xmlns:app="http://schemas.android.com/apk/res-auto"  ）**

在介绍各个控件之前，大家先看一下预览图，这里我自己写了一个示例，看起来不太好，但是用来介绍是够了，下面看图。


![演示动画.gif](http://upload-images.jianshu.io/upload_images/1916953-5e55463ed8193d8b.gif?imageMogr2/auto-orient/strip)

### 2.1 FLoatingActionButton

**演示gif**

![FloatingActionButton]NU.png](http://upload-images.jianshu.io/upload_images/1916953-89275ffd20b1f6e5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

一个负责显示界面基本操作的圆形按钮。Design library中的FloatingActionButton 实现了一个默认颜色为主题中colorAccent的悬浮操作按钮。FloatingActionButton继承自ImageView，你可以通过android:src或者 ImageView的任意方法，比如setImageDrawable()来设置FloatingActionButton里面的图标。下面介绍几种常用的设置属性：
+  app:fabSize  设置按钮的大小，有mini和normal可选，默认为normal
+  app:elevation 设置按钮的高度，影响阴影范围的显示
+  app:rippleColor  设置涟漪效果的颜色（当点击按钮时的水波纹）

**code：**
<code><android.support.design.widget.FloatingActionButton    android:id="@+id/floatingButton"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:src="@mipmap/ic_launcher"
app:elevation="5dp"
app:fabSize="normal" />
</code>

**无特别注意项，和普通控件类似。**

### 2.2 TextInputLayout
**演示gif：**

![TextInputLayout.gif](http://upload-images.jianshu.io/upload_images/1916953-d516fd40ac84f38f.gif?imageMogr2/auto-orient/strip)


在MD中，使用TextInputLayout将EditText进行了封装。其最大的改变就是为EditText添加了两个提示效果。一个显示在上面，提示用户输入的是什么，另一个可以设为Error提示，当输入错误的时候显示出来。

**code:**
```
<android.support.design.widget.TextInputLayout
android:id="@+id/textInput"
android:layout_width="match_parent"
android:layout_height="wrap_content">
<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content" />
</android.support.design.widget.TextInputLayout>
```
在代码中使用的是时候，需要监听EditText的变化。
 ```
editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("TAG",s.length()+"");
                if (s.length() > 4){//字符超过5个时，出现EditText提示
                    inputLayout.setError("字符不能超过5个");
                    inputLayout.setErrorEnabled(true);
                }else{
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });```

**注意项**
TextInputLayout中至少嵌套一个EditText。

### 2.3  SnackBar

**演示gif：**
![SnackBar.gif](http://upload-images.jianshu.io/upload_images/1916953-062d5cf239d70d6b.gif?imageMogr2/auto-orient/strip)


SnackBar提供一个轻量级的、快速的反馈。它可以说是一个增强功能的Toast，不同的是SnackBar被固定在底部（Snackbar试图找到一个合适的父亲以确保自己是被放置于底部），包含一段文本信息与一个可选的操作按钮。它会在时间到达时删除，或者被用户点击按钮提前删除。
**code**
SnackBar的使用十分简单，和Toast十分类似，使用下面代码就可以创建一个SnackBar：
```
final Snackbar snackbar = Snackbar.make(view,"你点击按钮",Snackbar.LENGTH_SHORT);
snackbar.show();
```
此时如果，你想添加在SnackBar上添加一个按钮，你可以这样：
```
snackbar.setAction("知道了", new View.OnClickListener() {
@Override
 public void onClick(View v) {
 snackbar.dismiss();
 }});
```
上面代码放在一起，就是动态图的效果。

**无特别注意项，和普通控件类似。**

###CoordinatorLayout

![CoordinatorLayout.gif](http://upload-images.jianshu.io/upload_images/1916953-bc762c058ab1fde0.gif?imageMogr2/auto-orient/strip)

我们来看看官方对他的描述：
- CoordinatorLayout is a super-poweredFrameLayout.
- CoordinatorLayout is intended for two primary use cases:1.As a top-level application decor or chrome layout2.As a container for a specific interaction with one or more child views
从这里我们可以知道它是一个增强版的FrameLayout，他可以协调其子View的交互，控制手势机滚动技巧。这个控件十分的强大，用处也十分的广泛。就比如刚才说的FloatingActionButton如果用CoordinatorLayout 作为FloatingActionButton的父布局，它将很好的协调Snackbar的显示与FloatingActionButton（见上图，可以见到FloatingActionButton随着SnackBar的出现而移动），在Snackbar以动画效果进入的时候自动向上移动让出位置，并且在Snackbar动画地消失的时候回到原来的位置，不需要额外的代码。

CoordinatorLayout的另一个用例是ActionBar与滚动技巧。你可能已经在自己的布局中使用了Toolbar ，它允许你更加自由的自定义其外观与布局的其余部分融为一体。Design library把这种设计带到了更高的水平，使用AppBarLayout可以让你的Toolbar与其他View（比如TabLayout的选项卡）能响应被标记了ScrollingViewBehavior的View的滚动事件。

**code**
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">
<TextView
 android:id="@+id/tv"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="CoordinatorLayout展示界面，当点击右下角的FloatingActionButton时，可以看到明显的移动。另外：点击下面的按钮跳转到CoordinatorLayout，AppbarLayout,toolbar等演示界面：" />
<android.support.v7.widget.AppCompatButton
android:id="@+id/act_btn"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="100dp"
android:text="跳转"
/>
<android.support.design.widget.FloatingActionButton
android:id="@+id/fab_btn"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="bottom|right"
android:layout_margin="10dp"
android:src="@mipmap/ic_launcher"
app:fabSize="normal"
/>
</android.support.design.widget.CoordinatorLayout>
```

**无特别注意项，和普通控件类似。**

###NavigationView
**演示gif**
这个在上面的录制过程中忘了录了，这里也录的有点小瑕疵，大家见谅啊。
![Navigation.gif](http://upload-images.jianshu.io/upload_images/1916953-4af0a684c8aaa30a.gif?imageMogr2/auto-orient/strip)

NavgationView是一个抽屉式的导航控件，它可以让我们很方便的建立一个滑动菜单。
关于用法，以上图为例，见下面代码：
**code**
navigationview_layout.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">

   <!--内容区-->
<include layout="@layout/activity_main"
/>

<!--左侧导航菜单-->
<android.support.design.widget.NavigationView
android:id="@+id/navigationView"
android:layout_width="wrap_content"
android:layout_height="match_parent"
android:layout_gravity="start"
android:fitsSystemWindows="true"
app:headerLayout="@layout/head"
app:menu="@menu/navigationview_item"
/>
</android.support.v4.widget.DrawerLayout>
```
里面内容区的布局，就是侧滑不滑动时显示的布局。里面headerLayout和menu分别为侧滑导航中的头部和item。这里我头部用了一张图片：
head.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical">

<ImageView
android:layout_width="match_parent"
android:layout_height="match_parent"
android:adjustViewBounds="true"
android:src="@drawable/header"
/>
</LinearLayout>
```
对于menu，就是菜单项的配置：
navigationview_item.xml:
```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto">
<group
android:checkableBehavior="single">
<item
android:id="@+id/menu_main"
android:icon="@mipmap/ic_home_white"
android:title="首页"
app:showAsAction="always"
/>
<item
android:id="@+id/menu_about"
android:icon="@mipmap/ic_about"
android:title="关于"
app:showAsAction="always"
/>
</group>
</menu>
```
上面的布局已经完成了，如果我们添加抽屉导航的Item的点击事件，那又该怎么做呢？下面简单介绍一下：
```
private void navigationViewListener(){
NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigationView);
mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
@Override
public boolean onNavigationItemSelected(MenuItem item) {
switch(item.getItemId()){
case R.id.menu_main:
Toast.makeText(MainActivity.this, "点击了首页", Toast.LENGTH_SHORT).show();
break;
case R.id.menu_about:
Toast.makeText(MainActivity.this, "点击了关于", Toast.LENGTH_SHORT).show();
break;
}
item.setChecked(true);
mDrawerLayout.closeDrawer(Gravity.LEFT); //关闭侧滑菜单
return false;
}
});
}
看了上面的代码，大家应该很明白应该怎么做了。
```

###TabLayout

![TabLayout.png](http://upload-images.jianshu.io/upload_images/1916953-4fcc58c38233c0dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

通过选项卡的方式切换View并不是MD中才有的新概念，它们和顶层导航模式或者组织app中不同分组内容（比如，不同风格的音乐）是同一个概念。 Design library的TabLayout 既实现了固定的选项卡（View的宽度平均分配），也实现了可滚动的选项卡（View宽度不固定同时可以横向滚动）。如果你使用ViewPager在 tab之间横向切换，你可以直接从PagerAdapter的getPageTitle() 中创建选项卡，然后使用setupWithViewPager()将两者联系在一起。它可以使tab的选中事件能更新ViewPager,同时 ViewPager的页面改变能更新tab的选中状态。

```
ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragments);
viewPager.setAdapter(mAdapter);
tabLayout.setupWithViewPager(viewPager);
tabLayout.setTabsFromPagerAdapter(mAdapter);
```
**注意事项**
如果你使用ViewPager在tab之间横向切换，切记可以直接从PagerAdapter的getPageTitle() 中创建选项卡，然后使用setupWithViewPager()将两者联系在一起。

###AppBarLayout、CollapsingToolbarLayout
这介绍这两个之前，大家要明白什么是ToolBar。
ToolBar:这代表一个标题栏，图中的绿色区域就是ToolBar。

AppBarLayout:其继承于LinearLayout，使用AppBarLayout可以让包含在其中的子控件能响应被标记了ScrollingViewBehavior的的滚动事件（要与CoordinatorLayout 一起使用），利用它我们可以很容易的去实现视差滚动效果,见下图（蓝色区域就是AppBarLayout）。

![AppBarLayout.gif](http://upload-images.jianshu.io/upload_images/1916953-5b9a1ffec9403a9f.gif?imageMogr2/auto-orient/strip)

CollapsingToolbarLayout:可伸缩折叠的Toolbar （Collapsing Toolbar），直接添加Toolbar到AppBarLayout可以让你使用enterAlwaysCollapsed和 exitUntilCollapsedscroll标志，但是无法控制不同元素如何响应collapsing的细节。当你让CollapsingToolbarLayout和Toolbar在一起使用的时候，title 会在展开的时候自动变得大些，而在折叠的时候让字体过渡到默认值。

这里面有一些属性十分重要：这里先介绍一下：
**1.app:layout_collapseMode**
**pin：**来确保Toolbar在view折叠的时候仍然被固定在屏幕的顶部
**parallax：**见3
**2. app:layout_scrollFlags**
**scroll**: 所有想滚动出屏幕的view都需要设置这个flag，没有设置这个flag的view将被固定在屏幕顶部。
**enterAlways**: 这个flag让任意向下的滚动都会导致该view变为可见，当向上滑的时候Toolbar就隐藏，下滑的时候显示。
**enterAlwaysCollapsed**: 顾名思义，这个flag定义的是何时进入（已经消失之后何时再次显示）。假设你定义了一个最小高度（minHeight）同时enterAlways也定义了，那么view将在到达这个最小高度的时候开始显示，并且从这个时候开始慢慢展开，当滚动到顶部的时候展开完。
**exitUntilCollapsed**: 同样顾名思义，这个flag时定义何时退出，当你定义了一个minHeight，这个view将在滚动到达这个最小高度的时候消失。

**3.定位到ImageView，有这两个属性是我们平常用没看到的，说明我写在注释上了**
```
app:layout_collapseMode="parallax"//这个是配置当ImageView消失或者显示时候有一种视差滚动效果
app:layout_collapseParallaxMultiplier="0.7"//视差因子，越大视差特效越明显，最大为1
```
**code**
xml文件：
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/root"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!--toolbar-->
    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="300dp">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsingtoolbarlayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="#77db93"
                app:layout_collapseMode="pin"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
　　　　　　　<!--省略了很多ImagView-->
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:src="@mipmap/ic_launcher" />

        </LinearLayout>
    </android.support.v4.widget.NestedScrollView>

</android.support.design.widget.CoordinatorLayout>


```
activity:

```
public class CoordinationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        collapsingToolbarLayout.setTitle("极客学院");  //设置toolbar的标题
        //使用右上角的返回按钮
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
```
**附(一些控件使用使用应注意的地方):**
1.在使用CardView的时候，一定要注意，当CardView的宽和高填充父容器的时候，CardView的margin最好要比cardElevation大，不然就看不到立体的效果。

2.我们知道ListView有一个onItemClick的事件，但是RecyclerView却没有，那么我们应该怎样去设置呢？其实很简单，关于RecyclerView设置item的点击事件，只需在创建ViewHolder的时候，给填充的View设置单击事件即可。

3.在使用android.support.design.widget.AppBarLayout的时候内容区最好使用android.support.v4.widget.NestedScrollView，之前我的内容区用的是ScrollView，在往上拉的时候AppBarLayout一直没有动画效果，折腾了几个小时都没找到原因。最后逼不得用Android Studio创建了一个他自带的关于AppBarLayout的模板项目，看到他用的是NestedScrollView作为内容区，我果断就把我的内容区换成了这个，立马就有动画效果了。
NestedScrollView官方的描述：
NestedScrollView is just likeScrollView, but it supports acting as both a nested scrolling parent and child on both new and old versions of Android. Nested scrolling is enabled by default.
如果感觉还不错的就给个喜欢支持一下吧，有问题请留言，谢谢
最后附一张MD的主题色解析图:


![主题色.jpg](http://upload-images.jianshu.io/upload_images/1916953-5a1442f9d9add28c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**参考**
1.[Android Material Design 兼容库的使用详解](http://www.jianshu.com/p/1e6eed09d48b)
2.[Android应用Design Support Library完全使用实例 ](http://www.open-open.com/lib/view/open1433385856119.html)