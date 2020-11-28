1. Install XAMPP.

2. Start Apache and MySQL.

3. Select PHPMyAdmin.

4. Create a new DataBase by selecting new on left bar.

5. Give name for the DataBase and click create. (Ex. DataBase_Name: loginregister).

6. Create a table by entering the name (Ex. Table_Name: users) and select no.of Columns required i.e 5.

7. Table Columns:  1)id, INT, Index: Primary, A_I;  2) fullname, TEXT; 3) username, VARCHAR, 100, Index: Unique; 4) password, TEXT; 5) email, VARCHAR, 300, Index: Unique.

8. Then select SAVE.

9. Take the 4 php files in the HelperFiles/LoginFiles i.e DataBase, DataBaseConfig, login & signup.

10. Place these files in XAMPP folder.

11. Go to XAMPP control panel and select Explorer then "htdocs".

12. Now create a new Folder (Ex. FolderName: LoginRegister) and copy the 4 php files into this folder.

13. Now open DataBaseConfig.php in a editor and change the "databaseName" to which ever name you've given in PHPMyAdmin and save it.

14. Now you can open and check these php file in a browser by entering the endpoint url's where the files are located:
        Ex:  http://localhost/LoginRegister/signup.php
        Ex:  http://localhost/LoginRegister/login.php

15. As the android studio doesn't take localhost change it to 10.0.2.2:
        Ex: http://10.0.2.2/LoginRegister/signup.php
        Ex: http://10.0.2.2/LoginRegister/login.php

16. Now create a app and integrate the api and the user will be able to signup and login.

17. After signup you can check the entered details in PHPMyAdmin.