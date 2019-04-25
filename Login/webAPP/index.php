<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <script src="index.js"></script>
    <title> Login </title>
</head>

<body>
    <form class="form-signin" id="formLogin" method="POST" action="profilePage.php">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Login</h1>
        </div>
        <br>
        <div class=" form-label-group text-danger text-center" id="response">
        </div>
        <div class="form-label-group">
            <input type="text" id="Username" name="username" class="form-control" placeholder="Username" required autofocus>
            <label for="Username">Username</label>
        </div>

        <div class="form-label-group">
            <input type="password" id="Password" name="password" class="form-control" placeholder="Password" required>
            <label for="Password">Password</label>
        </div>
        <input type="text" name="message" id="responseForProfilePage" hidden>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="button" onClick="login()">Login</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="signUp()">Registrati</button>
    </form>
</body>

</html>