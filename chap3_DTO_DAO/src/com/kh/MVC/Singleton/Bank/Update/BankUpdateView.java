package com.kh.MVC.Singleton.Bank.Update;

import java.sql.SQLException;
import java.util.Scanner;

public class BankUpdateView {
	
	public void UpdateBank() {
		Scanner sc = new Scanner(System.in);
		System.out.println("변경하실 [계정 ID]와 [금액] 을 입력해주세요");
		
		System.out.println("변경하실 계정 ID : ");
		int bankId = sc.nextInt();
		
		System.out.println("업데이트하실 금액 : ");
		double bankBalance = sc.nextDouble();

		BankUpdateDTO updatebank = new BankUpdateDTO(bankId, bankBalance);
		System.out.println("***DTO에 전달,저장완료***");
		BankUpdateModel bankupdateModel;
		boolean success = false;
		
		
		try {
			bankupdateModel= BankUpdateModel.getInstance();
			System.out.println("***인스턴스 생성!***");
			success = bankupdateModel.UpdateBank(updatebank);
			System.out.println("***success에 값들어감***");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
		
		if(success) {
			System.out.println("통장이 성공적으로 업데이트 되었습니다.");
		}else {
			System.out.println("통장 업데이트 중 오류가 발생했습니다.");
		}
		
		sc.close();
		
		
	}

}
