<?php

$errorList = array(
    //"num"=>"Message error"
    "signUp" => array(
        1062 => "Utente già registrato",
    ),
    "login" => array(
        0 => "Passowrd o nome utente errati",
    ),
    "changePassword" => array(
        0 => "Password errata",
        2 => "Le due password sono uguali",
    ),
    "deleteUser" => array(
        0 => "Password errata",
    ),
);

require_once '../includes/DbOperation.php';
//controlla se esistono i parametri richiesti
function parametersAvailable($params)
{
    $available = true;
    $missingparams = "";

    foreach ($params as $param) {
        if (!isset($_POST[$param]) || strlen($_POST[$param]) <= 0) {
            $available = false;
            $missingparams = $missingparams . ", " . $param;
        }
    }

    //se manca qualche parametro
    if (!$available) {
        $response = array();
        $response['error'] = true;
        $response['message'] = 'Mancano i seguenti parametri: ' . substr($missingparams, 1, strlen($missingparams));

        //inviamo l'errore
        echo json_encode($response);

        //fermiamo l'esecuzione dello script
        die();
    }
}


$response = array();

if (isset($_GET['apicall'])) {
    $apicall = $_GET['apicall'];
    switch ($apicall) {

        case 'signUp':
            //Controlliamo se sono stati passati i parametri corretti
            parametersAvailable(array('username', 'password'));
            $db = new DbOperation();

            $result = $db->signUp(
                $_POST['username'],
                $_POST['password']
            );

            if ($result == 1) {

                $response['error'] = false;
                $response['message'] = 'Utente registrato con successo';
            } else {

                //impostiamo errore e messaggio di errore 
                $response['error'] = true;
                $response['message'] = array_key_exists($result, $errorList["$apicall"]) ? $errorList["$apicall"][$result] : "Si è verificato errore. n:$result";
            }
            break;

        case 'changePassword':
            parametersAvailable(array('username', 'oldPassword', 'newPassword'));
            $db = new DbOperation();
            $result = $db->changePassword(
                $_POST['username'],
                $_POST['oldPassword'],
                $_POST['newPassword']
            );

            if ($result == 1) {
                $response['error'] = false;
                $response['message'] = 'Password aggiornata con successo';
            } else {
                //impostiamo errore e messaggio di errore 
                $response['error'] = true;
                $response['message'] = array_key_exists($result, $errorList["$apicall"]) ? $errorList["$apicall"][$result] : "Si è verificato errore. n:$result";
            }
            break;

        case 'deleteUser':
            parametersAvailable(array('username', 'password'));
            $db = new DbOperation();
            $result = $db->deleteUser($_POST['username'], $_POST['password']);

            if ($result == 1) {
                $response['error'] = false;
                $response['message'] = 'Utente eliminato con successo';
            } else {
                //impostiamo errore e messaggio di errore 
                $response['error'] = true;
                $response['message'] = array_key_exists($result, $errorList["$apicall"]) ? $errorList["$apicall"][$result] : "Si è verificato errore. n:$result";
            }
            break;

        case 'login':
            parametersAvailable(array('username', 'password'));
            $db = new DbOperation();
            $result = $db->login(
                $_POST['username'],
                $_POST['password']
            );

            if ($result == 1) {
                $response['error'] = false;
                $response['message'] = 'Accesso avvenuto con successo';
            } else {
                //impostiamo errore e messaggio di errore 
                $response['error'] = true;
                $response['message'] = array_key_exists($result, $errorList["$apicall"]) ? $errorList["$apicall"][$result] : "Si è verificato errore. n:$result";
            }
            break;
        default:
            $response['error'] = true;
            $response['message'] = 'Questa chiamata API non viene gestita';
    }
} else {
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);
