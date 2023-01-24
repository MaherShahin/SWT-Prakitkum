package mocks;

public class Subject {
	private Observer obs;
	
	public Subject() {
		super();
	};
	
	public void attach(Observer ob) {
		obs = ob;
	}
	
	public void doSomething() {
		obs.update("Something");
	}

}
