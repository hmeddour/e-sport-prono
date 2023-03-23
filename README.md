# E-Sport Prono : Template de Projet

Bonjour !  
Vous trouverez ici un template de projet pour réaliser [les katas et la formation d'architecture hexa](https://gitlab.com/exalt-it-dojo/labo/architecture-hexa)  

Cette version du template comprend un projet par feature, et est orientée pour réaliser le kata sous architecture microservice

## Structure du template

Chaque feature est située dans un projet feature[1-5]  
Au sein de chaque feature, on trouvera la structure suivante :

    feature/
    |__application/
    |   |__api/
    |   |__api-contract/
    |   |__broker/
    |   |__broker-contract/
    |__domain/
    |   |__domain-core/
    |   |__domain-contract/
    |__infrastructure/

Chaque répertoire contient un projet Java indépendant, avec les répertoires de base (src/main/java, src/test/java, src/main/resources, src/test/resources)

Un module est constitué d'un contract (ex: api-contract) et d'un module (ex: api).

On mettra dans le contrat les éléments permettant au consommateur d'un module de l'utiliser (ex: Spec OpenAPI dans le module api-contract).

Ce projet est un build multi-module viable.  

Les configurations communes à tous les modules sont définis dans un script pré-compilé dans le répertoire buildSrc.

## Guide d'utilisation

Déplacer le contenu de la branche dans un nouveau répertoire

```bash
    $ mkdir ~/path/to/my/project/directory/
    $ cp -r ./ ~/path/to/my/project/directory/ && \
        cp .gitignore ~/path/to/my/project/directory/ && \
        cp .gitattributes ~/path/to/my/project/directory/
```

Se déplacer dans le répertoire précédent et supprimer le répertoire .git
```bash    
    $ cd ~/path/to/my/project/directory
    $ rm -rf .git/
```

Initialiser le repo git (et réassigner le remote origin si vous en disposer déjà d'un)

```bash
    $ git init
    $ git branch -m main
    $ git remote add origin <url/de/mon/repo>
    $ git commit -a -m "feat(e-sport prono): project initialization"
    $ git push --set-upstream origin main
```
