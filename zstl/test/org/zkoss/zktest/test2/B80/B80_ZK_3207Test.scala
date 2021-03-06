package org.zkoss.zktest.test2.B80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.openqa.selenium.Keys
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement

@Tags(tags = "B80-ZK-3207.zul")
class B80_ZK_3207Test extends ZTL4ScalaTestCase {
  @Test
  def test() = {
    runZTL(
      () => {
        val h = jq(".z-groupbox").get(0).get("offsetHeight")
        
        click(jq(".z-button"))
        waitResponse()
        
        verifyEquals(h, jq(".z-groupbox").get(0).get("offsetHeight"))
    })
  }
}
