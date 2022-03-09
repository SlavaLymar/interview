1. Создать REST-контроллер для поиска отеля по id. Написать юнит-тест(ы).
###(GET) /hotel/{id} 
### Example: http://hostname/hotel/1
2. Создать REST-контроллер для удаления отеля по id. Написать юнит-тест(ы).
###(DELETE) /hotel/{id}
### Example: http://hostname/hotel/1
3. Создать сервис поиска N - ближайших отелей к центру города cityId. Написать юнит-тест(ы).
###(GET) /search/{cityId}?n=N
### Example: http://hostname/search/1?n=3