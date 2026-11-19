- mydemo-gradle è (oltre ad essere un progetto di prova per alcune tecnologie Java: Kakfa, Dependency Injection, spring-security) il backend che gestisce 
  il catalogo dei libri.
- Si avvia tramite main class java (spring-boot) si interfaccia con un'istanza locale di Elasticsearch (la quale deve essere prima avviata)
- Risponde su http://localhost:8080 al frontend angular (book-library-fronted)
- Se si vuole utilizzare direttamente le api da swagger: http://localhost:8080/swagger-ui/index.html
- Si interfaccia anche con il mdodulo Python (mydemo-ai-service) tramite l'api "chat" il quale deve essere prima avviato sempre in locale tramite l'apposito 
  comando (vedi relativo file README.txt).