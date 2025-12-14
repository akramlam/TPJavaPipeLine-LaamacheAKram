# Rapport TP Java Pipeline

**Étudiant** : Laamache Akram  
**Date** : Décembre 2024  
**Module** : DevOps / CI-CD

---

## 1) Contexte & Objectif

L'objectif de ce TP est d'automatiser le processus de **build** et de **test** d'un projet Java Maven en utilisant **Jenkins Pipeline** avec un **agent Docker**.

Cette approche permet de :
- Garantir un environnement de build reproductible
- Automatiser les tests à chaque commit
- Faciliter le déploiement continu (CI/CD)

---

## 2) Outils utilisés

| Outil | Version | Rôle |
|-------|---------|------|
| **Jenkins** | LTS | Serveur d'intégration continue |
| **Docker** | Latest | Conteneurisation de l'agent de build |
| **Maven** | 3.9.6 | Gestion de build et dépendances |
| **Java** | 17 (Temurin) | Langage de programmation |
| **JUnit 5** | 5.10.1 | Framework de tests unitaires |
| **Git** | Latest | Gestion de version |

---

## 3) Architecture de la Pipeline

```
┌─────────────────────────────────────────────────────────────┐
│                        JENKINS                               │
├─────────────────────────────────────────────────────────────┤
│  ┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐ │
│  │ Checkout │ → │  Build   │ → │   Test   │ → │ Package  │ │
│  └──────────┘   └──────────┘   └──────────┘   └──────────┘ │
│                                                              │
│  Agent Docker: my-maven-git:latest                          │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                      ARTEFACTS                               │
│  • Rapport JUnit (surefire-reports/*.xml)                   │
│  • JAR exécutable (target/*.jar)                            │
└─────────────────────────────────────────────────────────────┘
```

---

## 4) Description des Stages

### Stage 1 : Checkout
```groovy
checkout scm
```
Récupération automatique du code source depuis le repository GitHub configuré dans Jenkins.

### Stage 2 : Build
```groovy
sh 'mvn -B clean compile'
```
- Nettoyage des artefacts précédents
- Compilation du code source Java

### Stage 3 : Test
```groovy
sh 'mvn -B test'
```
- Exécution des tests unitaires JUnit
- Génération des rapports Surefire

### Stage 4 : Package
```groovy
sh 'mvn -B package -DskipTests'
```
- Création du fichier JAR exécutable
- Skip des tests (déjà exécutés)

### Post Actions
```groovy
junit '**/target/surefire-reports/*.xml'
archiveArtifacts artifacts: '**/target/*.jar'
```
- Publication des résultats de tests
- Archivage du JAR pour téléchargement

---

## 5) Configuration Docker

### Dockerfile
```dockerfile
FROM maven:3.9.6-eclipse-temurin-17
RUN apt-get update && apt-get install -y git && rm -rf /var/lib/apt/lists/*
```

Cette image contient :
- Maven 3.9.6 pour le build
- Java 17 (Eclipse Temurin) pour la compilation
- Git pour le checkout SCM

---

## 6) Résultats

**Pipeline exécutée avec succès**

Les captures d'écran sont disponibles dans le dossier `screenshots/` et dans le `README.md` :
- Pipeline verte (tous les stages OK)
- Console output détaillé
- Résultats des tests (7 tests passés)

---

## 7) Problèmes rencontrés et solutions

### Problème 1 : Permission denied sur docker.sock

**Erreur** :
```
Got permission denied while trying to connect to the Docker daemon socket
```

**Solution** :
```bash
docker exec -u root -it jenkins bash
chmod 666 /var/run/docker.sock
usermod -aG docker jenkins
exit
docker restart jenkins
```

### Problème 2 : Image Docker introuvable

**Erreur** :
```
Unable to find image 'my-maven-git:latest' locally
```

**Solution** :
Build l'image localement avant de lancer la pipeline :
```bash
docker build -t my-maven-git:latest .
```

---

## 8) Conclusion

Ce TP m'a permis de :
- Comprendre le fonctionnement des **Jenkins Pipelines**
- Maîtriser l'utilisation d'**agents Docker** dans Jenkins
- Automatiser le cycle de vie d'un projet Maven (build, test, package)
- Résoudre des problèmes courants liés à Docker et Jenkins

La pipeline mise en place est **reproductible**, **portable** et constitue une base solide pour une stratégie **CI/CD** complète.

---

## 9) Références

- [Jenkins Pipeline Documentation](https://www.jenkins.io/doc/book/pipeline/)
- [Docker Pipeline Plugin](https://plugins.jenkins.io/docker-workflow/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
