public class Grass extends Living{

	@Override
	Living next(Neighbors n) {
		// TODO Auto-generated method stub
		int rabbit=0;
		int grass=0;
		for (int i=0;i<=7;i++){
			if (n.array[i] == 'R')
				rabbit++;
			else if (n.array[i] == 'G')
				grass++;
		}
		if (grass > 2*rabbit)
			return new Rabbit();
		else if (grass > rabbit)
			return new Grass();
		else
			return new Empty();
	}

	@Override
	char toChar() {
		// TODO Auto-generated method stub
		return 'G';
	}
    
}
