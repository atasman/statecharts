/* Generated by YAKINDU Statechart Tools code generator. */
package org.yakindu.sct.simulation.core.sexec.test;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sexec.interpreter.test.util.AbstractExecutionFlowTest;
import org.yakindu.sct.model.sexec.interpreter.test.util.SExecInjectionProvider;
import org.yakindu.sct.test.models.SCTUnitTestModels;
import com.google.inject.Inject;
import static org.junit.Assert.assertTrue;
/**
 * Unit TestCase for ExitState
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class ExitState extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception{
		ExecutionFlow flow = models.loadExecutionFlowFromResource("ExitState.sct");
		initInterpreter(flow);
	}
	@Test
	public void defaultExit() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		raiseEvent("e");
		interpreter.runCycle();
		assertTrue(isStateActive("E"));
	}
	@Test
	public void namedExitThroughNamedTransition() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		raiseEvent("f");
		interpreter.runCycle();
		assertTrue(isStateActive("F"));
	}
	@Test
	public void namedExitThroughDefaultTransition() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		raiseEvent("g");
		interpreter.runCycle();
		assertTrue(isStateActive("E"));
	}
	@Test
	public void remainInA() throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		interpreter.runCycle();
		assertTrue(isStateActive("A"));
	}
}
