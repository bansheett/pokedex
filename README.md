# Pokémon Web App - Still In Progress

Un'applicazione web basata su Spring Boot per visualizzare e filtrare i Pokémon da un database MySQL, con funzionalità di autenticazione e session management.

Tecnologie Utilizzate

Backend: Java, Spring Boot, Spring Security, RESTApi, MySQL

Frontend: HTML, CSS, JavaScript

Autenticazione: Spring Security con gestione sessioni (possibile miglioramento con JWT)

Database: MySQLl


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


# Possibili Miglioramenti

🔹 JWT Authentication: Migliorare la gestione delle sessioni con token JWT invece dei session ID.
🔹 UI Migliorata: Aggiungere React o Vue per un frontend dinamico.
🔹 Gestione avanzata utenti: Ruoli (admin/user) e profili personalizzati.

Autore: Ivan Amoruso 🚀
