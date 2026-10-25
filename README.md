# PE02 - Hash i Salt

Pràctica de Programació de Processos sobre funcions de resum (SHA-256) i l'ús de salt per desar contrasenyes.

## Estructura del repositori

```
PE02_Hash_Salt/
├── .vscode/
│   └── settings.json          # Configuració del projecte Java a VS Code
├── bin/                       # Fitxers .class compilats
├── docs/
│   ├── Fase 5 - HASH.pdf      # Fase 5: proves de la pràctica
│   ├── missatge.txt           # Missatge original (generat a la Fase 2)
│   ├── missatgeCanviat.txt    # Missatge modificat per provar la integritat
│   └── missatge.hash          # Hash del missatge original
├── lib/                       # Dependències (buida)
├── src/
│   ├── App.java               # Menú principal per accedir a cada fase
│   ├── Fase1.java             # Fase 1: càlcul de resums SHA-256
│   ├── Fase2.java             # Fase 2: verificació de la integritat dels missatges
│   ├── Fase3.java             # Fase 3: registre d'usuaris amb salt
│   └── Fase4.java             # Fase 4: inici de sessió i verificació de contrasenyes
├── Hash01.java                # Exemple inicial de hash SHA-512
└── README.md
```

## Execució

Executa `src/App.java` i tria la fase al menú principal. La Fase 4 fa servir els usuaris registrats a la Fase 3 durant la mateixa execució.
