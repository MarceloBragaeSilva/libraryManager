# Library Manager

BRAGA E SILVA, Marcelo

Le 01/04/2022

## Introduction
Ce projet fait partie du cours [IN205 : Applications Portables](https://synapses.ensta-paris.fr/catalogue/2020-2021/ue/3741/IN205-applications-portables?from=P1680)
de l'école ENSTA Paris. Il s'agit du développement d'une application web de gestion d’une bibliothèque : gestion des membres, des livres référencés et des emprunts. L’application a été réalisée à l’aide de Java EE.

## Exécution du code
Le projet a été développé avec Maven. Pour l'installer, utilisez la commande suivante:
- sudo apt-get install maven

Pour charger la base de données du système, entrez dans le dossier 'Projet2' et exécutez la commande suivante:
- mvn clean install exec:java

OBS.: Pour changer de code main (pour lancer les codes de test de chaque étape si vous voulez), il faut commenter/décommenter les lignes à partir de 100 dans le fichier "pom.xml".

Pour lancer l'application, dans le dossier "Projet2", utilisez la commande suivante:
- mvn tomcat7:run

Après, il faut se connecter au site Web avec le lien: [http://localhost:8080/TP3Ensta/](http://localhost:8080/TP3Ensta/)

## Division du développement
L'application a été faite dans l'ordre de chaque couche du développement et chaque une a été faite dans un exercice différent.
À la fin de chaque exercice un commit a été fait dans ce repositoire, alors, si vous voulez regarder le développement au fur et à mésure, vérifiez l'histoire de commits.
- Exercice 1: Configuration initiale
- Exercice 2: La représentation des données (Création du modèle, c'est-à-dire, des classes Emprunt, Livre, Modèle)
- Exercice 3: L'accès aux données (Implémentation des DAOs)
- Exercice 4: Manipulation des données par les services (Implémentation des Services)
- Exercice 5: Interface utilisateur (Implémentation des Servlets et des Java Server Pages)


