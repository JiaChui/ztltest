/* B30_1914104_2Test.scala

	Purpose:
		
	Description:
		
	History:
		Tue Oct 11 18:45:05 TST 2011, Created by jumperchen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.test2.B30

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags;

/**
  *
  * @author jumperchen
  */
@Tags(tags = "B30-1914104-2.zul,C,Window,IE")
class B30_1914104_2Test extends ZTL4ScalaTestCase {
  def testCase() = {
    runZTL(() => {
      val width = jq("@window").outerWidth()
      val height = jq("@window").outerHeight()
      windowResizeTo(400, 800);
      waitResponse();
      verifyEquals(width, jq("@window").outerWidth());
      verifyNotEquals(height, jq("@window").outerHeight());
    })
  }
}
