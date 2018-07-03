/* B85_ZK_3932.java

        Purpose:
        
        Description:
        
        History:
                Tue Jul 03 09:52:03 CST 2018, Created by charlesqiu

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B85

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase

class B85_ZK_3932Test extends ZTL4ScalaTestCase {
	
	@Test
	def test(): Unit = {
		runZTL(() => {
			click(jq(".z-button:eq(0)"))
			waitResponse()
			verifyFalse(jq(".z-log").exists())
			
			click(jq(".z-button:eq(1)"))
			waitResponse()
			verifyFalse(jq(".z-log").exists())
		})
	}
}
