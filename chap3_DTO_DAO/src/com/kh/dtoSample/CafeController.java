package com.kh.dtoSample;

import java.sql.Connection;
import java.util.List;

// model과 view 연결해주는 중간 다리
public class CafeController {
	// 필드 
	
	private CafeModel model; // 모델 멤버변수
	private CafeView view; // 뷰 멤버변수
	
	// 파라미터를 작성한 생성자
	public CafeController(Connection con, CafeView view) {
		this.model = new CafeModel(con);
		this.view = view;
	}
	
	//출력 메소드
	public void displayAllCafes() {
		List<CafeDTO> cafes = model.getCafes();
		view.displayCafes(cafes);
	}
	

}
