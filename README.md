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
  * Run the Controller.java
- `Functionalities`
  * `Sign in` and `Sign u` modes to choose you your username and a password to protect it
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
