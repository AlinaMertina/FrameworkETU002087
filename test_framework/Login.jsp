<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="cssperso/style.css" rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <form action="authentification" method="Post">
        <div class="mb-3 row">
            <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
            <input type="text"  class="form-control-plaintext" id="staticEmail" value="Admin" name="profile">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword" value="Mertina" name="motpasse">
            </div>
        </div>
        <input type="submit" value="valider">
    </form>
   
</body>
</html> 
