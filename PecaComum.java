import java.lang.Math;

public class PecaComum extends Peca{

	PecaComum(int i, int j,char cor, Tabuleiro T) {
		super(i, j,cor,T);
	}
	
	public boolean checar (int x2, int y2) {
		boolean permissao = false;
		
		if (Math.abs(x2-i) == 1) {
			if (T.ultimoJogador == cor)  //o jogador não pode se mover se foi o último a jogar
				return permissao;
			
			if ((cor == 'B' && y2-j == 1) || (cor == 'P' && y2-j == -1)) {
				if (T.vPecasComuns[x2][y2] == null && T.vPecasDamas[x2][y2] == null) {
					permissao = true;
				}
			}
		}
		
		else if ((Math.abs(x2-i) == 2) && (Math.abs(y2-j) == 2)) {
			if (T.ultimoJogador == cor && T.ultimoMovimento=="movimento")// o jogador não pode capturar se foi o último a jogar...
				return permissao;												//...e na rodada anterior apenas se moveu
			
			if (T.vPecasComuns[x2][y2] == null && T.vPecasDamas[x2][y2] == null) {
				if (T.vPecasComuns[(x2+i)/2][(y2+j)/2] != null) {
					permissao = (T.vPecasComuns[(x2+i)/2][(y2+j)/2].cor != cor) ? true : false;	
				}
				else if (T.vPecasDamas[(x2+i)/2][(y2+j)/2] != null) {
					permissao = (T.vPecasDamas[(x2+i)/2][(y2+j)/2].cor != cor) ? true : false;
				}
			}
		}
		
		return permissao;
	}
	
}