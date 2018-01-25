<?php
	include('config.php'); 

	if($_SERVER["REQUEST_METHOD"]== "POST"){
		getAll();
	}

	function getAll(){
		$con = mysqli_connect(HOST_DB,USUARIO_DB,USUARIO_PASS,NOMBRE_DB);
		if(mysqli_connect_errno()){
			echo "Error en la conexión: ".mysqli_connect_error();
		}
		
		$sql = "select * from persona;";
		$result = mysqli_query($con,$sql);
		if($result){
			$number_rows = mysqli_num_rows($result);
			$array = array();

			if($number_rows > 0){
				while($row = mysqli_fetch_assoc($result)){
					$array[]=$row;
				}
				header('Content-Type: aplication/json');
				echo json_encode(array("personas"=>$array));
			}

			mysqli_close($con);
		}
		else{
			echo " Error leyendo";	
			mysqli_close($con);
		}

	}


?>