import java.lang.Math;

public class PecaDama extends Peca {

	PecaDama(int i, int j, char cor, Tabuleiro T) {
		super(i, j,cor,T);
	}
	
	public boolean checar(int x2, int y2) {
		boolean permissao = false;
		
		if (Math.abs(x2-i) == Math.abs(y2-j)) {
			if (T.vPecasComuns[x2][y2] == null || T.vPecasDamas[x2][y2] == null) {
				int cont=0;
				if (Math.abs(x2-i) != 1) {
					int d, t;
					
					if (i<x2 && j<y2) {
						d=x2-i;
						for (t=1;t<d;t++) {
							if (T.vPecasComuns[i+t][j+t]!=null) {
								if (T.vPecasComuns[i+t][j+t].cor != cor) 
									cont++;
								else 
									return permissao;
							}
							else if (T.vPecasDamas[i+t][j+t]!=null) {
								if (T.vPecasDamas[i+t][j+t].cor != cor) 
									cont++;
								else
									return permissao;
							}	
						}
					}
					else if (i<x2 && j>y2) {
						d=x2-i;
						for (t=1;t<d;t++) {
							if (T.vPecasComuns[i+t][j-t]!=null) {
								if (T.vPecasComuns[i+t][j-t].cor != cor) 
									cont++;
								else
									return permissao;
							}
							else if (T.vPecasDamas[i+t][j-t]!=null) {
								if (T.vPecasDamas[i+t][j-t].cor != cor) 
									cont++;
								else
									return permissao;
							}	
						}
					}
					else if (i>x2 && j>y2) {
						d=i-x2;
						for (t=1;t<d;t++) {
							if (T.vPecasComuns[i-t][j-t]!=null) {
								if (T.vPecasComuns[i-t][j-t].cor != cor) 
									cont++;
								else
									return permissao;
							}
							else if (T.vPecasDamas[i-t][j-t]!=null) {
								if (T.vPecasDamas[i-t][j-t].cor != cor) 
									cont++;
								else
									return permissao;
							}
						}
					}
					else if (i>x2 && j<y2) {
						d=i-x2;
						for (t=1;t<d;t++) {
							if (T.vPecasComuns[i-t][j+t]!=null) {
								if (T.vPecasComuns[i-t][j+t].cor != cor) 
									cont++;
								else
									return permissao;
							}
							else if (T.vPecasDamas[i-t][j+t]!=null) {
								if (T.vPecasDamas[i-t][j+t].cor != cor) 
									cont++;
								else
									return permissao;
							}
						}
					}
				}
				if (cont==0) {
					if (T.ultimoJogador == cor)  //o jogador não pode se mover se foi o último a jogar
						return permissao;
					
					permissao = true;
				}
				if (cont == 1) {
					if (T.ultimoJogador == cor && T.ultimoMovimento=="movimento")// o jogador não pode capturar se foi o último a jogar...
						return permissao;												//...e na rodada anterior apenas se moveu
					
					permissao = true;
				}
			}
		}
		return permissao;
	}

}