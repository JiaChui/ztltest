/* 

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.bind.issue
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.junit.Test
import org.openqa.selenium.Keys

/**
 * @author pao
 */
@Tags(tags = "zbind")
class Z60_B00657Test extends ZTL4ScalaTestCase {
  @Test
  def testIssue() = {
    val zul = """
      <include src="/bind/issue/B00657.zul"/>
"""

    runZTL(zul, () => {
      verifyEquals("0", jq("$listbox").toWidget().get("selectedIndex"))
      verifyEquals("0", jq("$intbox").toWidget().get("value"))
      val intbox = jq("$intbox")
      intbox.toElement().set("value", "")
      sendKeys(intbox, "1" + Keys.TAB)
      waitResponse()
      blur(intbox)
      waitResponse()
      verifyEquals("1", jq("$listbox").toWidget().get("selectedIndex"))
      verifyEquals("1", jq("$intbox").toWidget().get("value"))
      intbox.toElement().set("value", "")
      sendKeys(intbox, "2" + Keys.TAB)
      waitResponse()
      blur(intbox)
      waitResponse()
      verifyEquals("2", jq("$listbox").toWidget().get("selectedIndex"))
      verifyEquals("2", jq("$intbox").toWidget().get("value"))
    })
  }
}