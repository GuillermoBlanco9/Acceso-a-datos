<?php
header('Content-Type: application/json; charset=UTF-8');

$ficheroJSON="../recursos/infoinstitutodam2.json";
// Devuelve contenido de un archivo en el directorio actual
$textoJson = file_get_contents($ficheroJSON);
echo $textoJson;


//echo "<br>---------------------------------------------<br>";
//$infoJSON=json_decode($textoJson);
//var_dump($infoJSON);


?>