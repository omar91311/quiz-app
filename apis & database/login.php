<?php
error_reporting(0);
	include "connection.php";
if($_SERVER['REQUEST_METHOD'] == "POST"){
	$a = $_POST['uname'];$b = $_POST['password'];	
	$query = "select * from profile where uname=? and password=?";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("ss", $a, $b);
$item = array("uname"=>"i", "password"=>"a", "age"=>8, "gender"=>"yes");
$item2 = array("uname"=>"mmm", "password"=>"a", "age"=>8, "gender"=>"no");
$item3 = array("uname"=>"444", "password"=>"a", "age"=>8, "gender"=>"o");
$stmt->execute();
$stmt->bind_result($id, $bt, $ba, $bp, $bpr, $bpa);
	if($stmt->fetch())
		echo json_encode($item);
	else
			echo json_encode($item2);}
else
echo json_encode($item3);
$conn->close();?>
