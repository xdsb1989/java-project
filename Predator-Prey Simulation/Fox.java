public class Fox extends Living {

	@Override
	Living next(Neighbors n) {
		// TODO Auto-generated method stub
		int rabbit=0;
		int fox=0;
		for (int i=0;i<=7;i++){
			if (n.array[i] == 'R')
				rabbit++;
			else if (n.array[i] == 'X')
				fox++;
		}
		if (rabbit == 0 || fox > 5){
			return new Empty();
		}
		
		else
			return new Fox();
	}

	char toChar() {
		// TODO Auto-generated method stub
		return 'X';
	}
    
}
