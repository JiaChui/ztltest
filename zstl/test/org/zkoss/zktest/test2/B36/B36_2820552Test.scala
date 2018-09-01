/* B36_2820552Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:01 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B36

;

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.unit.Widget


class B36_2820552Test extends ZTL4ScalaTestCase {
  @Test
  def testztl() = {
    var zscript =
      """
			


<zk>
After loading the page, you should not see any error.
<style dynamic="true">
	.cells td, .search .item td {
		border-bottom: 1px solid #BFDBF5;
	}
	.search .cell-inner {
		color: #2C559C; font: italic Tahoma, Arial, Helvetica, sans-serif;
	}
    .z-menubar-horizontal, .z-menubar-vertical{
        border-bottom:0 none;
        background: transparent none;
    }
</style>
<panel id="panel" framable="true" width="500px" height="400px"
	title="Panel"
	maximizable="true" minimizable="true" border="normal"
	collapsible="true" closable="true">
	<attribute name="onClose">alert("Panel is closed");</attribute>
	<attribute name="onMinimize">alert("Panel is minimized");</attribute>
<caption>test</caption>
	<toolbar>
		Search:
		<bandbox id="bd" autodrop="true" width="350px"
			onOK="search.model = strset.getSubModel(self.value, 30);">
			<bandpopup width="320px">
				<panel height="215px">
					<panelchildren>
						<zscript>							
			String[] data = new String[] {
				"Albert", "Bob", "Candy", "Elva", "Elva2", "Gaby", "Gavin", "Jason", "John", "Jean", "Janet", "Jamie", "Jessica", "Peter",
				"Rex", "Richard", "Sam", "Sidney", "Simon", "Sky", "Tony", "Vicky", "Winnie", "Wendy", "Zera", "Zenobia" };
			ListModel strset = new SimpleListModel(data);
						</zscript>
						<listbox class="listbox search" id="search"
							model="&#36;{strset}" height="100%"
							style="background:white;" oddRowSclass=""
							onSelect="bd.value=self.selectedItem.label; bd.closeDropdown();" />
					</panelchildren>
					<toolbar>
						<paging
							onCreate='search.setMold("paging");search.paginal=self;search.pageSize = 10;' />
					</toolbar>
				</panel>
			</bandpopup>
		</bandbox>
	</toolbar>
	<panelchildren>
		<columnlayout>
			<columnchildren width="100%">
				<panel height="260px">
					<panelchildren>
						<borderlayout>
						<center flex="true">
							<div id="viewer" style="overflow:auto;"/>
						</center>
						<south splittable="true" size="25%"  flex="true">
							<textbox id="text" multiline="true" width="98%" style="margin:0px" value="Hi, I am Jumper. (Please press the OK Button)"/>
						</south>
						</borderlayout>
					</panelchildren>
				</panel>
			</columnchildren>
			<columnchildren width="130px">
				<panel height="260px" width="100%">
					<panelchildren>
						<borderlayout width="100%" style="background:transparent">
							<north size="120px" border="none" style="background:transparent">
						<image src="/img/msn1.gif"/>	
							</north>
							<south size="120px"  border="none" style="background:transparent">
						<image src="/img/msn2.gif"/>
							</south>
							</borderlayout>
					</panelchildren>
				</panel>
			</columnchildren>
		</columnlayout>
	</panelchildren>
	<toolbar>
		<menubar id="menubar" width="200px">
			<menu label="Project" src="/img/Centigrade-Widget-Icons/Briefcase-16x16.png">
				<menupopup>
					<menuitem src="/img/Centigrade-Widget-Icons/BriefcaseSpark-16x16.png" label="New"
						onClick="alert(self.label)" />
					<menuitem src="/img/Centigrade-Widget-Icons/BriefcaseOpen-16x16.png" label="Open"
						onClick="alert(self.label)" />
					<menuitem src="/img/Centigrade-Widget-Icons/DisketteBlack-16x16.png" label="Save"
						onClick="alert(self.label)" />
					<menuseparator />
					<menuitem label="Exit" src="/img/Centigrade-Widget-Icons/DoorOpen-16x16.png" onClick="alert(self.label)" />
				</menupopup>
			</menu>
			<menu label="Help" src="/img/Centigrade-Widget-Icons/QuestionmarkButton-16x16.png">
				<menupopup>
					<menuitem label="Index" onClick="alert(self.label)" />
					<menu label="About">
						<menupopup>
							<menuitem label="About ZK"
								onClick="alert(self.label)" />
							<menuitem label="About Potix"
								onClick="alert(self.label)" />
						</menupopup>
					</menu>
				</menupopup>
			</menu>
			<menu src="/img/Centigrade-Widget-Icons/Spyglass-16x16.png">
				<menupopup>
					<menuitem label="Index" onClick="alert(self.label)" />
				</menupopup>
			</menu>
		</menubar>
	</toolbar>
	<toolbar mold="panel" align="center">
		<button label="OK" width="65px">
			<attribute name="onClick">
			if (text.value != "") {
				Div div = new Div();
				new Image("/img/m1.gif").setParent(div);
				new Label("Jumper@office says:").setParent(div);
				div.setParent(viewer);
				Label content = new Label(text.value);
				content.setStyle("padding-left:20px");
				content.setParent(viewer);
				text.value = "";
			}
			</attribute>
		</button>
		<button label="Canel" width="65px" onClick='text.value = ""'/>
	</toolbar>
</panel>
</zk>
			

		"""
    val ztl$engine = engine()
    val panel = ztl$engine.$f("panel")
    val bd = ztl$engine.$f("bd")
    val search = ztl$engine.$f("search")
    val viewer = ztl$engine.$f("viewer")
    val text = ztl$engine.$f("text")
    val menubar = ztl$engine.$f("menubar")
    runZTL(zscript, () => {
      verifyFalse(jq("@window").exists())
      verifyFalse(jq(".z-error").exists())
    })
  }
}



