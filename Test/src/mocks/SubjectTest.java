package mocks;

import org.junit.Test;

import junit.framework.TestCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SubjectTest extends TestCase {

	@Test
	public final void testUpdateIsCalledOnce() {

		Observer observer = mock(Observer.class);

		Subject subject = new Subject();
		subject.attach(observer);

		subject.doSomething();

		verify(observer, times(1)).update("Something");

	}

}
