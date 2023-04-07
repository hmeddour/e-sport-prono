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

# ☕ Doctrine Java


🔴 : parcours type premier entretien

# 🎓 Junior

> L'idée ici, c'est de confirmer la connaissance des notions haut niveau du Java et du développement backend. 

## Programmation Orientée Objet

On considère la classe Animal ci-dessous:

```java
public abstract class Animal {

  protected int age;
  protected String color;

  public Animal(int age, String color) {
    this.age = age;
    this.color = color;
  }

  public Animal(Animal father, Animal mother) {
    this.age = 0;
    this.color = Math.random() >= 0.5 ? father.color : mother.color;
  }

  abstract void shout();

}
```


<details>
<summary>Qu'est ce que l'héritage en programmation objet ? Comment l'appliquer à la classe Animal ?</summary>
<br>

L'héritage  permet de créer des hiérarchies de classes, en faisant des classes dérivées à partir de classes parents. Les classes dérivées **héritent** de tous les attributs et comportement des classes parentes, et peuvent modifier leur comportement, les spécialiser, les enrichir, ...

> Par exemple, si ton programme gère des Chats et des Chiens, comment tu mets en place de l'héritage pour factoriser ton code ?


```java
public class Cat extends Animal {

  public Cat(int age, String color) {
    super(age, color);
  }

  void shout() {
    System.out.println("meow");  
  }
}
```

```java
public class Dog extends Animal {

  public Dog(int age, String color) {
    super(age, color);
  }

  void shout() {
    System.out.println("wouf");  
  }

}
```

</details>


<details>
<summary>🔴 Qu’est-ce que la surcharge (overload) et la redéfinition (override) ? </summary> 
<br>

- **Override :** redéfinition d'une méthode héritée dans une sous-classe pour en modifier le comportement. C'est l'implémentation du concept orienté-objet du **polymorphisme**

> La classe Animal ci-dessus définit une méthode `shout()`, dont le comportement diffère selon que l'animal soit un Chien ou un Chat (le premier aboie, le second miaule).


- **Overload (surcharge) :** plusieurs méthodes avec même nom et valeur de retour, mais des des arguments différents.

> La classe Animal a deux constructeurs: le premier pour créer un animal à partir d'attributs. Le deuxième pour créer un animal à partir de deux animaux parents.
> Le premier constructeur permet d'instancier un animal _ex-nihilo_, le second permet de simuler la naissance d'un nouvel individu avec 2 parents.


</details>

<details>
<summary> Classe et Objet: quelle différence ?</summary>
<br>

**Une classe est un modèle ou un plan** qui décrit les propriétés et le comportement d'un ensemble d'objets qui partagent les mêmes caractéristiques. Une classe contient des attributs (variables) et des méthodes (fonctions) qui décrivent comment les objets de cette classe doivent être créés et comment ils interagissent avec le reste du programme.

Un objet est **une instance unique d'une classe**. Lorsqu'un objet est créé, il obtient ses propres valeurs uniques pour les attributs définis dans la classe, et il peut appeler les méthodes de la classe pour effectuer des actions spécifiques.

> La _classe_ `Chien` permet de définir une représentation en java du meilleur ami de l'homme. A partir de cette _classe_, on peut instancier des _objets_ chien ayant tous **une identité propre** (mon chien, celui du voisin, etc.)

</details>

<details>
<summary>Comment est créé un Objet en Java ?</summary>
<br>

Avec le mot clé `new`, la JVM alloue un espace mémoire pour sotcker cette objets dans le tas (heap) qui est initialisé à sa valeur par défaut (0, false, null etc...) et la JVM lui attribue une adresse mémoire pour y accéder
</details>



<details>
<summary>Qu'est ce qu'une classe abstraite ?</summary>
<br>

C'est une classe qui ne peut pas être instanciée, qui contient au moins une méthode abstraite.
Elle est conçue pour être dérivée par des classes concrètes qui implémentent ses méthodes.

> La classe Animal est abstraite, car sa méthode `shout()` est abstraite : elle doît être définie dans une classe dérivée de Animal (Chat, Chien, ...).

</details>

<details>
<summary>Quels sont les différents niveaux d'accès en Java ?</summary>
<br>

il existe trois niveaux d'accès différents qui permettent de contrôler la visibilité des classes, des méthodes et des variables qui sont les suivants :

- **public :** accessible depuis l'extérieur de l'objet.
- **private :** accessible uniquement à l'intérieur de la classe.
- **protected :** accessible depuis le package et les sous-classe qui héritent la classe
- **default :** accessible que depuis le package dans lequel ils sont définis mais pas depuis les packages externes

| Access from                    | Public   | Private  | Protected  |  Default  |
| ------------------------------ | -------- | -------- | ---------- | --------- |
| Same Class                     | Yes      | Yes      | Yes        |  Yes      |
| Same package subclass          | Yes      | No       | Yes        |  Yes      |
| Same packaga non-subclass      | Yes      | No       | Yes        |  Yes      |
| Different package subclass     | No       | No       | Yes        |  Yes      |
| Different package non-subclass | No       | No       | No         |  Yes      |


</details>

<details>
<summary> 🔴 Comment peut-on expliquer la différence entre (== et equals) ?</summary>
<br>

**==** c'est un opérateur qui compare les adresses mémoires de 2 objets, il est souvent utilisé pour comparer les types primitifs (int, float, double, char etc..), et **equals()** est une méthode de la classe Object qui compare les valeurs de deux 2 objets plutôt que leurs références.

Ce shéma ci-dessous résume la différence entre (== et equals) : 

![image info](./assets/defference-entre-equals-et-==.png)

Exemple :
```java
public class Exemple{ 
    public static void main(String[] args) 
    { 
        String s1 = new String("ExaltIT"); 
        String s2 = new String("ExaltIT"); 
        // output : false
        System.out.println(s1 == s2); 

        // output : true
        System.out.println(s1.equals(s2)); 
    } 
}
```
</details>

<details>
<summary>À quoi sert le mot clé static ? </summary>
<br>

```java
public abstract class Animal {

  protected int age;
  protected String color;

  public Animal(int age, String color) {
    this.age = age;
    this.color = color;
  }

  public Animal(Animal father, Animal mother) {
    this.age = 0;
    this.color = Math.random() >= 0.5 ? father.color : mother.color;
  }

  public abstract int getPopulation();

  public abstract void shout();

}

public class Cat extends Animal {

  public static int population = 0;

  public Cat(int age, String color) {
    super(age, color);
    this.population += 1;
  }

  public static int getPopulation() {
    return population;
  }

  public void shout() {
    System.out.println("meow");
  }
}
```

Marquer des attributs ou méthodes comme _"statique"_, ou _"de classe"_. Ces attributs/méthodes existent donc au niveau de la classe, et non de ses objets.


**Attribut statique (ou attribut de classe)** : existe indépendement des objets instanciés et permet de contenir des informations relatives aux instances de cette classe.


**Méthode statique (ou méthode de classe)**: C'est une méthode indépendante des objets de la classe dans laquelle elle est définie. Son accès ne nécessite donc pas une instanciation de l'objet mais dépend quand même de son niveau de visibilité.

> Dans cet exemple, on utilise un attribut statique & une méthode statique pour garder trace de la population de chaque espèce animale dans notre environnement, la population étant typiquement un attribut relié à la classe, et non à un individu en particulier.


</details>
<br>


## Structures de données

<details>
<summary> 🔴 Je veux écrire un algorithme qui me renvoie les noms des 10 capitales les plus peuplées du monde. De quelle(s) structure(s) de données j'ai besoin ?</summary>
<br>

Une `list` de villes. Les villes ont un nom et une population.

On choisit une `list` car ses éléments peuvent être triés, et accedés par indice en temps constant.

Dans le cas présent, on trie les villes par population en ordre croissant, et on prend les 10 dernières villes de la liste.

> En Java, 2 exemples de classes implémentant l'interface `list` sont les `ArrayList` et `LinkedList`

![liste-example](assets/liste.png)


</details>

<details>
<summary>🔴 Je veux écrire un algorithme qui prend une string en input, et me renvoie l'ensemble des caractères présents dans la string. De quelle(s) structure(s) de données j'ai besoin ?</summary>
<br>

Un `set` de caractère est le plus adapté.

On choisit un `set` car les éléments n'ont pas de notion d'ordre, ni de quantité, et on ne veut pas de doublons. C'est la structure la plus adaptée.

> En Java, 2 exemples de classes implémentant l'interface `set` sont les `TreeSet` et `HashSet`

![set](assets/set.png)

</details>


<details>
<summary> J'ai un ensemble d'employés ayant un identifiant unique, un nom, un age et un poste. Je veux écrire un algorithme qui me permet d'afficher le nom d'un employé à partir de son identifiant. De quelle(s) structure(s) de données j'ai besoin ? </summary>
<br>

Une `map` d'employés, utilisant l'identifiant de chaque employé comme clé. L'identifiant peut être utilisé comme clé car **unique**. La `map` permet **d'accéder à un objet en temps constant** à partir de la clé de cet objet, un vrai avantage par rapport à une `list` qui nous demande de faire un parcours.
 
> En Java, 2 exemples de classes implémentant l'interface `map` sont les `HashMap` et `TreeMap`

</details>




<details>
<summary>🔴 Qu'est-que un objet immutable ? </summary>
<br>

En Java, un objet immutable est un objet dont l'état ne peut pas être modifié après sa création. Cela signifie que les valeurs de ses propriétés ne peuvent pas être modifiées après leur initialisation. Au lieu de cela, une nouvelle instance de l'objet est créée avec la nouvelle valeur.

La classe `String` est un exemple de classe immutable en Java. Lorsqu'un objet String est créé, sa valeur ne peut pas être modifiée. Si une méthode tente de modifier la valeur d'une String, une nouvelle instance de String sera créée.

exemple:

```java
String s1 = "Hello";
String s2 = s1.concat(" World");

System.out.println(s1); // Hello
System.out.println(s2); // Hello World
```

Dans cet exemple, nous avons créé un objet String immutable avec la valeur "Hello". Ensuite, nous avons appelé la méthode `concat()` sur l'objet `s1` pour lui ajouter la valeur " World". Cependant, au lieu de modifier l'objet `s1`, une nouvelle instance de String avec la valeur "Hello World" a été créée et affectée à la variable `s2`. L'objet `s1` est resté inchangé et conserve sa valeur initiale "Hello".


</details>

<br>


## Développement Backend

<details>
<summary> Qu'est-ce que une API REST ?</summary>
<br>
REST (REpresentational State Transfer) est une architecture basée sur les resources et les actions pour la communication entre le client et le serveur en stateless (on garde pas l'état de la resource entre les requête), elle est basé sur le protocole HTTP en utilisant ses verbes (GET, POST, PUT, PATCH et DELETE) pour manipuler les resources, elle utlise généralement le format JSON pour la réception et l'envoi des données car c'est un format qui est léger et lisible par tous les langages de programmation, dans les reponse on peut trouver des codes sigificatifs par exemple : 
- 200, 201 etc... pour indiquer que les requête est bien passée
- 400, 401 etc... généralement pour indiquer qu'il y a des erreurs côté client
- 500, 502 etc... des erreurs côté serveur


> **🔴 Notions à valider:**
> * Protocole HTTP : verbes, statut de retours
> * stateless
> * formats de données: json, xml, ...

<br>
</details>


<details>
<summary>Comment gérer la persistance d'une application backend ?</summary>
<br>

Avec une **base de données** _(et/ou un filesystem)_.

<details>
<summary>Différence entre SQL et NoSQL ?</summary>
<br>

- **SQL (Structured Query Language)** est un langage de requête utilisé pour accéder et manipuler des bases de données **relationnelles**. Les données sont stockées dans des tables qui ont des relations définies entre elles. SQL est souvent utilisé pour les applications qui nécessitent des transactions complexes, des contraintes de données et une forte cohérence

> exemples: PostgreSQL, MySQL, SQLite

- **NoSQL (Not Only SQL)** est un terme générique qui désigne des bases de données qui ne suivent pas le modèle relationnel de SQL. Les données sont stockées sous forme de **documents**, de **clés-valeurs**, de **graphes** ou de **colonnes**. NoSQL est souvent utilisé pour les applications qui nécessitent une grande évolutivité, une grande disponibilité et une faible cohérence. NoSQL est également adapté aux données semi-structurées ou non structurées, telles que les données JSON ou XML

> exemples: MongoDB, Cassandra

<br>
</details>

<details>
<summary>Qu'est ce qu'un ORM ?</summary>
<br>

**ORM (Object-Relational Mapping)**, il s'agit d'une technique de programmation qui permet de faire correspondre des objets issus d'un langage de programmation orienté objet (comme Java, Python) à des tables dans une base de données relationnelle. L'ORM permet ainsi de manipuler les données de la base de données à travers des objets dans le langage de programmation, sans avoir à écrire des requêtes SQL.
L'ORM les plus courant utilisé pour Java est **Hibernate**
</details>

<br>
</details>
<br>


<br>


# 🧪 Confirmé

> Est-ce que le jour où tu arrives sur une solution en 50 modules avec du legacy, des modules développés par d'autres équipes, de l'injection de dépendance, tu es capable de t'y retrouver et d'appliquer les bonnes pratiques ?

## Culture générale

<details>
<summary>Quels sont les avantages du Java par rapport à d'autres langages ?</summary>

Java est un langage de programmation orienté objet, il a été fréquemment choisi par les développeurs depuis deux décennies pour plusieurs raisons :

- **Portabilité :** est l'une des principales forces de Java, cela signifie que les programmes écrit en Java s'éxécute de la même manière quelques soit le système d'exploitation (Windows, Linux, macOS, Android etc...) qui dispose d'une **JVM (Java Virtuel Machine)**
- **Sécurité :** Java est conçu avec des fonctionnalités de sécurité telles que la gestion de la mémoire, la vérification des types et l'exécution en sandbox, ce qui permet de prévenir les vulnérabilités et les attaques de sécurité.
- **Performance :** Java est un langage compilé, ce qui signifie qu'il peut fournir des performances élevées par rapport à d'autres langages de programmation interprétés.
- **Gestion de mémoire :** Java gère automatiquement la mémoire allouée aux programmes, ce qui facilite la programmation et évite les fuites de mémoire.
</details>

</details>

<details>
<summary>Quelles sont les innovations des principales versions Java ?</summary>
<br>

Ce sont des versions majeures de la plateforme Java avec des fonctionnalités et améliorations significatives. Voici un aperçu des nouveautés de chaque version :

| Java 8   | Java 11  | Java 17  |
| -------- | -------- | -------- |
| **API Stream :** Séquence d’éléments sur lesquels on peut effectuer un groupe d’opérations de manière séquentielle ou parallèle sur un ensemble d'éléments| **La classe HttpClient :** elle fournit une API moderne pour envoyer et recevoir des requêtes HTTP | **classe scellées (sealled class) :** limiter l’héritage d’une classe mère et lister les sous-classe autorisées à hérité de cette classe en utilisant le mot clé **sealed** |
| **Interface fonctionnelle :** une interface qui contient qu’une seule méthode abstraite exemple (Runnable, ActionListener, Comparable)  | **classe String :** mélioration dans la classe String (stripe, isBlank, streaming Lines, repeat)| amélioration dans la récupération des ressources pour éviter les fuites de mémoire |
| **Lambda Expression :** exprime une instances d'interfaces fonctionnelles elle implémente la seule méthode abstraite de l'interface fonctionnelle | **système de fichiers HTTP :** permet de lire des fichiers directement à partir d'un serveur HTTP sans avoir besoin de bibliothèques externes | Suppression de l'API Appelet |
| **Optional :** son objectif est d'éviter les **NPE** (NullPointerException), il vérifie la présence d'une valeur pour une variable particulière d'un objet| | |


<details><summary>Citez moi quelques fonctions de l'API Stream</summary>
<br>

- **filter :** opération intermédiaire qui renvoi un nouveau flux qui contient les élément qui correspondent au prédicat donné dans le filter (exemple filtrer les listes)
<details><summary>Quelle est la différence entre map et flatMap ?</summary>
<br>

Se sont deux méthodes de l'interface Stream qui sont utilisé pour des opérations de transformation et de mappage d'objets

- **map :** produit une sortie pour une entrée exemple (multiplier tous les éléments de la liste par 2 et renvoyer la liste mise à jour)
- **flatMap :**  renvoie un nombre arbitraire de valeurs en sortie pour chaque valeur d'entréé exemple (obtenir le 1er caractère de toutes les strings présentes dans une liste de strings et renvoyer le résultat sous la forme d’un flux)

Exemple :
```java
public class Main {
    // map
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    
    List<Integer> doubledNumbers = numbers.stream.map(n => n * 2).collect(Collectors.asList());
    // output : Array(2, 4, 6, 8, 10)
    
    // flatMap
    List<String> strings = Arrays.asList("Hello world", "Goodbye world");
    List<String> words = strings.stream()
                            .flatMap(s -> Arrays.stream(s.split(" ")))
                            .collect(Collectors.toList());
    // output : words: ["Hello", "world", "Goodbye", "world"]

}
```
</details>
Ici un exemple d'exercice technique sur l'utilisation de l'API Stream,[voir ici](./example/Interview.java)
</details>
<br/>

</details>



<details>
<summary>C'est quoi la différence entre Maven et Gradle ?</summary>

se sont deux gestionnaires de dépendances pour des projets Java, (avant il faut toujours télécharger des jar et les intégrer au projet java), le tableau ci-dessous résume leurs différences :

| Différence   | Maven   | Gradle |
| -------- | -------- | -------- |
| **Config :** | XML (pom.xml) | Groovy ou Kotlin |
| **Flexibilité et performances :** | suit une approche plus linéaire qui peut conduire à des temps de contrustion plus longs | Gradle est plus flexible et plus performant que Maven car il se charge des tâches et des projets plus complexe |
| **Langages :** | Java | Java, Kotlin, Groovy, Scala, C++ etc... |
</details>

</details>
</details>








<details>
<summary>Quel est l'intérêt de Spring Boot ?</summary>
<br>

C'est un framework de développement des applications Java, il offre de nombreux avantages notamment :
- Simplifie le développement d’application web java en fournissant des fonctionnalités telles que **l'injection de dépendances**, la sécurité et la gestion des erreurs
- **Autoconfiguration :** c'est l'un des principe de Spring Boot, il fournit une configuration rapide ce qui permet au développeur de se concentrer sur code métier
- **Déploiement facile :** il prend en charge différents types de déploiment tels que le déploiment sur un conteneur docker, sur un serveur d'application ou un serveur cloud
- **Écosystème :** Spring Boot dispose d'un écosystème riche et dynamique qui offre de nombreux outils et bibliothèques tiers pour faciliter le développement d'applications Java

<details>
<summary>Qu'est-ce que l'injection de dépendance ?</summary>
<br>

C'est un concept clé de Spring Boot qui facilite la gestion des dépendances entre les composants d'une application, ça consiste à fournir automatiquement les dépendances nécessaire pour la configuration et le démarrage rapide d'un projet java. Spring Boot dispose des annocations tels que `@Component`, `@Service` qui indiquent que les classes annotées sont gérées par Spring et quelle sont automatiquement injectées au moment de l'exécution
</details>

<details>
<summary>Qu'apporte les Starter de Spring boot ?</summary>
<br>

Les Starters de Spring Boot permettent de configurer et simplifier la mise en place d'un projet Spring en fournissant les dépendances de base pour fonctionner.

<details>
<summary>Citez moi un exemple de Starter ?</summary>
<br>

Le starter **JPA (Java Persistence API)** fournit des dépendances pour la mise en œuvre de la persistence des données. Il comprend des bibliothèques telles que **Hibernate** qui est un ORM (Object Relation Mapping)qui simplifie la transformation des tabales d'une base de données en objet Java
</details>
</details>
</details>

<br>

## Good Practices


<details>
<summary>Connais-tu les principes SOLID ?</summary>
<br>

Les principes SOLID sont un ensemble de cinq principes de conception de logiciels qui visent à améliorer la qualité et la maintenabilité du code:

- **Single Responsability :** 1 classe = 1 responsabilité = 1 use case.
> Une classe ne devrait avoir qu’une seule raison de changer.
> Si ma classe fait plusieurs choses, c'est que sa responsabilité est trop importante et la classe deviendra inévitablement trop complexe.
On comprend toujours avec un exemple 

```java
public class Customer
{
    public int id;
    public string name;
    public bool active;
  
    public void activateCustomer() {
        this.active = true;
    }

    public void inactivateCustomer() {
        this.active = false;
    }

    public void addCustomer() {
        // Some implementation here (database) ...
    }

    public void deleteCustomer() {
        // Some implementation here (database) ...
    }
}
```
Dans cet exemple, la classe `User` ne respecte le principe **Single Responsability** car elle gère deux responsabilités, les **règles de gestion** et la **persistance de la base de données**.

Un bon exemple :
```java 
public class Customer
{
    public int id;
    public string name;
    public bool active;
  
    public void activateCustomer() {
        this.active = true;
    }

    public void inactivateCustomer() {
        this.active = false;
    }
}
```

```java
public class CustomerRepository
{
    public void addCustomer(Customer customer)
    {
        // Some implementation here (database) ...
    }

    public void deleteCustomer(Customer customer)
    {
        // Some implementation here (database) ...
    }
}
```

Ici le principe est bien appliqué car les responsabilités ont été réparties et chaque classe n’a qu’une seule raison de changer.

- **Open / Close :** ouvert à l'extension, fermé à la modification. 
> Concrètement, un code écrit n'est pas censé être modifié, mais étendu / spécialisé par la création de classes dérivées ou d'interfaces. C'est un concept assez extrême, rarement appliqué à la lettre mais important à garder en tête.

Mauvais exemple :

```java 
public enum PaymentMethod {
    VisaCard,
    MasterCard,
    Cash,
}
```

```java 
public class PaymentService
{
    public PaymentMethod paymentMethod;


    public void ProcessPayment()
    {

        if (PaymentMethod == PaymentMethod.VisaCard)
        {
            // Some implementation here
        }
        else if (PaymentMethod == PaymentMethod.MasterCard)
        {
            // Some implementation here
        }
        else if (PaymentMethod == PaymentMethod.Cash)
        {
            // Some implementation here
        }

        // Some implementation here
    }
}
```

Le code ici est ouvert à la modification, si un nouveau mode de paiement est ajouté, la classe doit être modifiée, ça respecte donc pas le principe **d'Open/Close**.

Un bon exemple :
```java
public interface IPaymentMethod
{
    void processPayment();
}
```


```java
public class VisaCardPayment implements IPaymentMethod
{
    public void processPayment() {
        // Some implementation here (VisaCardPayment)
    }
}
```


```java
public class MasterCardPayment implements IPaymentMethod
{
    public void processPayment() {
        // Some implementation here (MasterCardPayment)
    }
}
```


```java
public class CashPayment implements IPaymentMethod
{
    public void processPayment() {
        // Some implementation here (CashPayment)
    }
}
```


```java
public class PaymentService
{
    private IPaymentMethod paymentMethod;

    public PaymentService(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void ProcessPayment() {
        // Some implementation here
        PaymentMethod.ProcessPayment();
        // Some implementation here
    }
}
```

Le principe est bien respécté dans cet exemple car si un nouveau mode de paiement est ajouté, la classe ne sera pas modifiée.

- **Liskov Substitution :** principe de substitution de Liskov, si le système fonctionne et qu'on ajoute une sous-classe qui hérite d'une autre classe, le système doit continuer à fonctionner 
- **Interface Segregation :** les interfaces doivent être spécifiques aux besoins des clients qui les utilisent, plutôt que de fournir un ensemble de fonctionnalités génériques
> analogue pour les interfaces du principe de single-responsability.
- **Dependency Inversion :** pricnipe d'inversion de dépendances, les modules de haut niveau ne devraient pas avoir à être modifiées lorsqu'un module de bas niveau est modifié, les abstractions doivent dépendre des détails pas l'inverse
> Concept essentiel à maitriser. Un dev java doit être à l'aise avec le concept d'injection de dépendance et comment l'implémenter en java.
</details>


<details>
<summary>A quoi servent les Exceptions ?</summary>
<br>

L'intérêt principal des exceptions est de remonter les erreurs et éviter l'interuption du programme lors de l'exécution.

<details>
<summary>De quelle classe les exceptions héritent-elles  ?</summary>

de la classe **Throwable**

</details>

<details>
<summary>Quels sont les deux principaux types d'exception ?</summary>
<br>

- **Les exceptions vérifiées (checked exceptions) :** Elles héritent de la classe Error et se sont des exceptions qui sont véréfiées au moment de la compilation et qui doient être gérées explicitement dans le code 
- **Les exceptions non vérifiées (unchecked exceptions) :** Elles héritent de RuntimeException, se sont des exceptions d'implémentation et elles n'ont pas besoin d'être déclarée dans la signature de la fonction
</details>
</details>

<details>
<summary>Connaissez-vous la différentre entre final, finally et finalize ?</summary>
<br>

- **final :** c'est un mot clé utilisé en java dans défférents endroits qui sont les suivants : 
    - **classe :** empêche la classe d'être héritée.
    - **method :** empêche la méthode d'être redéfinié dans les classes dérivées.
    - **attribut :** empêche l'attribut d'être modifié.

- **finally :** il est souvent utilisé dans les cas d'exception, le bloc finally sera toujours exécuté qu'une exception soit levée ou pas

- **finalize :** est une méthode de la class Object qui est appélée par le **Garbage Collector** juste avant de libérer l'espace mémoire occupé par l'objet. Par exemple, si un objet a ouvert une connexion à une base de données ou à un fichier, la méthode "finalize" peut être utilisée pour fermer la connexion et libérer les ressources associées
</details>


<br>

## Testing


<details>
<summary>Test: à quoi ça sert ? Comment tu les implémente en Java ?</summary>
<br>

Les testes unitaires ont une grande importance dans le développement des applications, leur objectif est de s'assurer que chaque bout de code fonctionne comme prévu et répond parfaitement aux spécifications et exigences définies par le métier, ils pertmettent également de détecter rapidement les erreurs et les défauts de code avant que l'application soit déployée en production ce qui va réduire les risques et les coûts de maintenance 

Framework de test unitaire: Junit.

Parmis les nouveautés du Junit 5 on peut trouver : 
- **BeforeEach**, **BeforeAll**, **AfterEach**, **AfterAll :** se sont des annotations pour définir l'ordre de l'exécution des testes unitaires
- Testes paramétrés à l'aide de différentes sources de données tels que les fichiers csv, ou des tableaux etc...
- Gérer les exceptions avec la méthode **assertThrow**

</details>


<details>
<summary>Que sont les méthodologies BDD, DDD, TDD ?</summary>
<br>

- **BDD (Behavior Driven Developpement - développement piloté par le comportement) :** est une méthode qui regroupe les équipes fonctionnelles, techniques, et autres participants dans le développement d'un projet informatique en utilisant un langage naturel commun (Gherkin) pour que les équipes métiers exprime clairement le besoin aux développeurs, en écrivant des scénarios qui décrivent le comportement attendu par le système pour que tous les participants aient le même niveau de compréhension des fonctionnalités de l'application
    - **définition du besoin :** les fonctionnels acceuillent le besoin auprès du client
    - **les 3 amigos :** une réunion entre les développeur, fonctionels, PO, testeurs etc..., le but est de discuter et de soulever toutes les questions sur le fonctionnement de l'application et décrire différents scénarios
    - **transformation des scénarios en test :** traduire les scénarios en test en suivant le cycle (Red, Green, Refactor) 
        - **Red :** écrire un test qui échoue
        - **Green :** écrire un test qui réussit
        - **Refactor :** Factoriser le maximum du code possible
    - **développement :** après ses différentes étapes, les développeurs sont censé comprendre le besoin du clients donc ils attaquent le développement des focntionnalités
-  **DDD (Domain Driven Design - Conception piloté par le domaine) :** est une méthode de développement logiciel qui se concentre sur la modélisation du domaine métier de l'application (nom de classe, champs, méthodes) qui doivent refléter le métier et qu'une personne métier soit capable de comprendre ce que va faire le code
- **TDD (Test Driven Developpement - développement piloté par les testes) :** est une méthode de développement logiciel qui se concentre sur l'écriture des tests unitaires avant l'implimentation du code. Le test échoue initialement, puis le développeur écrit le code minimal nécessaire pour faire passer le test, ensuite il fera un maximum de refactoring pour rendre le code plus lisible et maintenable. Le TDD permet de garantir que le code produit est testé et fiable et éviter les bugs dès le début de développement 
</details>


</details>

<br>

# 🧙‍♂️ Senior

> Est-ce que tu as une connaissance de Java suffisament aboutie pour prendre une position de mentor, et par ailleurs est-ce que tu es capable de monter sur des notions d'architecture pour assumer une posture de tech lead ?

## Architecture



<details>
<summary>Comment le code Java est-il exécuté  dans la JVM (Java Virtuel Machine) ?</summary>
<br>

Quelque soit le système d'exploitation (windows, Linux, macOS etc...) le code Java est exécuté de la même manière grâce à sa **JVM (Java Virtuel Machine)** en suivant un processus appelé "compilation Just-In-Time" (JIT). Ce processus se déroule en plusieurs étapes :
- **Compilation du code source :** Le code source Java est compilé en bytecode Java (.class) grâce au compilateur **Javac** (Java Compilateur)
- **Chargement du bytecode :** La JVM charge le **bytecode** dans sa mémoire
- **Vérification du bytecode :** La JVM vérifie que le bytecode est conforme aux règles de sécurité de Java
- **Interprétation :** Le bytecode est interprété par la JVM en langage machine
- **Compilation JIT :** Au fil de l'exécution, la JVM analyse le bytecode et identifie les parties de code qui sont exécutées fréquemment. Ces parties de code sont compilées en code machine natif pour améliorer les performances
- **Exécution :** Le code compilé est exécuté par la JVM
</details>

<details>
<summary>Quels sont les avantages et les inconvinients d'une architecture microservices ?</summary>
<br>

C'est une approche de conception logicielle où une application est divisée en plusieurs services indépendants qui communiquent via des API ou des message broker (par exemple Kafka ou RabbitMQ). Chaque service répond à un besoin métier spécifique et déployé indépendament des autres services, parmi les avantages de cette architecture on trouve : 
- **Évolutivité  :** il est possible d'ajouter ou de supprimer un service sans perturber le fontionnement de l'application
- **Fiabilité :** En cas de panne d'un service, les autres continueront de fonctionner, celà réduit le risque de panne de l'application dans son ensemble
- **Flexibilité :** Les microservices permettent de choisir la technologie la plus adaptée pour chaque service. Par exemple, un service peut être développé en Java, un autre en Python etc...
- **Déploiement continu :** chaque service est déployé indépendamment des autres, ce qui permet de mettre à jour ou de corriger un service sans avoir à redéployer toute l'application
- **Rapidité :** Les microservices facilitent le développement rapide et la livraison continue en permettant aux équipes de travailler de manière autonome sur chaque service.

Comme cette architecture a de nombreux avantages, elle a aussi ses incovinients, on trouve par exemple :
<br>

- **Problèmes de communication :** souvent les microservices communiquent entre eux via des API, celà peut causer des problèmes de disponibilité et de sécurité 
- **Tests plus complexes :**  Les tests des microservices sont plus complexes, car chaque service doit être testé individuellement, ainsi que les interactions entre les différents services
- **Coût de déploiement :** cette architecture peut être coûteuse en termes de déploiement car chaque service nécessite des ressources indépendantes
</details>

<details>
<summary>Connais tu l'architecture hexagonale ? Quels sont ses principes, et à quel besoin ça répond ?</summary>
<br>

Principes:
* séparer la logique métier des dépendances techniques (persistances de données, système de fichiers, API externes etc...)
* tester le code métier en isolation de ses éventuels systèmes d’exécution et bases de données.
* protéger mon code métier. Le jour où je dois le faire évoluer, je n'ai pas besoin de m'inquiéter des compatibilités avec mon framework d'API, mon ORM, ma base de donnée ...

En conséquent, le code est séparé en trois parties:
- **métier / domaine :** hexagone central qui représente le noyau de l'application, qui contient toute la logique métier et les règles de l'entreprise
- **ports primaires :** comment le métier est mis à disposition (ex: API, CLI, message broker, ...)

- **ports secondaires :** qu'est ce que l'application a besoin pour fonctionner ? (ex : base de données, fichiers système, API externes, ...)
</details>

<br>

## Multithreading

<details>
<summary>Comment sont-ils gérés les accès concurrents en multithreading ?</summary>
<br>

Dans un contexte **multithreading**, plusieurs threads peuvent s'exécuter simultanément et cela peut  causer des problèmes de concurrence lorsque plusieurs threads accèdent simultanément à des ressources partagées.

Pour résoudre ces problèmes de concurrence, Java fournit deux mécanismes principaux : les **verrous (locks)** et les **blocs synchronisés (synchronized blocks)**.

Les verrous, permettent de restreindre l'accès à une ressource partagée à un seul thread à la fois. Ils sont gérés par des objets de type `Lock` ou `ReentrantLock`, qui permettent de bloquer et de débloquer l'accès à une ressource partagée. Les verrous sont généralement utilisés lorsque l'on souhaite une plus grande flexibilité et un contrôle plus fin sur la gestion de la concurrence.
<br>

Les blocs synchronisés, sont un autre mécanisme fourni par Java pour résoudre les problèmes de concurrence. Ils permettent de restreindre l'accès à une ressource partagée à un seul thread à la fois en utilisant un **verrou implicite**. Les blocs synchronisés sont plus simples à utiliser que les verrous, mais offrent moins de contrôle et de flexibilité.

Voici un exemple simple pour mieux comprendre l'utilisation des blocs synchronisés :
```java
public class Example {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

Dans cet exemple, la classe `Example` a une propriété `count` qui est partagée entre plusieurs threads. Les méthodes `increment()` et `getCount()` sont toutes deux synchronisées en utilisant le mot-clé `synchronized`. Cela signifie que chaque méthode ne peut être exécutée que par un seul thread à la fois.
</details>


<details>
<summary>Que permet le mot clé volatile ?</summary>
<br>

Dans un contexte **multithreading**, on déclare une variable **volatile** si elle est susceptible d’être lue/modifiée (de manière asynchrone) simultanément par plusieurs threads. En effet, la JVM se charge de rafraîchir sa valeur à chaque fois qu’elle est utilisée pour permettre à tous les threads d’accéder à sa plus récente valeur

</details>
