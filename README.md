# SpeedRunMVI
This sample app shows an unpaginated list from https://www.speedrun.com/api/v1/games and information of the first run of that game when clicking it. The project showcases the use of the following technologies: 

- **Android modularisation**: The project consists of several modules:
  - Remote: Handles the creation of Retrofit and Moshi instances, as well as a helper function to map from Retrofit's Response to our domain classes
  - Commons: Several small classes shared between feature modules
  - Feature GamesList: Contains the activity to show a list of games, the use case and repository to get the data
  - Feature GameDetail: From GamesList we can navigate here. There's the detail of the first run of the game as well as repository code to get the name of a player if needed
  - App: It only contains classes used for navigation, since it knows all modules. It also manages dependency injection classes
- **Clean architecure pattern**: Inside each feature, I created a domain package with the use cases and the repository interface, a data package where we interact with the API to convert the data to our domain values, and a presentation package with the activity, the presenter and MVI classes that communicate with the domain layer via use cases to generate a view State.
- **Dagger2**: As a dependency injection framework of choice
- **RxJava2**: To handle all reactive programming in the app
- **Custom navigation**: An own way of navigating inside the app without using Navigation Component from Android
- **MVI**: In such a small example like this, the MVI pattern may be a bit big, but in bigger projects, its good scalability and testability make it a good choice
