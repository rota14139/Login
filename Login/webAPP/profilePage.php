<?php
if (!isset($_POST["username"]) || !isset($_POST["message"]))
    header("location: .");
?>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <script src="profilePage.js"></script>
    <title> Login </title>
</head>

<body>
    <div class="fluid-container">
        <div class="row">
            <div class="col border-right">
                <h1 class="text-center text-primary">Utente</h1>
                <h3 class="text-center" id="username"><?php echo ($_POST["username"]); ?></h3>
                <br>
                <p class="text-center text-success"><?php echo ($_POST["message"]); ?></p>
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="button" onClick="window.location.href='.'">Esci</button>
            </div>

            <div class="col border-left">
                <label for="oldPassword">Inserisci vecchia password</label>
                <input type="password" id="oldPassword" class="form-control" placeholder="Password" required>
                <label for="newPassword">Inserisci nuova password</label>
                <input type="password" id="newPassword" class="form-control" placeholder="Password" required>
                <div class="form-label-group text-danger text-center" id="response">
                    <br></div>
                <button class="btn btn-lg btn-primary btn-block" type="button" onclick="changePassword()">Cambia password</button>
                <button class="btn btn-lg btn-block btn-danger" type="button" onclick="deleteAccount()">Elimina Account</button>
            </div>
        </div>
    </div>
</body>

</html>