/* B30_1893247Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:00 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B30_1893247Test extends ZTL4ScalaTestCase {
  @Test
  def test() = {
    var zscript =
      """
			<?init class="org.zkoss.zkplus.databind.AnnotateDataBinderInit" ?> 
			<window id="mainwin">
			<html><![CDATA[
			<p>You should see three rows with column titled  "row 1", "row 2", and "row 3" and each with same 
			contents of "grid content". If you did not see them, it is a bug!</p>
			]]></html>
			<zscript><![CDATA[
			
			public class Person {
				private String _name;
				
				public Person(String name) {
					super();
					_name = name;
				}
			
				public String getName() {
					return _name;
				}
			
				public void setName(String name) {
					_name = name;
				}
			}
			
			List persons = new ArrayList();
			persons.add(new Person("row 1"));
			persons.add(new Person("row 2"));
			persons.add(new Person("row 3"));
			
			]]>
			</zscript>
			<grid model="@{persons}">
				<columns>
					<column label="Name"/>
				</columns>
				<rows>
					<row self="@{each=q}">
						<groupbox>
							<grid>
								<columns>
									<column id="x" label="@{q.name}"/>
								</columns>
								<rows>
									<row><label value="grid content"/></row>
								</rows>
							</grid>
						</groupbox>
					</row>
				</rows>
			</grid>
			</window>
		"""
    val ztl$engine = engine()
    val mainwin = ztl$engine.$f("mainwin")
    val x = ztl$engine.$f("x")
    runZTL(zscript, () => {
      sleep(1000); //for DataBinding
      for (i <- 0 until 3) {
        var label = jq(".z-label:eq(" + i + ")")
        verifyEquals("grid content", label.text())
      }
    })
  }
}



