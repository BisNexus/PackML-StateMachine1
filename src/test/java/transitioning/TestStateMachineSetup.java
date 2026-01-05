package transitioning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import statemachine.Isa88StateMachine;
import statemachine.StateMachineBuilder;
import states.ActiveStateName;
import states.IStateAction;
public class TestStateMachineSetup {

	int dummyActionTime = 500;
	IStateAction dummyAction = new DummyAction(dummyActionTime);

	@BeforeEach
	void printTestName(TestInfo testInfo) {
		System.out.println("Starting test: " + testInfo.getDisplayName());
	}

	@AfterEach
	void checkTestCompletion(TestInfo testInfo) {
		System.out.println("Completed test: " + testInfo.getDisplayName());
	}

	@ParameterizedTest
	@EnumSource(ActiveStateName.class)
	void testActionSetup(ActiveStateName stateName) {
		Isa88StateMachine stateMachine = new StateMachineBuilder().withAction(dummyAction, stateName).build();
		IStateAction action = stateMachine.getStateActionManager().getAction(stateName);
		assertEquals(action, dummyAction, "dummyAction should be added to state action");
	}

}
