package com.shop.myshop.validator;

public class AuthorizeApi {

    public boolean checkHeader(String header){
        if ("pass".equals(header)) {
            return true;
        }
        else return false;
    }
}
