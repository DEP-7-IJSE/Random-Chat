/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package service;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final File userDB = new File("User-db.user");
    private static List<User> USER_LIST = new ArrayList<>();

    private void readDataFromFile() {
        if (!userDB.exists()) return;

        try (FileInputStream fosStudent = new FileInputStream(userDB);
             ObjectInputStream oosStudent = new ObjectInputStream(fosStudent)) {

            USER_LIST = (ArrayList<User>) oosStudent.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean userIsExists(User user) {
        return USER_LIST.contains(user);
    }

    public boolean writeDataFile(User user) {
        try (FileOutputStream fosStudent = new FileOutputStream(userDB);
             ObjectOutputStream oosStudent = new ObjectOutputStream(fosStudent)) {

            USER_LIST.add(user);
            oosStudent.writeObject(USER_LIST);

        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}
