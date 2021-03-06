package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test
import java.awt.event.KeyEvent
import org.openqa.selenium.Keys
import org.openqa.selenium.Dimension

@Tags(tags = "B70-ZK-2307.zul")
class B70_ZK_2307Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    runZTL(() => {
    	// don't support opera
        val window = driver.manage().window();
        val originW = window.getSize().width;
        val originH = window.getSize().height;
        window.setSize(new Dimension(500, originH));
        
        sleep(500);
        
        val h5l = jq("$h5").offsetLeft();
        val i5l = jq("$i5").offsetLeft();
        
        verifyTrue("list head and cell must align to same vertial line.", h5l == i5l);

        window.setSize(new Dimension(originW, originH));
        //window.maximize();
      })

  }
}