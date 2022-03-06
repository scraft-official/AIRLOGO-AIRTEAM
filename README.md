
# CZYM JEST APLIKACJA AIRLOGO?
**AirLogo** to aplikacja, która pomaga w stawianiu pierwszych kroków w świecie programowania dla najmłodszych i nie tylko. 
Skupia się ona na przesuwaniu po planszy żółwia za pomocą wywoływania rozmaitych komend, dzięki czemu można tworzyć np. figury.
Polecenia wydawane przez użytkownika są bazowane na tych, które były  zaimplementowane w programie ATARI LOGO na konsolach ATARI w latach 80.
Rozbudowany system tworzenia procedur umożliwia jeszcze lepszą zabawę i możliwość rozwijania swoich umiejętności.
Swoją pracę można zapisać i udostępnić, za pomocą opcji zapisu planszy lub zrzutu ekranu, która widnieje na pasku przybornika.

# INSTALACJA
### WYMAGANIA SYSTEMOWE:
   - Windows 10 (32 bit / 64 bit)*,
   - 2GB+ RAM,
   - ~180+ MB Przestrzeni Dyskowej

`* Aplikacja była testowana na wersji systemu Windows 10, lecz niewykluczone jest wsparcie, także dla innych wersji systemów Windows.`

### SPOSÓB INSTALACJI:
1.	Należy pobrać instalator aplikacji wedle korzystanej wersji architektury systemu, na której będzie instalowana aplikacja. (32bit lub 64bit). [POBIERZ GO TUTAJ](https://github.com/scraft-official/AIRLOGO-AIRTEAM/releases/latest)
2.	Po pobraniu, należy otworzyć instalator za pomocą dwukrotnego kliknięcia.
3.	Gdy instalator się otworzy, należy postępować zgodnie ze wskazówkami jakie będą wyświetlane.
4.	Po zainstalowaniu, jeżeli została wybrana opcja „Utwórz ikonę Pulpitu”, to skrót aplikacji można znaleźć na pulpicie lub wyszukując go po nazwie w wyszukiwarce aplikacji.

# DOKUMENTACJA APLIKACJI
Dokumentacje aplikacji znajdziesz tutaj: [AIRLOGO - DOKUMENTACJA.pdf](https://github.com/scraft-official/AIRLOGO-AIRTEAM/files/8192457/AIRLOGO.-.DOKUMENTACJA.pdf)

# BUDOWANIE APLIKACJI
### DO ZBUDOWANIA APLIKACJI POTRZEBNE BĘDZIE:
   - [Apache Maven](https://maven.apache.org/download.cgi)
   - [JDK 11](https://www.oracle.com/pl/java/technologies/javase/jdk11-archive-downloads.html)

### JAK ZBUDOWAĆ?
   - Należy pobrać pliki tego repozytorium. (Przycisk "CODE" > Download ZIP)
   - Pobrane repozytorium należy rozpakować.
   - Wewnątrz wypakowanego folderu należy uruchomić CMD (Command Prompt) i wywołać komendę\
   ```mvn clean install```
   - Gotowe pliki aplikacji znajdują się w lokalizacji **/target/**
   
   `* Plik z koncówką -shaded.jar jest to plik JAR aplikacji ze wszystkimi bibliotekami.`\
   `* Plik z koncówką .exe jest to plik EXE aplikacji ze wszystkimi bibliotekami.`\
   `* Plik zakończony tylko .jar jest to plik JAR aplikacji bez bibliotek. (Brak możliwości uruchomienia)`

# DODATKOWE INFORMACJE:
* Wykorzystane w aplikacji ikony zostały pobrane ze strony https://www.flaticon.com
* Testy automatyczne znajdują się w katalogu plików źródłowych „SRC/TEST/JAVA” i są uruchamiane podczas eksportowania aplikacji za pomocą narzędzia Apache Maven.
