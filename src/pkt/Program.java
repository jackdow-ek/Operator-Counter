package pkt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {

	public static void main(String[] args) {
		
		try {
			String icerik="";
			icerik = new String(Files.readAllBytes(Paths.get(args[0])));//veriler dosyadan okunur
			Lexical lx = new Lexical(icerik);//sinifin kurucu metoduna okunan veriler gonderilir
			lx.OperatorBul();//operator hesaplama islemlerinin yapildigi fonksiyon cagrilir
		}
		catch (IOException ex){
			System.out.println("Dosya bulunamadi");//verilen dosyanin bulunamamasi durumu
		}
	}
}
