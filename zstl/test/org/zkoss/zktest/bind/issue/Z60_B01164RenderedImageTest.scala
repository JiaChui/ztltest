

/* Z60_B01164RenderedImageTest.scala

	Purpose:
		
	Description:
		
	History:
		Jul 2, 2012 Created by Pao Wang

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.bind.issue
import org.junit.Assert
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

/**
 * @author pao
 */
@Tags(tags = "zbind")
class Z60_B01164RenderedImageTest extends ZTL4ScalaTestCase {

  def testArg() = {
    val zul = """
      <include src="/bind/issue/B01164ImageContentType.zul"/>
"""
    runZTL(zul, () => {

      var img = jq("$img")
      verifyTrue(img.exists())

    })
  }
}