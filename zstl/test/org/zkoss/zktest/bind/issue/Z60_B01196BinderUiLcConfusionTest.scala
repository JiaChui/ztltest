/* Z60_B01196BinderUiLcConfusionTest.scala

	Purpose:
		
	Description:
		
	History:
		Nov 6, 2012 Created by Pao Wang

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.bind.issue
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.junit.Assert

/**
 * @author pao
 */
@Tags(tags = "zbind")
class Z60_B01196BinderUiLcConfusionTest extends ZTL4ScalaTestCase {

  def testArg() = {
    val zul = """
      <include src="/bind/issue/B01196BinderUiLcConfusion.zul"/>
"""
    runZTL(zul, () => {

      var listItems = jq("@listbox").find("@listitem")
      verifyEquals(5, listItems.length())

    })
  }
}

