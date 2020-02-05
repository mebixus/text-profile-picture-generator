# text-profile-picture-generator
This is a java tool class to generate text profile pictures (or rather name profile picture) with oval, circular, rectangular, rounded rectangular or polygonal background.
It can be used to generate profile picture for user using their username or other text.  

You can specified the following params of the generated picture:  
1. the size of the picture  
2. the font and color of the text(name)  
3. the shape and color of the background

The text will be set in the center of the generated png image automatically.

**Some examples:** 

```java
TextProfilePictureGenerator.generateOvalTextProfilePicture(100, 100,
        "李白",
        new Font("Yuanti SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("e3f2fd", 16)),
        new Color(Integer.parseInt("ff8a65", 16)),
        "/Users/Shared/test/test1.png");

    TextProfilePictureGenerator.generateRectTextProfilePicture(100, 100,
        "鲁班",
        new Font("Wawati SC", Font.PLAIN, 50),
        new Color(Integer.parseInt("e0e0e0", 16)),
        new Color(Integer.parseInt("00897b", 16)),
        "/Users/Shared/test/test2.png");

    TextProfilePictureGenerator.generateRoundRectTextProfilePicture(100, 100,
        50, 50,
        "露娜",
        new Font("Yuanti SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("e3f2fd", 16)),
        new Color(Integer.parseInt("7e57c2", 16)),
        "/Users/Shared/test/test3.png");

    int[] xPoints = new int[]{0, 140, 150, 140, 0, 10};
    int[] yPoints = new int[]{0, 0, 75, 150, 150, 75};
    TextProfilePictureGenerator.generatePolygonTextProfilePicture(xPoints,
        yPoints,
        "明世隐",
        new Font("Wawati SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("212121", 16)),
        new Color(Integer.parseInt("0288d1", 16)),
        "/Users/Shared/test/test4.png");
```

The output images are as follow:  
![image](https://github.com/mebixus/text-profile-picture-generator/blob/master/test1.png)

![image](https://github.com/mebixus/text-profile-picture-generator/blob/master/test2.png)

![image](https://github.com/mebixus/text-profile-picture-generator/blob/master/test3.png)

![image](https://github.com/mebixus/text-profile-picture-generator/blob/master/test4.png)

 
