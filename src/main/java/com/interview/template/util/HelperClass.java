package com.interview.template.util;

import com.interview.template.exceptions.UsernameNotAllowed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;


public class HelperClass {

    public static boolean isBlackListedUser(String username, String[] reservedWords) throws UsernameNotAllowed{

        for(String reservedWord : reservedWords){
            if(reservedWord.equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

}
