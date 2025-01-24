# Генерация QR-Кода

## Настройки
Для начала подключить библиотеку

Пример на Maven
``` xml
<dependencies>  
    <dependency>  
        <groupId>com.google.zxing</groupId>  
        <artifactId>core</artifactId>  
        <version>3.5.1</version>  
    </dependency>  
    <dependency>  
        <groupId>com.google.zxing</groupId>  
        <artifactId>javase</artifactId>  
        <version>3.5.1</version>  
    </dependency>  
</dependencies>
```


Дальше надо определить какие данные и куда мы будем сохранять

```java
public static void main(String[] args) throws Exception {  
    String data = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";  
    String filePath = "QRCode.png";  
    generateQrCode(data, filePath);  
}
```



## Метод генерации


Настройка параметров QR кода
```Java
//Устанавливаем параметры для генерации QR-кода  
Map<EncodeHintType,Object> hintMap = new HashMap<>();  
//Устанавливаем отступы вокруг QR-кода  
hintMap.put(EncodeHintType.MARGIN, 1);
```

#### Базовые элементы
`EncodeHintType.MARGIN` - отступ
`EncodeHintType.ERROR_CORRECTION` - коррекция ошибок работает в связке: 
`ErrorCorrectionLevel.H` где `H` уровень коррекции, еще есть `L, M, Q`
`CHARACTER_SET` - установка кодировки символов

#### Генерация
```java
//Генерация QR кода  
BitMatrix matrix = new MultiFormatWriter()  
        .encode(data, BarcodeFormat.QR_CODE, 200,200 ,hintMap);
```

`data` - наши данные для сохранения
`BarcodeFormat` - выбор формата 
`width=200 и height=200`- ширина и высота. Это не размер самого QR-кода на экране или в печати, а его внутренний размер, то есть размер сетки, которая будет заполнена битами (черный и белый цвета).

#### Преобразование в изображение

```java
// Преобразуем BitMatrix в изображение  
BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);  
for (int i = 0; i < 200; i++) {  
    for (int j = 0; j < 200; j++) {  
        image.setRGB(i, j, matrix.get(i, j) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());  
    }  
}
```

`matrix.get(i, j)` - возвращает булево значение `true`если пиксель черный и `false` если пиксель белый
## Метод сохранения

```java
public static void saveImage(BufferedImage image, String filePath) throws Exception {  
    // Сохраняем изображение на диск  
    ImageIO.write(image, "PNG", new File(filePath));  
    System.out.println("QR-код успешно сгенерирован и сохранен в " + filePath);  
}
```

## Результат
![QRCode](https://github.com/user-attachments/assets/6c2ad6e8-cd7d-46d7-8abb-4b4906a75fc9)


