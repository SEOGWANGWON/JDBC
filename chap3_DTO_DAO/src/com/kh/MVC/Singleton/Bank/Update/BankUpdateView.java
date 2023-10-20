package com.kh.MVC.Singleton.Bank.Update;

import java.sql.SQLException;
import java.util.Scanner;

public class BankUpdateView {
	
	public void UpdateBank() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�����Ͻ� [���� ID]�� [�ݾ�] �� �Է����ּ���");
		
		System.out.println("�����Ͻ� ���� ID : ");
		int bankId = sc.nextInt();
		
		System.out.println("������Ʈ�Ͻ� �ݾ� : ");
		double bankBalance = sc.nextDouble();

		BankUpdateDTO updatebank = new BankUpdateDTO(bankId, bankBalance);
		System.out.println("***DTO�� ����,����Ϸ�***");
		BankUpdateModel bankupdateModel;
		boolean success = false;
		
		
		try {
			bankupdateModel= BankUpdateModel.getInstance();
			System.out.println("***�ν��Ͻ� ����!***");
			success = bankupdateModel.UpdateBank(updatebank);
			System.out.println("***success�� ����***");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
		
		if(success) {
			System.out.println("������ ���������� ������Ʈ �Ǿ����ϴ�.");
		}else {
			System.out.println("���� ������Ʈ �� ������ �߻��߽��ϴ�.");
		}
		
		sc.close();
		
		
	}

}
