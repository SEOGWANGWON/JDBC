package com.kh.mySampleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class MyModel {
	Connection connection;
	
	public MyModel(Connection connection) {
		this.connection = connection;
	}
	
	public List<MySampleDTO> getSample(){
		List<MySampleDTO> sample = new ArrayList<>();
		
		PreparedStatement st = connection.prepareStatement() 
		
	}
}
