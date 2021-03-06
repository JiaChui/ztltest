package org.zkoss.zktest.test2.B60

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import org.zkoss.ztl.ZK

@Tags(tags = "B60-ZK-1222.zul")
class B60_ZK_1222Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(() => {
        mouseOver(jq(".z-listitem:contains(Mouse hover - tooltip)"))
        sleep(1000)
        verifyTrue("should see a tooltip loading for about 2 seconds.", jq(".z-popup").exists())
        verifyTrue("should see a tooltip loading for about 2 seconds.", jq(".z-apply-mask").exists())
        sleep(500)
        verifyTrue("If you see the tooltip appearing and disappearing several times while it is loading, it is an error.", jq(".z-popup").exists())
        sleep(1500)
        verifyTrue(!jq(".z-apply-mask").exists())
        click(jq(".z-listitem:contains(Click - popup)"))
        waitResponse()
        sleep(2000)
        verifyTrue("Then, the tooltip should keep displaying until the mouse cursor is click outside the second data row.", isVisible(jq(".z-popup")))
        clickAt(jq(".z-listhead"), "3,3")
        waitResponse()
        sleep(2000)
        verifyTrue("Then, the tooltip should keep displaying until the mouse cursor is click outside the second data row.", !jq(".z-popup").isVisible())
      })

  }
}
