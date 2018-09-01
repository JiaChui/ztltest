/* B30_2140491Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:00 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

;

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B30_2140491Test extends ZTL4ScalaTestCase {
  @Test
  def testBinding() = {
    var zscript =
      """
			<?init class="org.zkoss.zkplus.databind.AnnotateDataBinderInit" ?>
			<window id="window" width="100%" height="100%">
			<html>
			<![CDATA[
			<ol>
			<li>select "minny" from the first listbox.</li>
			<li>You shall see the "minny" and "2" appear on the bottom beside "save" button.</li> 
			<li>Select the listbox by the "save" button from "2" to "5".</li> 
			<li>You shall not see any change in the first listbox.</li>
			<li>Now click the "save" button.</li>
			<li>You shall see on the first listbox the second column of "minny" changes from "2" to "5".</li>
			<li>Done</li>
			</ol>
			]]>
			</html>
					<zscript>
					<![CDATA[
						import java.util.*;
						
						class Foo{
							String bar,item;
							
							public Foo(){
							}
							
							public Foo(String bar,String item){
								this.bar = bar;
								this.item = item;
							}
							
							public void setBar(String s){
								bar = s;
							}
							
							public String getBar(){
								return bar;
							}
							
							public void setItem(String s){
								item = s;
							}
							
							public String getItem(){
								return item;
							}
							
							public String toString(){
								return "bar :" + bar + "  item:"+item;
							}
						}
						
						List fooList = new ArrayList();
						fooList.add(new Foo("goofy","1"));
						fooList.add(new Foo("minny","2"));
						fooList.add(new Foo("pluto","3"));
						
						List itemsList = new ArrayList();
						itemsList.add("1");
						itemsList.add("2");
						itemsList.add("3");
						itemsList.add("4");
						itemsList.add("5");
						itemsList.add("6");
						itemsList.add("7");
						
					]]>
					</zscript>
					<listbox id="listbox"  model="@{fooList}"  height="200px" selectedItem="@{selectedFoo}">
						<listhead sizable="true">
								<listheader label="bar"/>
								<listheader label="item"/>
							</listhead>
							<listitem self="@{each=str}" value="@{str}">
								<listcell label="@{str.bar}"/>
								<listcell label="@{str.item}"/>
							</listitem>
					</listbox>
					<textbox id="textboxDesc" value="@{selectedFoo.bar,save-when='saveButton.onClick'}"/>
					<listbox id="currentListbox" model="@{itemsList}" selectedItem="@{selectedFoo.item,save-when='saveButton.onClick'}" mold="select">
						<listitem self="@{each=is}" value="@{is}" label="@{is}"></listitem>
					</listbox>
					<button id="saveButton" label="save"></button>
			</window>
		"""
    val ztl$engine = engine()
    val window = ztl$engine.$f("window")
    val listbox = ztl$engine.$f("listbox")
    val textboxDesc = ztl$engine.$f("textboxDesc")
    val currentListbox = ztl$engine.$f("currentListbox")
    val saveButton = ztl$engine.$f("saveButton")
    runZTL(zscript, () => {
      sleep(1000); //for DataBinding
      click(jq(listbox).find(".z-listitem:eq(1)"))
      waitResponse()
      // First validation
      verifyEquals("minny", jq(textboxDesc).`val`())
      select(currentListbox, "5")
      waitResponse()
      verifyEquals("minny2", jq(listbox).find(".z-listitem:eq(1)").text())
      // Second validation
      click(saveButton)
      waitResponse()
      verifyEquals("minny5", jq(listbox).find(".z-listitem:eq(1)").text())
    })
  }
}



