public class Peca {
	int i,j;  //posição em que a peça se encontra
	char cor;
	Tabuleiro T;
	
	Peca(int i,int j,char cor,Tabuleiro T){
		this.i=i; 
		this.j=j;
		this.cor=cor;
		this.T=T;
	}
}
