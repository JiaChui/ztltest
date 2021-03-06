package org.zkoss.zktest.test2.B80

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.zkoss.ztl.util.ConfigHelper

@Tags(tags = "B80-ZK-2598.zul")
class B80_ZK_2598Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  runZTL(() => {
      var btns = jq(".z-button").iterator();
      click(jq(".z-button").eq(0));
      waitResponse();
      var result = jq(".z-messagebox-window .z-label").text();
      click(jq(".z-messagebox-button"));
      waitResponse()
      while (btns.hasNext()) {
        var btn = btns.next();
        click(btn);
        waitResponse();
        verifyTrue(jq(".z-messagebox-button").exists());
        verifyEquals(jq(".z-messagebox-window .z-label").text(), result);
        click(jq(".z-messagebox-button"));
        waitResponse();
      }
    })
    
  }
}