<?php
	include('config.php'); 

	if($_SERVER["REQUEST_METHOD"]== "POST"){
		createPerson();
	}

	function createPerson(){
		$con = mysqli_connect(HOST_DB,USUARIO_DB,USUARIO_PASS,NOMBRE_DB);
		if(mysqli_connect_errno()){
			echo "Error en la conexión: ".mysqli_connect_error();
		}
		$nombre = $_POST['nombre'];
		$telefono = $_POST['telefono'];
		$direccion = $_POST['direccion'];

		$sql = "INSERT INTO persona (Nombre,Telefono,Direccion) values ('$nombre','$telefono','$direccion');";
		if(mysqli_query($con,$sql)){
			echo " Se ha insertado: ".$nombre;
			mysqli_close($con);
		}
		else{
			echo " Error insertado: ".$nombre;	
			mysqli_close($con);
		}

	}


?>