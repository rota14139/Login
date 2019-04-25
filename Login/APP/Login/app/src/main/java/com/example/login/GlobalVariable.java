package com.example.login;
public class GlobalVariable {
        //indirizzo del server
        //quando si utilizza un dispositivo emulato usare l'ip 10.0.2.2
        private static final String ROOT_URL = "http://10.0.2.2/login/API/v1/API.php?apicall=";

        public static final String URL_LOGIN = ROOT_URL + "login";
        public static final String URL_SIGNUP = ROOT_URL + "signUp";
        public static final String URL_CHANGEPASSWORD = ROOT_URL + "changePassword";
        public static final String URL_DELETEUSER = ROOT_URL + "deleteUser";


        public static String responseMessage = new String();
        public static Boolean responseError;
        public static String username = new String();
}
