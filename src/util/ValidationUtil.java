/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package util;

public class ValidationUtil {

    public static boolean isValidUser(String user) {
        return user.matches("[A-Za-z]+-?[A-Za-z]+");
    }


    public static boolean isValidPassword(String password) {
        return password.matches("\\w{4,}");
    }

}
