1. Создать REST-контроллер для поиска отеля по id. Написать юнит-тест(ы).
### (GET) /hotel/{id} 
### Example: http://hostname/hotel/1
2. Создать REST-контроллер для удаления отеля по id. Написать юнит-тест(ы).
### (DELETE) /hotel/{id}
### Example: http://hostname/hotel/1
3. Создать сервис поиска N - ближайших отелей к центру города cityId.

Для поиска расстояния между центром города и отелем использовать формулу поиска расстояния между двумя точками:
![f4b41f5deeabcf1ad6ac5b2f1ecc997d](https://user-images.githubusercontent.com/16633265/157454496-261e3054-4b74-4c11-bf37-0e36aa93f08a.jpeg)

Написать юнит-тест(ы).
### (GET) /search/{cityId}?n=N
### Example: http://hostname/search/1?n=3
