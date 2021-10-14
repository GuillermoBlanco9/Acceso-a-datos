<?php
$url1="https://germinadordetalentos.com/agradecimientos";

$url2="https://germinadordetalentos.com/blog";

$nombreFichero="C:/Tech/area-git/PruebaJava1/resources/info-usuario.cfg";
$ficheroXML="infodam2.xml";
$ficheroJSON="infoinstitutodam2.json";

/*
// Devuelve el contenido de una web
$web = file_get_contents($url1);
echo "<br>WEB: <br>$web<br>";

// El código anterior es igual que el siguiente:
$fh = fopen($url2, "r");
fpassthru($fh);
*/

// Devuelve contenido de un archivo en el directorio actual
echo "<br>INICIO file_get_contents:<br>";
echo "---------------------------------------------<br>";
$contenido = file_get_contents($ficheroXML);
echo "<br>file_get_contents: <br>$contenido<br>";
echo "---------------------------------------------<br>";
echo "<br>FIN file_get_contents: <br><br>";



echo "<br>INICIO FOPEN-FGETS-FPASSTHRU:<br>";
echo "---------------------------------------------<br>";
$fp = fopen($ficheroXML,"r");
// Lee la primera línea, que es Esta es la línea 1
fgets($fp);
// Envía el resto del archívo al output buffer
echo fpassthru($fp);
// Esta es la línea 2
fgets($fp);
// Envía el resto del archívo al output buffer
echo fpassthru($fp);
/*
Esta es la línea 3
Esta es la línea 4
Y el número de caracteres: 59, porque hemos hecho echo
*/
fclose($fp);
echo "---------------------------------------------<br>";
echo "<br>FIN FOPEN-FGETS-FPASSTHRU: <br><br>";


echo "<br>INICIO READFILE:<br>";
echo "---------------------------------------------<br>";
// Devuelve el contenido y el número de bytes, 79
$contenidoJSON = readfile($ficheroJSON);
echo "<br>$contenidoJSON<br>";
echo "---------------------------------------------<br>";
echo "<br>FIN READFILE: <br><br>";



echo "<br>INICIO file_get_contents con FILE_USE_INCLUDE_PATH: XML<br>";
echo "---------------------------------------------<br>";
// Busca dentro de include_path
$contenidoXML = file_get_contents("./$ficheroXML", FILE_USE_INCLUDE_PATH);
echo "<br>$contenidoXML<br>";
echo "---------------------------------------------<br>";
echo "<br>FIN file_get_contents con FILE_USE_INCLUDE_PATH: XML<br><br>";


// Devuelve 20 caracteres desde el carácter 100
echo "<br>INICIO file_get_contents (devuelve n caracteres a partir de un número dado): JSON<br>";
echo "---------------------------------------------<br>";
$contenido1 = file_get_contents($ficheroJSON, NULL, NULL, 10, 155);
echo "<br>$contenido1<br>";
echo "---------------------------------------------<br>";
echo "<br>FIN file_get_contents (devuelve n caracteres a partir de un número dado): JSON<br><br>";

?>