ActivityRouter
===

管理 Activity 路由的一种简单实现。使用注解和 PackageManager 读取的 Activity 生成一个映射表，通过在入口 Activity 配置路径分发器来完成 Activity 的路径跳转。

* [x] 外部 Uri 调用
* [x] 无需生命 intent-filter
* [x] 无需 exported activity
* [x] 可打开深层链接
* [x] 可动态配置

## 基本配置

Application：
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}
```

TestActivity：
```java
@Route("/test")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Uri data = getIntent().getData();
        if (data != null) {
            // 取 Uri 参数
            String helloString = data.getQueryParameter("hello_string");
        }
    }
}
```

调用（内部或者外部）：
```java
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("router:///test?hello_string=HelloWorld!"));
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
```