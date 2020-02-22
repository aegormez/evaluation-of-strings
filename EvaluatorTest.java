import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvaluatorTest {
	StringEvaluator EvaluatorInstance= new StringEvaluator();
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		//EvaluatorInstance=null;
	}

	@Test
	void test() {
		assertEquals("8",EvaluatorInstance.calculate("3 + 5"));
	}
	@Test
	void test2() {
		assertEquals("16.2",EvaluatorInstance.calculate("21-12*2/5"));
	}
	@Test
	void test3() {
		assertEquals("1.0E9",EvaluatorInstance.calculate("100*10000000"));
	}
	@Test
	void test4() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*E"));
	}
	@Test
	void test5() {
		assertEquals("Invalid String",EvaluatorInstance.calculate("4+3*?"));
	}
}
