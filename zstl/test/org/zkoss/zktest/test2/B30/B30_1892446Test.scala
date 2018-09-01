/* B30_1892446Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:01 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.{JQuery, Widget}
import org.zkoss.ztl._
import org.zkoss.ztl.unit._
import org.zkoss.ztl.annotation.Tags


class B30_1892446Test extends ZTL4ScalaTestCase {
  @Test
  def testztl() = {
    var zscript =
      """
			<window id="win" title="Dynamically Change by Model">
	<zscript>
		
		import org.zkoss.zktest.test2.tree.TreeModelA;
		//An ArrayList is created to be the root of tree
		ArrayList mother = new ArrayList();
		
		//Create a branch "child1" and assign children to it
		ArrayList child1 = new ArrayList();
		child1.add("Tommy");
		child1.add("Juile");
		
		//Create a branch "child2" and assign children to it
		ArrayList child2 = new ArrayList();
		child2.add("Gray");
		
		//Create a branch "grandchild" and assign children to it
		ArrayList grandChild = new ArrayList();
		grandChild.add("Paul");
		grandChild.add("Eric");
		
		//Assign branch "grandchild" to be branch "child2"'s child
		child2.add(grandChild);
		
		//Assign branch "grand2" to be branch "child1"'s child
		child1.add(child2);
		
		//Assign children to root "mother"
		mother.add("Andy");
		mother.add("Davis");
		mother.add(child1);
		mother.add("Matter");
		mother.add("Kitty");
		
		//TreeModelA class is contructed, only the root "mother" of tree is passed to its constructor.
		TreeModelA tma = new TreeModelA(mother);
		
		ArrayList childnew = new ArrayList();
		childnew.add("Clinton");
		childnew.add("Obama");

		
		public void replace(){
			Object[] data = {childnew};
			tma.set(mother,2,2,data);
		}
		
	</zscript>
		
		<vbox>
			Click on "Replace," if the 3rd item of both tree turns to [Clinton, Obama], it is correct.
			<tree model="${tma}" id="tree" >
		</tree>
		<hbox>
			
			<button label="Replace" onClick="replace();" />
		</hbox>
		</vbox>
		<zscript>
			Tree t2 = (Tree)tree.clone();
			t2.setId("tree2");
			t2.setParent(win);
		</zscript>
	</window>

		"""
    val ztl$engine = engine()
    val win = ztl$engine.$f("win")
    val tree = ztl$engine.$f("tree")
    val tree2 = ztl$engine.$f("tree2")
    runZTL(zscript, () => {
      for (i <- 0 until 2) {
        var jqTree: JQuery = jq("@tree").eq(i)
        verifyContains(jqTree.find("@treecell").eq(2).text(), "[Tommy, Juile, [Gray, [Paul, Eric]]]")
      }
      click(jq("@button"))
      waitResponse()
      for (i <- 0 until 2) {
        var jqTree: JQuery = jq("@tree").eq(i)
        verifyContains(jqTree.find("@treecell").eq(2).text(), "[Clinton, Obama]")
      }
    })
  }
}



