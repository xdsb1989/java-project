
public class Rabbit extends Living{

	@Override
	Living next(Neighbors n) {
		// TODO Auto-generated method stub
		int rabbit=0;
		int grass=0;
		int fox = 0;
		for (int i=0;i<=7;i++){
			if (n.array[i] == 'R')
				rabbit++;
			else if (n.array[i] == 'X')
				fox++;
			else if (n.array[i] == 'G')
				grass++;
		}
		if (grass == 0 || fox>=rabbit+1){
			return new Empty();
		}
		
		else
			return new Rabbit();
		
	}

	@Override
	char toChar() {
		// TODO Auto-generated method stub
		return 'R';
	}
    
}
