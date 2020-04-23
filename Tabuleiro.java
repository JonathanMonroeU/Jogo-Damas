import java.lang.Math;

public class Tabuleiro {
	public PecaComum vPecasComuns[][];
	public PecaDama vPecasDamas[][];
	public char ultimoJogador;
	public String ultimoMovimento;
	
	Tabuleiro(){
		vPecasComuns=new PecaComum[8][8]; //criação do tabuleiro com duas matrizes de peças
		vPecasDamas=new PecaDama[8][8];
		ultimoJogador = 'X'; // nenhuma peça foi movimentada ainda
		ultimoMovimento="nenhum foi feito ainda";
	}
		
	public void instanciaPecas(Tabuleiro T) {
		int x,y;
		for(y=7;y>=0;y--) {			//criação das peças nas posições em que elas devem iniciar
			if (y==7||y==5) {
				for (x=0;x<=7;x++) {
					if (x%2==1)
						vPecasComuns[x][y]=new PecaComum(x,y,'P',T);	
				}	
			}else if (y==6) {
				for (x=0;x<=7;x++) {
					if (x%2==0)
						vPecasComuns[x][y]=new PecaComum(x,y,'P',T);	
				}
			}else if (y==2||y==0) {
				for (x=0;x<=7;x++) {
					if (x%2==0)
						vPecasComuns[x][y]=new PecaComum(x,y,'B',T);	
				}
			}else if (y==1) {
				for (x=0;x<=7;x++) {
					if (x%2==1)
						vPecasComuns[x][y]=new PecaComum(x,y,'B',T);	
				}
			}	
		}	
	}
	
	
	public void mostraTabuleiro() { 	
		int x,y;
		for(y=7;y>=0;y--) {
			System.out.print(y+1);
			for (x=0;x<=7;x++) {
				if (vPecasComuns[x][y]!=null)
					System.out.print(" "+vPecasComuns[x][y].cor);
				else if (vPecasDamas[x][y]!=null) {
					if (vPecasDamas[x][y].cor=='B')
						System.out.print(" C");	//ao imprimir uma peça branca que é dama, é usada a letra C para diferenciar da comum
					if (vPecasDamas[x][y].cor=='P')
						System.out.print(" Q"); //ao imprimir uma peça preta que é dama, é usada a letra Q para diferenciar da comum
				}else 
					System.out.print(" -");
			}System.out.print("\n");
		}	
		System.out.println("  a b c d e f g h");
		System.out.print("\n");
	}
	
	public void modificaComum(int posX1, int posY1, int posX2,int posY2, Tabuleiro T) {
		
		//o método deixa vazio o espaço de onde a peça movida saiu 
		
			vPecasComuns[posX2][posY2]=vPecasComuns[posX1][posY1];
			vPecasComuns[posX2][posY2].i=posX2;
			vPecasComuns[posX2][posY2].j=posY2;
			ultimoJogador = vPecasComuns[posX2][posY2].cor;
			
			vPecasComuns[posX1][posY1]=null;
			
		//o método verifica se a peça alcançou o lado oposto, se sim, transforma a peça comum em dama
			
			if (posY2==7) {
				if (vPecasComuns[posX2][posY2].cor=='B') {
					vPecasDamas[posX2][posY2]=new PecaDama(posX2,posY2,'B',T);
					vPecasComuns[posX2][posY2]=null;
				}
			}
				
			else if (posY2==0) {
				if (vPecasComuns[posX2][posY2].cor=='P') {
					vPecasDamas[posX2][posY2]=new PecaDama(posX2,posY2,'P',T);
					vPecasComuns[posX2][posY2]=null;
				}
			}
			
		//por fim, também exclui a peça entre as duas posições do comando se tiver passado por cima de outra
			if (Math.abs(posX1-posX2)==2) {
				captura((posX1+posX2)/2, (posY1+posY2)/2);
			}else
				ultimoMovimento="movimento";
	}	
	
	public void modificaDama(int posX1, int posY1, int posX2,int posY2) {
		
		//o método deixa vazio o espaço de onde a peça movida saiu 
		vPecasDamas[posX2][posY2]=vPecasDamas[posX1][posY1];
		vPecasDamas[posX2][posY2].i=posX2;
		vPecasDamas[posX2][posY2].j=posY2;
		ultimoJogador = vPecasDamas[posX2][posY2].cor;
			
		vPecasDamas[posX1][posY1]=null;
		
		//por fim, também exclui a peça entre as duas posições do comando 
			//se tiver passado por cima de outra
		
		ultimoMovimento="movimento";	//afirma que esse movimento é só de movimento POR ENQUANTO,...
											//sabendo que o programa vai ver em seguida se há alguma captura
		if (Math.abs(posX1-posX2)!=1) {
			int d, i;
			//para excluir a peça capturada o programa checa de que direção veio a dama
			if (posX1<posX2 && posY1<posY2) {
				d=posX2-posX1;
				for (i=1;i<d;i++) {
					captura(posX1+i, posY1+i);
				}
			}
			else if (posX1<posX2 && posY1>posY2) {
				d=posX2-posX1;
				for (i=1;i<d;i++) {
					captura(posX1+i, posY1-i);
				}
			}
			else if (posX1>posX2 && posY1>posY2) {
				d=posX1-posX2;
				for (i=1;i<d;i++) {
					captura(posX1-i, posY1-i);
				}
			}
			else if (posX1>posX2 && posY1<posY2) {
				d=posX1-posX2;
				for (i=1;i<d;i++) {
					captura(posX1-i, posY1+i);
				}
			}
		}
	}
	
	public void captura(int x, int y) {
		ultimoMovimento="captura";
		if (vPecasComuns[x][y]!=null)
			vPecasComuns[x][y]=null;
		else if (vPecasDamas[x][y]!=null)
			vPecasDamas[x][y]=null;
	}
}