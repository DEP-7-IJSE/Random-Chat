/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package model;

import java.io.Serializable;

public class User implements Serializable {
    private String user;
    private String password;

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String fromMap(String user) {
        return user.replace("u#", "");
    }

    /*public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("user", DigestUtils.sha256Hex(user));
        map.put("password", DigestUtils.sha256Hex(password));
        return map;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
