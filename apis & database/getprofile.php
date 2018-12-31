<?php
	include "connection.php";
	$un = $_GET['uname'];
	$query = "Select uname, password, age, gender FROM profile where uname='$un'";
	$stmt = $conn->prepare($query);
	$stmt->execute();
	$stmt->bind_result($un, $pa, $ag, $gn);
	$rs = array();
	//$dt = "";
	if($stmt->fetch()){
		$item = array("uname"=>$un, "password"=>$pa, "age"=>$ag, "gender"=>$gn);
		//$dt = $dt."$un $pa $ag $gn";
	}
	echo json_encode($item);
?>
