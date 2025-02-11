# Pokémon Web App

Un'applicazione web basata su Spring Boot per visualizzare e filtrare i Pokémon da un database MySQL, con funzionalità di autenticazione e session management.

Tecnologie Utilizzate

Backend: Java, Spring Boot, Spring Security, MySQL

Frontend: HTML, CSS, JavaScript

Autenticazione: Spring Security con gestione sessioni (possibile miglioramento con JWT)

Database: MySQL


# Funzionalità

✅ Gestione dei Pokémon

Importazione dei Pokémon in un database MySQL

Visualizzazione di tutti i Pokémon in una pagina web

Filtri per ID e Tipo

✅ Autenticazione Utente

Registrazione e login con nickname e password

Sicurezza della password (hashing)

Blocco dopo 5 tentativi di accesso falliti

Session Management con logout e timeout

✅ Gestione delle Sessioni

Un solo login attivo per utente

Scadenza automatica della sessione

Logout con invalidazione della sessione


# Struttura del Progetto

/pokemon-web-app
│── src/main/java/com/example/pokemonapp
│   │── controller/
│   │   ├── AuthController.java
│   │   ├── PokemonController.java
│   │── model/
│   │   ├── Pokemon.java
│   │   ├── User.java
│   │── repository/
│   │   ├── PokemonRepository.java
│   │   ├── UserRepository.java
│   │── service/
│   │   ├── AuthService.java
│   │   ├── PokemonService.java
│   │── security/
│   │   ├── SecurityConfig.java
│   │── PokemonApplication.java
│── src/main/resources/
│   ├── application.properties
│   ├── static/
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── register.html
│   │── templates/
│── pom.xml

# Configurazione del Database

Modifica il file application.properties con le credenziali del tuo database:

spring.datasource.url=jdbc:mysql://localhost:3306/pokemon_db
spring.datasource.username=tuo_username
spring.datasource.password=tuo_password
spring.jpa.hibernate.ddl-auto=update


# Avvio del Progetto

1️⃣ Clona il repository

git clone https://github.com/tuo-username/pokemon-web-app.git
cd pokemon-web-app

2️⃣ Configura il database

Assicurati che MySQL sia attivo

Modifica application.properties

Importa i dati nel database

3️⃣ Avvia il server

mvn spring-boot:run

4️⃣ Accedi all'applicazione

Apri http://localhost:8080 nel browser

Registrati o accedi con un utente esistente


# Possibili Miglioramenti

🔹 JWT Authentication: Migliorare la gestione delle sessioni con token JWT invece dei session ID.
🔹 UI Migliorata: Aggiungere React o Vue per un frontend dinamico.
🔹 Gestione avanzata utenti: Ruoli (admin/user) e profili personalizzati.

Autore: Ivan Amoruso 🚀
