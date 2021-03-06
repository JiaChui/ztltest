package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2775.zul")
class B70_ZK_2776Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  runZTL(() => {
      val grid = jq("@grid")
      val firstColumn = grid.find(".z-column").first
      mouseOver(firstColumn)
      waitResponse()
      val column_button = firstColumn.toWidget.$n("btn")
      click(column_button)
      waitResponse()
      click(jq(".z-menupopup-open .z-menupopup-content .z-menuitem-checkable").get(3))
      waitResponse()
      frozenScroll(grid, 2860)
      waitResponse()
      frozenScroll(grid, -2860)
      verifyEquals(jq("@grid .z-column:first").outerWidth(), jq("@grid .z-rows .z-row:first .z-cell:first").outerWidth())
      verifyEquals(jq("@grid .z-column:eq(1)").outerWidth(), jq("@grid .z-rows .z-row:first .z-cell:eq(1)").outerWidth())
      verifyEquals(jq("@grid .z-column:eq(2)").outerWidth(), jq("@grid .z-rows .z-row:first .z-cell:eq(2)").outerWidth())
      verifyEquals(jq("@grid .z-column:eq(4)").outerWidth(), jq("@grid .z-rows .z-row:first .z-cell:eq(4)").outerWidth())
    })
  }
}