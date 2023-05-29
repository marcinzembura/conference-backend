##Instrukcja
1. Nalezy sklonowac repozytorium.
2. Należy sprawdzić konfiguracje z bazą danych.
3. Następnie uruchomić aplikację - domyślnie nasłuchuje na porcie 8080.


##AdresyURL:
ReservationApi:

Tworzenie rezerwacji:

Metoda: POST
Adres URL: http://localhost:8080/api/reservations
Parametry zapytania:
login: (ciąg znaków) - login użytkownika
email: (ciąg znaków) - adres e-mail użytkownika
lectureId: (liczba całkowita) - identyfikator wykładu
Przykładowe zapytanie:
POST http://localhost:8080/api/reservations?login=john&email=john@example.com&lectureId=1

Anulowanie rezerwacji:

Metoda: DELETE
Adres URL: http://localhost:8080/api/reservations/{reservationId}
Parametr ścieżki:
reservationId: (liczba całkowita) - identyfikator rezerwacji
Przykładowe zapytanie:
DELETE http://localhost:8080/api/reservations/1

Pobieranie wszystkich rezerwacji:

Metoda: GET
Adres URL: http://localhost:8080/api/reservations
Przykładowe zapytanie:
GET http://localhost:8080/api/reservations

Pobieranie rezerwacji według loginu:

Metoda: GET
Adres URL: http://localhost:8080/api/reservations/{login}
Parametr ścieżki:
login: (ciąg znaków) - login użytkownika
Przykładowe zapytanie:
GET http://localhost:8080/api/reservations/john

LectureApi:

Plan konferencji:
Metoda: GET
Adres URL: http://localhost:8080/api/lectures
Przykładowe zapytanie:
GET http://localhost:8080/api/lectures

Pobieranie wykładu po identyfikatorze:

Metoda: GET
Adres URL: http://localhost:8080/api/lectures/{id}
Parametr ścieżki:
id: (liczba całkowita) - identyfikator wykładu
Przykładowe zapytanie:
GET http://localhost:8080/api/lectures/1

Zestawienie wykładów wg zainteresowania:

Metoda: GET
Adres URL: http://localhost:8080/api/lectures/stats-interest
Przykładowe zapytanie:
GET http://localhost:8080/api/lectures/stats-interest

UserApi:

Pobieranie wszystkich użytkowników:

Metoda: GET
Adres URL: http://localhost:8080/api/users
Przykładowe zapytanie:
GET http://localhost:8080/api/users

Pobieranie użytkownika po loginie:

Metoda: GET
Adres URL: http://localhost:8080/api/users/{login}
Parametr ścieżki:
login: (ciąg znaków) - login użytkownika
Przykładowe zapytanie:
GET http://localhost:8080/api/users/john123

Aktualizowanie adresu e-mail użytkownika:

Metoda: PUT
Adres URL: http://localhost:8080/api/users/{login}
Parametr ścieżki:
login: (ciąg znaków) - login użytkownika
Parametr żądania:
newEmail: (ciąg znaków) - nowy adres e-mail
Przykładowe zapytanie:
PUT http://localhost:8080/api/users/john123?newEmail=john123@example.com

##Autor
- Marcin Zembura
