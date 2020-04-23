public class Damas {
	public static void main(String[] args) {
		Tabuleiro T=new Tabuleiro();
		T.instanciaPecas(T);
		
		int posX1 = 0,posX2=0,posY1=0,posY2=0;
		char convertLetra[]= {'a','b','c','d','e','f','g','h'};
		char l,m;
		String s;
		boolean autorizado;
		
		System.out.println("Tabuleiro  inicial:");
		T.mostraTabuleiro();
		
		CSVReader csv = new CSVReader();
		csv.setDataSource("/home/jonathan/eclipse-workspace/Damas/src/Movimentos.csv");
		String commands[] = csv.requestCommands();
	
		for(int i=0;i<commands.length;i++){
			//primeiro são captados os dados do comando e transformados em posições de matriz:
			s=commands[i];
			
			l=s.charAt(0);
			posY1 =Character.getNumericValue(s.charAt(1))-1;
			m=s.charAt(3);
			posY2 =Character.getNumericValue(s.charAt(4))-1;
			
			System.out.println("Source: "+l+""+s.charAt(1));
			System.out.println("Target: "+m+""+s.charAt(4));
			
			for (int k=0;k<=7;k++) {
				if(l==convertLetra[k])
					posX1 = k;
				if(m==convertLetra[k])
					posX2=k;
			}
			
			//após isso as peças checam se o comando é válido e solicitam ao tabuleiro a movimentação
			if (T.vPecasComuns[posX1][posY1]!=null) {
				autorizado=T.vPecasComuns[posX1][posY1].checar(posX2,posY2);
				if (autorizado) 
					T.modificaComum(posX1,posY1,posX2,posY2,T);
				else 
					System.out.println("A peça não pode fazer o movimento solicitado.");
				
			}else if (T.vPecasDamas[posX1][posY1]!=null) {
				autorizado=T.vPecasDamas[posX1][posY1].checar(posX2,posY2);
				if (autorizado) 
					T.modificaDama(posX1,posY1,posX2,posY2);
				else 
					System.out.println("A peça não pode fazer o movimento solicitado.");
				
			}else
				System.out.println("Não há peça na posição solicitada");
			
			T.mostraTabuleiro();
		}
		
		
	}
}