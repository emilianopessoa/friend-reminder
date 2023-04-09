Friend Reminder
============= 

Friend Reminder is application that sends birthdays reminders via email and SMS to a list of friends.

Requirements
---------------

You need to have the following components installed in your environment:

    Java 17
    Maven

Running the Application
---------------

To build and run the application, follow these steps:

    1 - Open the friend-reminder folder.
    2 - Run: mvn clean package.
    3 - Open the target folder.
    4 - Run: java -jar friend-reminder-1.0.0.jar.

The initial application has already been built. If you do not want to compile it, simply proceed from step 3.    

Note: The Email and SMS messages will be displayed in the application console.

Databases
---------------

The project uses SQLite and flat file (.csv) as data repositories.

As this is a test project, the flat file already includes 3 friends by default, and when the application is executed, 3 more friends are added to the SQLite database, one of which, Pedro Souza, has his birthday today.

The files can be found in the "db" folder.


Framework
---------------


The project was built following the best practices of hexagonal architecture and TDD, with the domain layer completely decoupled from the other layers. This helps to ensure a greater separation of concerns and makes it easier to maintain and test the code.
