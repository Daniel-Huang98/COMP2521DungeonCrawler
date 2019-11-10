package compositecheck;

public interface CompositeCheck {
	boolean check();
	void addCheck(CompositeCheck obj);
}
