import java.text.DecimalFormat;
import java.util.Scanner;

class Stock{
	
		public String StockName;				//	주식 이름
		public double CurrentPrice; 			//	현재 주식의 가격
		public double HoldingPrice; 			//	보유 주식의 가격
		public int NumberofStocks; 				// 	보유 주식의 수
		
		public double ProfitandLoss; 			//	손익
		public double Yield; 					// 수익률
		public double PurchasePrice;			//	매수금액
		
		
		public void StockInput(Scanner scanner) {		//입력 메소드(주식 정보)
			System.out.print("주식 명을 입력해주세요 : ");
			StockName = scanner.next();
			System.out.print("입력한 주식의 현재 가격을 입력하세요(단위=원) : ");
			CurrentPrice = scanner.nextInt();
			System.out.print("보유 주식의 수를 입력하세요: ");
			NumberofStocks = scanner.nextInt();
			System.out.print("보유 주식의 평균가를 입력하세요(단위=원): ");
			HoldingPrice = scanner.nextInt();
			System.out.println("----------");
		}
		public double getPurchasePrice() { 			//	매수 금액을 구해 반환하는 메소드
			return HoldingPrice * NumberofStocks;
		}
		
		public double getYield() { 					// 	수익률을 구해 반환하는 매소드
			return ProfitandLoss / (NumberofStocks * HoldingPrice) * 100;
		}

		public double getProfitandLoss() { // 손익을 구해 반환하는 매소드
			return (CurrentPrice - HoldingPrice) * NumberofStocks;
		}
	
		
		public void StockPrint() { 					//출력 메소드
			
			DecimalFormat df = new DecimalFormat("###,###");
			
			System.out.println("주식 명: " + StockName);		//주식 이름 출력
			
			ProfitandLoss = getProfitandLoss();
			System.out.println("손익 : " + df.format(ProfitandLoss) + "원");	//손익 출력

			if(ProfitandLoss >= 0)		//수익률 출력
			{
				Yield = getYield();
				System.out.println("수익률 : " + String.format("%.2f", Yield) + "%");		
			}
			else 
			{	ProfitandLoss = Math.abs(ProfitandLoss);
				Yield = getYield();
				System.out.println("수익률 : -" + String.format("%.2f", Yield) + "%");
			}
			
			System.out.println();
			System.out.println();
		}
}

class AddStock extends Stock{  			//	AddStock 클래스는 Stock클래스를 상속받음
	public int Quantity; 				//	추가 매수할 주식의 수
	public double nextPurchasePrice; 	//	새로 추가할 매수금액

	public double NewHoldingStock = getNewHoldingPrice(); 	// 	추가 매수 후 평단가
	
	public double getNewPurchasePrice() {					//	추가 매수할 금액을 구해 반환하는 메소드
		return Quantity * CurrentPrice;
	}
	public double getNewHoldingPrice() { 					//	추가 매수 후 평단가를 구해 반환하는 메소드
		
		return (PurchasePrice + nextPurchasePrice) / (NumberofStocks + Quantity);
	}
	public double getYield() { 								// 	예상	수익률을 구해 반환하는 매소드
		return ProfitandLoss / getNewHoldingPrice() * 100;
	}
	
	
	public void StockInput(Scanner scanner) {				//	입력 메소드 오버라이딩
		System.out.print("주식 명을 입력해주세요 : ");
		StockName = scanner.next();
		System.out.print("입력한 주식의 현재 가격을 입력하세요(단위=원) : ");
		CurrentPrice = scanner.nextInt();
		System.out.print("보유 주식의 수를 입력하세요: ");
		NumberofStocks = scanner.nextInt();
		System.out.print("보유 주식의 평균가를 입력하세요(단위=원): ");
		HoldingPrice = scanner.nextInt();
		System.out.print("추가 매수할 주식의 수를 입력해주세요(단위=원): ");
		Quantity = scanner.nextInt();
		System.out.println("----------");
	}
	
	public void StockPrint() { 								//	출력 메소드 오버라이딩
		DecimalFormat df = new DecimalFormat("###,###");
		System.out.println("주식 명: " +  StockName);
		System.out.println("추가 매수 후 보유주식 수 : " + (NumberofStocks + Quantity));
		
		PurchasePrice = getPurchasePrice();
		nextPurchasePrice = getNewPurchasePrice();
		
		System.out.println("추가 매수 후 평단가(단위=원) : " + df.format((int)getNewHoldingPrice()));	//	추가 매수 후 평단가 출력
		
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
