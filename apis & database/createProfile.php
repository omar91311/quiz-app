<?php
//error_reporting(0);
	include "connection.php";
if($_SERVER['REQUEST_METHOD'] == "POST"){
	$a = $_POST['uname'];$b = $_POST['password'];	$c = $_POST['age'];$d = $_POST['gender'];
	$query = "insert into profile (uname, password, age, gender) values (?, ?, ?, ?)";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("ssis", $a, $b, $c, $d);
$item = array("uname"=>"i", "password"=>"a", "age"=>8, "gender"=>"yes");
$item2 = array("uname"=>"mmm", "password"=>"a", "age"=>8, "gender"=>"no");
$item3 = array("uname"=>"444", "password"=>"a", "age"=>8, "gender"=>"o");
	if($stmt->execute())
		echo json_encode($item);
	else
			echo json_encode($item2);}
else
echo json_encode($item3);
$conn->close();?>
