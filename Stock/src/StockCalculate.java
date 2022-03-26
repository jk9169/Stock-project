import java.text.DecimalFormat;
import java.util.Scanner;

class Stock{
	
		public String StockName;				//	�ֽ� �̸�
		public double CurrentPrice; 			//	���� �ֽ��� ����
		public double HoldingPrice; 			//	���� �ֽ��� ����
		public int NumberofStocks; 				// 	���� �ֽ��� ��
		
		public double ProfitandLoss; 			//	����
		public double Yield; 					// ���ͷ�
		public double PurchasePrice;			//	�ż��ݾ�
		
		
		public void StockInput(Scanner scanner) {		//�Է� �޼ҵ�(�ֽ� ����)
			System.out.print("�ֽ� ���� �Է����ּ��� : ");
			StockName = scanner.next();
			System.out.print("�Է��� �ֽ��� ���� ������ �Է��ϼ���(����=��) : ");
			CurrentPrice = scanner.nextInt();
			System.out.print("���� �ֽ��� ���� �Է��ϼ���: ");
			NumberofStocks = scanner.nextInt();
			System.out.print("���� �ֽ��� ��հ��� �Է��ϼ���(����=��): ");
			HoldingPrice = scanner.nextInt();
			System.out.println("----------");
		}
		public double getPurchasePrice() { 			//	�ż� �ݾ��� ���� ��ȯ�ϴ� �޼ҵ�
			return HoldingPrice * NumberofStocks;
		}
		
		public double getYield() { 					// 	���ͷ��� ���� ��ȯ�ϴ� �żҵ�
			return ProfitandLoss / (NumberofStocks * HoldingPrice) * 100;
		}

		public double getProfitandLoss() { // ������ ���� ��ȯ�ϴ� �żҵ�
			return (CurrentPrice - HoldingPrice) * NumberofStocks;
		}
	
		
		public void StockPrint() { 					//��� �޼ҵ�
			
			DecimalFormat df = new DecimalFormat("###,###");
			
			System.out.println("�ֽ� ��: " + StockName);		//�ֽ� �̸� ���
			
			ProfitandLoss = getProfitandLoss();
			System.out.println("���� : " + df.format(ProfitandLoss) + "��");	//���� ���

			if(ProfitandLoss >= 0)		//���ͷ� ���
			{
				Yield = getYield();
				System.out.println("���ͷ� : " + String.format("%.2f", Yield) + "%");		
			}
			else 
			{	ProfitandLoss = Math.abs(ProfitandLoss);
				Yield = getYield();
				System.out.println("���ͷ� : -" + String.format("%.2f", Yield) + "%");
			}
			
			System.out.println();
			System.out.println();
		}
}

class AddStock extends Stock{  			//	AddStock Ŭ������ StockŬ������ ��ӹ���
	public int Quantity; 				//	�߰� �ż��� �ֽ��� ��
	public double nextPurchasePrice; 	//	���� �߰��� �ż��ݾ�

	public double NewHoldingStock = getNewHoldingPrice(); 	// 	�߰� �ż� �� ��ܰ�
	
	public double getNewPurchasePrice() {					//	�߰� �ż��� �ݾ��� ���� ��ȯ�ϴ� �޼ҵ�
		return Quantity * CurrentPrice;
	}
	public double getNewHoldingPrice() { 					//	�߰� �ż� �� ��ܰ��� ���� ��ȯ�ϴ� �޼ҵ�
		
		return (PurchasePrice + nextPurchasePrice) / (NumberofStocks + Quantity);
	}
	public double getYield() { 								// 	����	���ͷ��� ���� ��ȯ�ϴ� �żҵ�
		return ProfitandLoss / getNewHoldingPrice() * 100;
	}
	
	
	public void StockInput(Scanner scanner) {				//	�Է� �޼ҵ� �������̵�
		System.out.print("�ֽ� ���� �Է����ּ��� : ");
		StockName = scanner.next();
		System.out.print("�Է��� �ֽ��� ���� ������ �Է��ϼ���(����=��) : ");
		CurrentPrice = scanner.nextInt();
		System.out.print("���� �ֽ��� ���� �Է��ϼ���: ");
		NumberofStocks = scanner.nextInt();
		System.out.print("���� �ֽ��� ��հ��� �Է��ϼ���(����=��): ");
		HoldingPrice = scanner.nextInt();
		System.out.print("�߰� �ż��� �ֽ��� ���� �Է����ּ���(����=��): ");
		Quantity = scanner.nextInt();
		System.out.println("----------");
	}
	
	public void StockPrint() { 								//	��� �޼ҵ� �������̵�
		DecimalFormat df = new DecimalFormat("###,###");
		System.out.println("�ֽ� ��: " +  StockName);
		System.out.println("�߰� �ż� �� �����ֽ� �� : " + (NumberofStocks + Quantity));
		
		PurchasePrice = getPurchasePrice();
		nextPurchasePrice = getNewPurchasePrice();
		
		System.out.println("�߰� �ż� �� ��ܰ�(����=��) : " + df.format((int)getNewHoldingPrice()));	//	�߰� �ż� �� ��ܰ� ���
		
		System.out.println();
		System.out.println();
	}
}
	
public class StockCalculate {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	
		Stock a1 = new Stock();
		a1.StockInput(scanner);
		a1.StockPrint();
	
		Stock a2 = new Stock();
		a2.StockInput(scanner);
		a2.StockPrint();
		
		AddStock a3 = new AddStock();
		a3.StockInput(scanner);
		a3.StockPrint();
		
		AddStock a4 = new AddStock();
		a4.StockInput(scanner);
		a4.StockPrint();
		
		scanner.close();
	}
	
}
