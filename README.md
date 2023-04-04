# App Store

Application Documentation

## How to build and start App-store-backend

App-store-backend is only half of the application. You will also need [app-store-ui](https://github.com/fillip1984/app-store-ui). After following these steps to build and start the backend you can pull down that project and start up the frontend.

You can use any IDE of your choice. These instructions will show you how to pull down the backend code into VS Code. To use VS Code there are a number of plugins that you'll need. I believe that VS Code will detect the Java nature of this project and suggest plugins for you to pull down and will also ask you to install or reference an instance of Java.

From your commandline of choice:

```cmd
git clone https://github.com/fillip1984/app-store-backend
cd app-store-backend
code .
```

VS Code should detect that the application is built with maven and begin to pull down dependencies. You will need Java 17 installed for the application to work. The application uses a file-based database, h2, which will be stored in your home directory under a h2 directory. You can control this with the spring.datasource.url property in application.properties.

When pulling down this project I had to resort to using the MAVEN tab and run the package lifecycle step. I also had to close and reopen VS Code for Java tooling to initialize properly.

After that, I was able to use the SpringBoot Dashboard plugin for VS Code to start the server (AppStoreApplication.java)
