package bankcli;

import org.junit.Test;

import com.bank.cli.util.CliEntry;

public class CliEntryTest {

	@Test
	public void testMain() {
		String arrayVals[] = { "login Harry", "topup 100", "login Tom", "topup 80", "pay Harry 50", 
				"pay Harry 100" ,"topup 30" ,"login Harry" ,"pay Tom 30"   ,"login Tom"   ,"topup 100"};
		int arrLenght = arrayVals.length;
		for (int i = 0; i < arrLenght; i++) {
			CliEntry.callService(getArrVal(arrayVals[i]));
		}
	}

	private String[] getArrVal(String inputVal) {
		return inputVal.split(" ");
	}

}
