# Rattlesnake adventure
There is a rattlesnake in a desert which is initially two units long. You have to collect with the snake the foods on the level. You can control the movement of the snake’s head with keyboard buttons WASD or arrows. When the snake eats food, its length grows by one unit.
There are rocks in the desert. If the snake collides with a rock, boundary of the game level or into itself, the game ends.
In case of game end a popup message shows where the player can type his name. The result will be saved together with the amount of food eaten to the database. 
There is also a menu item, which displays a highscore table of the players for the 10 best scores.
### Game was built using Java. It has Java Swing based GUI.
### "DatabaseManager.java" connects to MySQL database for storing scores.
To connect to the database you have to enter your password in "config.properties" file. However, you can still play the game without connecting to the database if you want to.
### The documentation is provided in the "rattlesnake_adv_documentation.pdf" file.