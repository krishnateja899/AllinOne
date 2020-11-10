1. Download the files in ServerForLocalHost into a folder.

2. Open VisualStudioCode > Select the folder in which the files are placed and open terminal.

3. Enter:
        ```
        npm install
        ```

4. It will download all the required dependencies.

5. Then open terminal again and enter:
        ```
        node server.js
        ```

6. The project will run on htttp://localhost:3000/

7. In your Android studio enter 10.0.2.2 instead of localhost.
    //Go through this: https://developer.android.com/studio/run/emulator-networking

8. All the devices that will connect to the localhost will be able to message in the app.

9. Don't forget to add Socket.Io dependency in the gradle file.