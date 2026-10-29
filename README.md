# TP 1 : Les sockets en mode connecté TCP

## Activité 1-3 : Amélioration sur le service de calculatrice

**Matière :** Développement d'applications réparties
**Établissement :** Faculté des Sciences de Sfax, Département d'informatique et des Communications
**Niveau :** LSI 3, année universitaire 2026 – 2027
**Type :** Travail individuel

---

## Description

Application Client/Serveur en Java utilisant les sockets TCP. Le serveur propose un service de calculatrice à plusieurs clients simultanément (un thread par client). Cette activité améliore l'application de l'Activité 1-2 sur deux points :

1. **Connexion entre deux machines distinctes** du même réseau local (et non plus uniquement sur `localhost`).
2. **Service de calculatrice enrichi** : Addition, Soustraction, Multiplication et Division.

## Structure du projet

```
src/
├── Server.java                  # Serveur : écoute sur l'IP de la machine et accepte les clients
├── Client.java                  # Client : menu, envoi du choix et de x, affichage du résultat
└── serverpackage/
    └── ClientProcess.java       # Thread qui traite un client côté serveur
```

## Environnement

- JDK >= 1.8
- IntelliJ IDEA (ou Eclipse / VS Code)
- Git

## Fonctionnement

### Étape 1 : connexion sur deux machines

- **Serveur** : utilisation de `InetAddress` et `InetSocketAddress`. Le `ServerSocket` est lié à l'adresse IP réelle de la machine (`InetAddress.getLocalHost()`, ou une IP passée en argument) sur le port **1234**.
- **Client** : l'adresse IP du serveur est saisie au démarrage (ou passée en argument) à la place de `localhost`, puis la connexion s'effectue avec `InetSocketAddress` (délai maximal de 5 secondes).

### Étape 2 : calculatrice améliorée

Le client affiche un menu :

| Choix | Opération |
|-------|-----------|
| 1 | Addition (`x + 5`) |
| 2 | Soustraction (`x - 5`) |
| 3 | Multiplication (`x * 5`) |
| 4 | Division (`x / 5`) |
| 0 | Quitter |

Comme dans l'Activité 1-2, la seconde opérande est la constante **5**.

### Protocole d'échange

1. À la connexion, le serveur envoie `Vous êtes le client n°N`.
2. Le client envoie le **choix** (une ligne), puis la **valeur x** (une ligne).
3. Le serveur calcule selon l'opération choisie et renvoie **une ligne** contenant le résultat.
4. Le client envoie `0` pour terminer la session.

Erreurs gérées côté serveur : valeur non numérique (`Erreur : valeur invalide`) et opération inconnue (`Erreur : opération invalide`).

## Exécution sur deux machines

1. Connecter les deux machines au **même réseau local**.
2. Sur la machine serveur, relever l'adresse IP (`ipconfig` sous Windows, `ip a` sous Linux), par exemple `192.168.1.20`.
3. Autoriser le port **1234** dans le pare-feu de la machine serveur.
4. Lancer le serveur :
   ```bash
   java Server
   # ou, si l'adresse affichée est 127.0.0.1 :
   java Server 192.168.1.20
   ```
5. Sur l'autre machine, lancer le client :
   ```bash
   java Client 192.168.1.20
   # ou lancer java Client puis saisir l'adresse IP quand elle est demandée
   ```
6. Choisir une opération dans le menu, saisir la valeur de x et lire le résultat.

## Exemple d'exécution

```
Vous êtes le client n°1

===== Calculatrice =====
1. Addition
2. Soustraction
3. Multiplication
4. Division
0. Quitter
Votre choix : 3
Valeur de x : 8
Résultat : 8.0 * 5.0 = 40.0
```

## Tests effectués

- [ ] Client et serveur sur la même machine
- [ ] Client et serveur sur deux machines distinctes
- [ ] Les quatre opérations avec plusieurs valeurs
- [ ] Valeur invalide et choix invalide
- [ ] Plusieurs clients connectés en même temps

## Auteur

Étudiant en LSI 3, Faculté des Sciences de Sfax.