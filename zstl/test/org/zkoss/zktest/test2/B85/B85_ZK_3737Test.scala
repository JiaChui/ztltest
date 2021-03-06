/* B85_ZK_3737Test.scala

	Purpose:

	Description:

	History:
		Wed, Nov 1, 2017 12:33:17 PM, Created by bobpeng

Copyright (C)  Potix Corporation. All Rights Reserved.

This program is distributed under LGPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.zktest.test2.B85

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags;

/**
  *
  * @author bobpeng
  */
@Tags(tags = "")
class B85_ZK_3737Test extends ZTL4ScalaTestCase {
  @Test
  def test() = {
    runZTL(() => {
      for (i <- 0 to 1) {
        var width = jq(".z-listheader:eq(" + i + ")").outerWidth();
        verifyEquals(width, jq(".z-listcell:eq(" + i + ")").outerWidth())
        verifyEquals(width, jq(".z-column:eq(" + i + ")").outerWidth())
        verifyEquals(width, jq(".z-row-inner:eq(" + i + ")").outerWidth())
        verifyEquals(width, jq(".z-treecol:eq(" + i + ")").outerWidth())
        verifyEquals(width, jq(".z-treecell:eq(" + i + ")").outerWidth())
      }
    })
  }
}
