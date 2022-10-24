<h1 id="title" align="center">B-Game</h1>

<p id="description">B-Game is an application that allows you to search through almost 150 000 board games add them to your lists (owned, favourite, disliked, wanted to buy/play), comment and rate them. It allows you to save session's spent on games with your friends - add them to your's (and only yours) space where you can write current status of game. You can come back anywhere and anytime.</p>



<h2>🧐 Features</h2>

Here's some of the project's best features:
*   uses external API to get new board games
*   allows to get board game by name, publisher, price, age, id
*   save/update/delete new game to database
*   login and sign up user (user or admin role)
*   create a list of favourite, disliked, owned, wanted to buy/play games (you can add or delete games)
*   save progress of games on board where it's possible to add friends and come back to game later

<h2>🛠️ Installation Steps:</h2>

<p>1. Make sure you have started docker then in root folder run command:</p>

```
docker-compose up
```

<p>2. Then just run command:</p>

```
mvn clean install && java -jar target/B-game-0.0.1-SNAPSHOT.jar
```


<h2>💻 Built with</h2>

Technologies used in the project:

*   Java
*   Spring Boot
*   Spring Security
*   JWT
*   Maven
*   Rest API
*   Lombok
*   JPA
*   JUnit
*   Mockito
*   MySQL
*   Docker

<h2>🔭 What's next? </h2>

*   allow users to add themselves as friends
*   add the ability to chat
*   finding people by their favorite game types
*   add places to play board games in a given city
*   add a general board where you can post
*   and more!

<h2>🍀 Endpoints </h2>
<p> You can find postman file in repository - B-games.postman_collection.json </p>
