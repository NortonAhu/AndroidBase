# Android UI 学习记录

# 自定义View学习

* View的基础知识
* 组合View-自定义标题实现(2016.4.17)


![](\image/device-2016-04-17-151618.png)

* 图像处理学习总结(2016.4.18)

&emsp;Android 图像处理主要包含的内容有美术知识/线性代数

    * 美术知识

        - 色调\色相 颜色值具体的就是 RGBA模型的四个值 
        - 饱和度 颜色的纯度范围从 0%到100% 颜色的鲜艳程度,当数值为0%时表示的黑白颜色的值;
        - 亮度 表示的是图片的亮度
        
    * 线性知识 主要是矩阵的变化

矩阵的变化主要也是针对颜色的三个属性进行设置的. 颜色矩阵是一个 4*5 的矩阵.源码看出来是使用一个数组的方式实现的
[a,b,c,d,e,
 f,g,h,i,j,
 k,l,m,n,o,
 p,q,r,s,t]

上面是变换矩阵,变换后的数据是:

R'= a*R + b*G + c*B + d*A +e;
G'= f*R + g*G + h*B + i*A +j;
B'= k*R + l*G + m*B + n*A +o;
A'= p*R + q*G + r*B + s*A +t;

Android 中用来实现图像处理的类是 ColorMatrix

ColorMatrix colorMatrix = new ColorMatrix();

源码上看:

```
 public ColorMatrix() {
        reset();
    }
```

它会初始化这个矩阵,reset()的实现是:

```
 public void reset() {
        final float[] a = mArray;
        Arrays.fill(a, 0);
        a[0] = a[6] = a[12] = a[18] = 1;
    }
```

在源码中的矩阵就是使用一个20大小的数据,(Arrays.fill(a,0)初始化这个数组为0),然后在特定的位置上初始化矩阵

[1,0,0,0,0
 0,1,0,0,0
 0,0,1,0,0
 0,0,0,1,0]
 
ColorMatrix 其他的几个构造方法都是参数型的初始化这里不做详细描述,其中关键的三个方法是:

```
/**
     * Set the rotation on a color axis by the specified values.
     * <p>
     * <code>axis=0</code> correspond to a rotation around the RED color
     * <code>axis=1</code> correspond to a rotation around the GREEN color
     * <code>axis=2</code> correspond to a rotation around the BLUE color
     * </p>
     */
    public void setRotate(int axis, float degrees)
```

这个方法主要是针对RGB进行设置,所以这个是针对色调进行设置,0表示Red,1表示Green,2表示Blue

```
 /**
     * Set the matrix to affect the saturation of colors.
     *
     * @param sat A value of 0 maps the color to gray-scale. 1 is identity.
     */
    public void setSaturation(float sat)
```

该方法主要是表示设置颜色的饱和度


````
    /**
     * Set this colormatrix to scale by the specified values.
     */
    public void setScale(float rScale, float gScale, float bScale,
                         float aScale) {
        final float[] a = mArray;

        for (int i = 19; i > 0; --i) {
            a[i] = 0;
        }
        a[0] = rScale;
        a[6] = gScale;
        a[12] = bScale;
        a[18] = aScale;
    }
````

该方法表示的是设置相应的亮度的设置,从代码上看是对矩阵中间对角线设置,这个值设置比较大的话显得比较亮.

* 图像处理的高级模式 像素处理

每一个像素点都是RGBA模型

常用的几个图像处理的计算公式:

    * 底片效果
        ABC三个像素点求像素点B的底片效果
        
        B.r = 255 - B.r
        B.g = 255 - B.g
        B.b = 255 - B.b
        
    * 老照片效果
        
        newR = (int)(0.393 * PixR + 0.769 * PixG + 0.189 * PixB);
        newG = (int)(0.349 * PixR + 0.686 * PixG + 0.168 * PixB);
        newB = (int)(0.272 * PixR + 0.534 * PixG + 0.131 * PixB);
    
    * 浮点效果
        
        ABC 三个点求B点的浮雕效果
        B.r = C.r - B.r + 127;
        B.g = C.g - B.g + 127;
        B.b = C.b - B.b + 127;
        
     
  图片的效果如下:
  
  <img src="/image\device-2016-04-19-222441.png" width="360px" height="720px">
   
        
        

    
    