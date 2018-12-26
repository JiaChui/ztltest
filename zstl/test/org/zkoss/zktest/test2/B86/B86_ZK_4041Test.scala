/* B86_ZK_4041Test.java

        Purpose:

        Description:

        History:
                Wed Dec 26 16:47:35 CST 2018, Created by charlesqiu

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B86

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase

class B86_ZK_4041Test extends ZTL4ScalaTestCase {
	@Test
	def test() = {
		runZTL(() => {
			val menu = jq(".z-menu")
			click(menu)
			waitResponse()
			verifyTrue(getElementPositionTop(jq(".z-menupopup-open")).intValue() > getElementPositionTop(menu).intValue())

			click(jq(".z-button"))
			waitResponse()
			click(menu)
			waitResponse()
			val menupopup = jq(".z-menupopup-open")
			verifyTrue(getElementPositionLeft(menupopup).intValue() < getElementPositionLeft(menu).intValue())

			click(menupopup.find(".z-menu"))
			waitResponse()
			verifyTrue(getElementPositionLeft(menupopup).intValue() < getElementPositionLeft(jq(".z-menupopup-open:eq(1)")).intValue())
		})
	}
}
