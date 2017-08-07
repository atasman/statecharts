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
 * Unit TestCase for Choice
 */
@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(SExecInjectionProvider.class)
public class Choice extends AbstractExecutionFlowTest {
	@Before
	public void setup() throws Exception{
		ExecutionFlow flow = models.loadExecutionFlowFromResource("Choice.sct");
		initInterpreter(flow);
	}
	@Test
	public void elseChoiceUsingNonDefaultTransition() throws Exception {
		initForEventE(true);
		assertTrue(isStateActive("C"));
	}
	@Test
	public void elseChoiceUsingDefaultTransition() throws Exception {
		initForEventE(false);
		assertTrue(isStateActive("B"));
	}
	@Test
	public void defaultChoiceUsingNonDefaultTransition() throws Exception {
		initForEventG(true);
		assertTrue(isStateActive("C"));
	}
	@Test
	public void defaultChoiceUsingDefaultTransition() throws Exception {
		initForEventG(false);
		assertTrue(isStateActive("B"));
	}
	@Test
	public void uncheckedChoiceUsingNonDefaultTransition() throws Exception {
		initForEventF(true);
		assertTrue(isStateActive("C"));
	}
	@Test
	public void uncheckedChoiceUsingDefaultTransition() throws Exception {
		initForEventF(false);
		assertTrue(isStateActive("B"));
	}
	@Test
	public void alwaysTrueTransitionInChoice() throws Exception {
		initForEventH(true);
		assertTrue(isStateActive("C"));
	}
	public void initForEventE(boolean valueForC) throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		setBoolean("c", valueForC);
		raiseEvent("e");
		interpreter.runCycle();
	}
	public void initForEventF(boolean valueForC) throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		setBoolean("c", valueForC);
		raiseEvent("f");
		interpreter.runCycle();
	}
	public void initForEventG(boolean valueForC) throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		setBoolean("c", valueForC);
		raiseEvent("g");
		interpreter.runCycle();
	}
	public void initForEventH(boolean valueForC) throws Exception {
		interpreter.enter();
		assertTrue(isStateActive("A"));
		setBoolean("c", valueForC);
		raiseEvent("h");
		interpreter.runCycle();
	}
}