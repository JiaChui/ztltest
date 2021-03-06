
package org.zkoss.zktest.test2.Z65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase

@Tags(tags = "Z65-Flex-081.zul,Flex")
class Z65_Flex_081Test extends ZTL4ScalaTestCase {

def testClick() = {
  val zscript = """<zk><hbox>
    <window border="normal" height="360px"
        title="Input HFlex Case: [Datebox]" width="480px">
        <datebox hflex="1"/>
    </window>
    <window border="normal" height="360px"
        title="Input HFlex Case: [Datebox, rounded]" width="480px">
        <datebox hflex="1" mold="rounded"/>
    </window>
</hbox>
</zk>"""  
  runZTL(zscript,
    () => {
      verifyImage()
    })
    
  }
}