<?php

class DbOperation
{
    //Database connection link
    private $con;

    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';

        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();

        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }

    function signUp($username, $password)
    {
        $username = strtolower($username);
        if ($this->con->query("INSERT INTO users (username, passwordHash) VALUE ('$username','$password')"))
            return true;
        return $this->con->errno;
    }


    function changePassword($username, $oldPassword, $newPassword)
    {
        $username = strtolower($username);
        if ($oldPassword == $newPassword)
            return 2;
        if ($this->con->query("UPDATE users SET passwordHash='$newPassword' WHERE username ='$username' AND passwordHash = md5('$oldPassword')"))
            return $this->con->affected_rows;
        return $this->con->errno;
    }

    function deleteUser($username,$password)
    {
        $username = strtolower($username);
        if ($this->con->query("DELETE FROM users WHERE username = '$username'AND passwordHash = md5('$password')"))
            return $this->con->affected_rows;
        return $this->con->errno;
    }
    function login($username, $password)
    {
        $username = strtolower($username);
        $result = $this->con->query("select * FROM users WHERE username = '$username' AND passwordHash = md5('$password') ");
        if ($result)
            return $result->num_rows;
        return $this->con->errno;
    }
}
