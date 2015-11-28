package testSuite;

import server.*;
import client.*;
import common.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests{

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for JSudoku");
		
		/* Test fuer Paket server */
		suite.addTestSuite(ServerTest.class);
		suite.addTestSuite(SpielTest.class);
		suite.addTestSuite(SpielerTest.class);		
		
		/* Test fuer Paket client */
		suite.addTestSuite(ClientTest.class);
		
		/* Test fuer Paket common */
		suite.addTestSuite(FeldTest.class);
		suite.addTestSuite(EinheitTest.class);
		suite.addTestSuite(KoordinateTest.class);
		suite.addTestSuite(SpielinformationTest.class);
		suite.addTestSuite(SpielstandTest.class);
		suite.addTestSuite(Spielfeld_AbstractTest.class);
		suite.addTestSuite(Spielfeld_StandardTest.class);
		suite.addTestSuite(Spielfeld_Fudschijma_2x3Test.class);
		suite.addTestSuite(Spielfeld_Fudschijma_4x3Test.class);
		suite.addTestSuite(Spielfeld_Fudschijma_4x4Test.class);
		suite.addTestSuite(Spielfeld_ComparisonTest.class);
		
		return suite;
	}

}
