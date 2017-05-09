# ChatSystem
This set of classes represent a library to have a [P2P](https://fr.wikipedia.org/wiki/Pair_%C3%A0_pair) 
chat system that will let you communicate with your friends by sending them messages or even files. 
This module is fully implemented in [Java 7](https://docs.oracle.com/javase/7/docs/api/).
## Requirements
- `Java Version` Java SE 7
- How to use it
  * Clone the project from `GitHub`
  * Add as external Jar the three .jar that you will find on the project (`ChatSystemUtil` `JUnit4.11` and `hamcrest1.3`).
  * Compile it using an IDE (Eclipse, Netbeans...) or the Shell
  * Run the Controller.java, there is a main at the bottom
- `Functionalities`
  * `Sign in` mode to choose your username and a status
  * Real time user connected list : Users send a periodic (2s) message on a multicast channel to advertise other users that they are
  connected. When they disconnect, they also send a message notifiying it.
  * Change your status so other users can see it
  * Have multiple chat windows open to chat with multiple persons at the same time
  * Send any type of file to other users
  * Receive notifications including the numbr of messages not readed for each user
  * Fancy GUI to chat with your friends!
## Tests

The following tests have been runned with `Junit`. You can find them on the package [Tests](tests/)

- `ContainsKnownUser` test on the ConnectedUsers Class 
  * `Description` : Check if a previous added user was on the connected users list
  * `Result` : Succes
  
- `ContainsUnknownUser` test on the ConnectedUsers Class 
  * `Description` : Changing parameters of the User previously added
  * `Result` : Succes if we change the IP address or the Pseudo but not if we just change the port or the state. The `equals`
  function takes in consideration just the IP address and the Pseudo.
  
- `SendSingleUser` test on the AlertOtherUsers Class
  * `Description` : We instantiate the Alerter class and the Checker class and send an `User` message.
  * `Result` : Succes
  
- `SendMultipleUser` test on the AlertOtherUsers Class
  * `Description` : We instantiate the Alerter class and the Checker class and we send multiple `User` messages on a different `Thread`.
  * `Result` : Succes

- `SendMultipleMessages` test on the Communication Class
  * `Description` : We instantiate the TCPServer and Contact Socket in order to simulate the sending of 200 `Messages` and try to retrieve them on the other side.
  * `Result` : Succes

- `ListeConversation` test on the ListeConversation Class
  * `Description` : We instantiate a conversations List and perform several method call on it.
  * `Result` : Succes

## Tests inter-projets

Le chat à été testé et marche avec toutes les fonctionnalités disponibles avec le projet de [VIOZELANGE](https://github.com/tintinbanban/ChatSystem_POO)
  
## Authors

- Luis Rodriguez Vallejo : @LuisRodriguez95
- Thibaut Sarion : @Chivunito 
