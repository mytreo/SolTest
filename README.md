## Soltest
---
Данные читаются/ сохраняются в  _MAIN_XML_PATH_=*"/main.xml"* (Без прав администратора изменить например на "D:/main.xml");

Интервал сохранения в мс _BETWEEN_SAVES_PAUSE_;

У сервиса один метод **/changeBook** , который принимает запрос в виде XML вида

```
<?xml version="1.0"?>
<catalog>
   <book id="bk103">
        <author>Gambardella, Eva</author>
        <title>Maeve Ascendant</title>
        <genre>Fantasy</genre>
        <price>5.95</price>
        <publish_date>2000-11-17</publish_date>
        <description>After the collapse of a nanotechnology</description>
    </book>
</catalog>
```

По id определяем наличие книги
* В случае если книга отсутствует Insert
* В случае если книга присутсвует
    * поля author,title, genre пусты Delete
    * иначе Update
Response - актуальный список книг в виде xml.

В случае отправки пустого POST запроса с типом
*application/xml, text/xml* возвращается весь список книг;
