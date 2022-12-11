package day5;

/*Both pepsi and kalimark buy cola from campacola

public class ClassesAndObjectsDemo1 {
	public static void main(String[] args) {
		Pepsi pepsiobj=new Pepsi();
		Kalimark kalimarkobj=new Kalimark();
		
		pepsiobj.makePepsiCola();
		kalimarkobj.makeBovontoCola();
	}
	
}

abstract class Cola {
	public abstract void makeCola();
}

class CampaCola extends Cola{

	@Override
	public void makeCola() {
		System.out.println("Cola made by campa cola...");
		
	}
	
}

class Pepsi{

	public void makePepsiCola() {
		Cola cola=new CampaCola();
		cola.makeCola();
		System.out.println("pepsi sells the campa cola in pepsi bottle....");
		
	}
	
}

class Kalimark{

	public void makeBovontoCola() {
		Cola cola=new CampaCola();
		cola.makeCola();
		System.out.println("kalimark sells the campa cola in bovonto bottle...");
		
	}
	
}*/

/*Kalimark has acquired campacola-leaing to a stronger association
public class ClassesAndObjectsDemo1 {
	public static void main(String[] args) {
		Pepsi pepsiobj=new Pepsi();
		Kalimark kalimarkobj=new Kalimark();
		
		pepsiobj.makePepsiCola();
		kalimarkobj.makeBovontoCola();
	}
	
}

abstract class Cola {
	public abstract void makeCola();
}



class Pepsi{

	public void makePepsiCola() {
		Cola cola=new Kalimark().new CampaCola();
		cola.makeCola();
		System.out.println("pepsi sells the campa cola in pepsi bottle....");
		
	}
	
}

class Kalimark{
//inner class can access properties of parent class directly but for accessing inner class properties,parent class should create object of inner class
	public void makeBovontoCola() {
		Cola cola=new CampaCola();
		cola.makeCola();
		System.out.println("kalimark sells the campa cola in bovonto bottle..."+new CampaCola().campamoney);
		
	}
	//inner class-class inside another class
	class CampaCola extends Cola{
		
		int campamoney=2000;
		@Override
		public void makeCola() {
			System.out.println("Cola made by campa cola..."+campamoney);
			
		}
		
	}
}*/

/*accessing a private method(campacola) inside kalimark which is of more stronger association using some other method
public class ClassesAndObjectsDemo1 {
	public static void main(String[] args) {
		Pepsi pepsiobj=new Pepsi();
		Kalimark kalimarkobj=new Kalimark();
		
		pepsiobj.makePepsiCola();
		kalimarkobj.makeBovontoCola();
	}
	
}

abstract class Cola {
	public abstract void makeCola();
}



class Pepsi{

	public void makePepsiCola() {
		Cola cola=new Kalimark().getTrojan();
		cola.makeCola();
		System.out.println("pepsi sells the campa cola in pepsi bottle....");
		
	}
	
}

class Kalimark{
//inner class can access properties of parent class directly but for accessing inner class properties,parent class should create object of inner class
	public void makeBovontoCola() {
		Cola cola=new CampaCola();
		cola.makeCola();
		System.out.println("kalimark sells the campa cola in bovonto bottle..."+new CampaCola().campamoney);
		
	}
	//inner class-class inside another class
	private class CampaCola extends Cola{
		
		int campamoney=2000;
		@Override
		public void makeCola() {
			System.out.println("Cola made by campa cola..."+campamoney);
			
		}
		
	}
	public Cola getTrojan()
	{
		return new CampaCola();
	}
}*/

/*To merge kalimark and campacola(so that no access is permitted to others),use anonymous inner class*/

public class ClassesAndObjectsDemo1 {
	public static void main(String[] args) {
		Pepsi pepsiobj=new Pepsi();
		Kalimark kalimarkobj=new Kalimark();
		
		pepsiobj.makePepsiCola();
		kalimarkobj.makeBovontoCola();
	}
	
}

abstract class Cola {
	public abstract void makeCola();
}



class Pepsi{

	public void makePepsiCola() {
//		Cola cola=new Kalimark().getTrojan();
//		cola.makeCola();
//		System.out.println("pepsi sells the campa cola in pepsi bottle....");
		
	}
	
}

class Kalimark{
//inner class can access properties of parent class directly but for accessing inner class properties,parent class should create object of inner class
	public void makeBovontoCola() {
		//anonymous inner class
		new Cola() {			
			@Override
			public void makeCola() {
				System.out.println("campa cola cola now belong to kalimark...its now kalimark cola...");
			}
		}.makeCola();
	}
}
//	public void makeBovontoCola() {
//		Cola cola=new CampaCola();
//		cola.makeCola();
//		System.out.println("kalimark sells the campa cola in bovonto bottle..."+new CampaCola().campamoney);
//		
//	}
	//inner class-class inside another class
//	private class CampaCola extends Cola{
//		
//		int campamoney=2000;
//		@Override
//		public void makeCola() {
//			System.out.println("Cola made by campa cola..."+campamoney);
//			
//		}
//		
//	}
//	public Cola getTrojan()
//	{
//		return new CampaCola();
//	}
