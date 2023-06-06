<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Bonjour</h1>
    <!-- <form action="fonction" method="post" enctype="multipart/form-data">
        Age:<input type="number" name="age" value="20">
        <br/>
        Nom:<input type="text" name="nom" value="Mertina">
        <br/>
        Salaire:<input type="number" name="salaire" value="1000">
        <br/>
        Float:<input type="text" name="test_Float" value="1000">
        <br/>
        Date de naissance:<input type="date" name="date_naissance" >
        <br/>
        <input type="hidden" name="nom_adulte" value="claudia">
        <input type="file" name="file" >

        <input type="hidden" name="age_adulte" value="20">
        <br/>
        <input type="submit" value="valider">
    </form> -->

    <form action="tabeemp_singleton" method="post" enctype="multipart/form-data">
        Mertina:<input type="text" name="nomemp[]" value="Mertina">
        <br/>
        Dylan:<input type="text" name="nomemp[]" value="Dylan">
        <br/>
        Alain:<input type="text" name="nomemp[]" value="Alain">
        <br/>
        <input type="submit" value="valider">
    </form>

</body>
</html>  
