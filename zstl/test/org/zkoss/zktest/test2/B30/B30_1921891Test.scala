/* B30_1921891Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:01 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

;

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B30_1921891Test extends ZTL4ScalaTestCase {
  @Test
  def testztl() = {
    var zscript =
      """
			
<window>
Click <button label="move" onClick="lb.insertBefore(li, li)"/>
and nothing shall happens (correct).
In 3.0.3 or earlier, ClassCastException is thrown - it is incorrect.
	<listbox id="lb" multiple="true" width="300px">
		<listhead>
		<listheader label="Country/Area"/>
		<listheader align="right" label="Visits"/>
		<listheader align="right" label="%"/>
		</listhead>
		<listitem id="li">
		<listcell label="United States"/>
		<listcell label="5,093"/>
		<listcell label="19.39%"/>
		</listitem>
		<listfoot>
		<listfooter label="Total 132"/>
		<listfooter label="26,267"/>
		<listfooter label="100.00%"/>
		</listfoot>
	</listbox>
</window>

		"""
    val ztl$engine = engine()
    val lb = ztl$engine.$f("lb")
    val li = ztl$engine.$f("li")
    runZTL(zscript, () => {
      click(jq("@button"))
      waitResponse()
      verifyFalse(jq("@window[title=\"ZK Test\"]").exists())
    })
  }
}



