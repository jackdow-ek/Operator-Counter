package pkt;

public class Lexical {
	private String yorumsuz = "";
	private int operator_ikili = 0;
	private int operator_tekli = 0;
	private int toplam_iliskisel = 0;
	private int toplam_mantiksal = 0;
	
	Lexical(String metin){//kurucu metot icerisinde metin yorum satirlarindan temizlenir
		metin = metin + "\n";
		while(metin.indexOf("*/")!=-1)
		{
			String sil = metin.substring(metin.indexOf("/*"),metin.indexOf("*/")+2);// /* */ ve arasindaki ifadeler metin icerisinden temizlenir
			metin = metin.replace(sil, "");
		}
		while(metin.indexOf("//")!=-1)
		{
			String sil = metin.substring(metin.indexOf("//"),metin.indexOf("\n",metin.indexOf("//"))); // // ile yazilan yorum satirlari metin icerisinden temizlenir
			metin = metin.replace(sil, "\n");
		}
		this.yorumsuz = metin;
	}
	
	public void OperatorBul()//operator hesaplamalarinin gerceklestirildigi fonksiyon
	{
		String[] sorgula = {"++","--","+=","-=","/=","*=","%=","&=","|=","^=","<=",">=","==","!=","&&","||","!","<",">","+","-","*","/","%","&","|","^","="};
		//0->9 ve 19->27 	 sayisal operatorlerin bulundugu index araligi
		//10->13 ve 17->18	 mantiksal operatorlerin bulundugu index araligi
		//14->16 			 iliskisel operatorlerin bulundugu index araligi

		StringBuffer hesaplanacak = new StringBuffer(yorumsuz);
		for(int i = 0; i<sorgula.length;i++) {//ilk indexteki operatorden baslayarak kontrol edilir
			while(hesaplanacak.indexOf(sorgula[i])!=-1) {//ifade icerisinde operator kalmayincaya kadar islem devam eder
				//bulununan operatorun hangi tur oldugu kontrol edilir ve o operatore ait olan sayacin degeri artirilir
				if(i<=2) {//tekli sayisal operator
					operator_tekli++;
				}
				else if((i>=10 && i<=13) || (i>=17 && i<=18)) {//iliskisel operator
					toplam_iliskisel++;
				}
				else if(i>=14 && i<=16) {//mantiksal operator
					toplam_mantiksal++;
				}
				else if((i>=3 && i<=9)||(i>=19 && i<=27)) { //ikili sayisal operator
					operator_ikili++;
				}
				if(sorgula[i].length()==2){//bulunan operator metinden silinir bu sayede metin bir sonraki dongu adiminda o opertoru icermez
					hesaplanacak = hesaplanacak.replace( hesaplanacak.indexOf(sorgula[i]) , hesaplanacak.indexOf(sorgula[i])+2 ," ");//operatorun iki karakterli olmasi durumunda silme islemi
				}
				else {
					hesaplanacak = hesaplanacak.replace( hesaplanacak.indexOf(sorgula[i]) , hesaplanacak.indexOf(sorgula[i])+1 ," ");//operatorun tek karakterli olmasi durumunda silme islemi
				}
			}
		}
		
		//hesaplamalar bittikten sonra bulununan degerler ekrana yazdirilir
		System.out.println("Operator Bilgisi:\n\tTekli Operator Sayisi: " + operator_tekli);
		System.out.println("\tIkili Operator Sayisi: " + operator_ikili);
		System.out.println("\tSayisal Operator Sayisi: " + (operator_ikili+operator_tekli));
		System.out.println("\tIliskisel Operator Sayisi: " + toplam_iliskisel);
		System.out.println("\tMantiksal Operator Sayisi: " + toplam_mantiksal);
		System.out.println("Operand Bilgisi:\n\tToplam Operand Sayisi: " + (operator_tekli + 2*(operator_ikili+toplam_mantiksal+toplam_iliskisel)));
	}
}
