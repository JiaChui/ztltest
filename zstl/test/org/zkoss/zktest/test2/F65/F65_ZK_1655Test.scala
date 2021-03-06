package org.zkoss.zktest.test2.F65

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.zkoss.ztl.util.Scripts

@Tags(tags = "F65-ZK-1655.zul")
class F65_ZK_1655Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(() => {
      val yellow = jq(".z-div[style*=yellow]")
      getActions
        .moveToElement(findElement(yellow))
        .contextClick()
        .perform()
      waitResponse()
      val yellowPP = jq("@popup")
      // should see tooltip showed on 50px left of mouse pointer
      verifyTolerant(yellowPP.positionTop(), yellow.offsetTop() + 100, 1)
      verifyTolerant(yellowPP.positionLeft() + 50, yellow.offsetLeft() + 100, 1)

      val pink = jq(".z-div[style*=pink]")
      getActions
        .moveToElement(findElement(pink))
        .click()
        .perform()
      waitResponse()
      val pinkPP = jq("@popup")

      // should see tooltip showed on 20px down of mouse pointer
      verifyTolerant(pinkPP.offsetTop() - 20, pink.offsetTop() + 100, 3)
      verifyTolerant(pinkPP.offsetLeft(), pink.offsetLeft() + 100, 3)

      val cyan = jq(".z-div[style*=cyan]")
      Scripts.triggerMouseEventAt(getWebDriver(), cyan, "mouseover", "100,100")
      waitResponse()
      sleep(1000)
      val cyanPP = jq("@popup")

      // should see tooltip showed on 40px right of mouse pointer
      verifyTolerant(cyanPP.offsetTop(), cyan.offsetTop() + 100, 1)
      verifyTolerant(cyanPP.offsetLeft() - 40, cyan.offsetLeft() + 100, 1)
    })
  }
}
