/* B50_ZK_835Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed Mar 14 16:01:14 CST 2012 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.B50

import org.zkoss.zstl.ZTL4ScalaTestCase
import scala.collection.JavaConversions._
import org.junit.Test;
import org.zkoss.ztl.Element;
import org.zkoss.ztl.JQuery;
import org.zkoss.ztl.Tags;
import org.zkoss.ztl.util.Scripts;
import org.zkoss.ztl.Widget;
import org.zkoss.ztl.ZK;
import org.zkoss.ztl.ZKClientTestCase;
import java.lang._

/**
 * A test class for bug ZK-835
 * @author benbai
 *
 */
@Tags(tags = "B50-ZK-835.zul,")
class B50_ZK_835Test extends ZTL4ScalaTestCase {
	
  def testClick() = {
    val zscript = """
			<zk xmlns:w="client">
				<h:pre xmlns:h="xhtml">
					(All browsers EXCEPT IE8)
					See the zk log , there should be only one "bind" log for listbox/grid/tree , 
					not "bind, unbind,bind " log.
				</h:pre>
				<zscript><![CDATA[
				    import java.util.*;
				    import org.zkoss.zul.*;
				    import org.zkoss.zk.ui.event.*;
				    
					List list = new ArrayList();
					for (int i = 0; i < 50; i++) {
						List list2 = new ArrayList();
						list2.add(new DefaultTreeNode("item " + i,(List) null));
						list.add(new DefaultTreeNode("item " + i,(List)list2) );
					}
					DefaultTreeModel model =  
						new DefaultTreeModel(new DefaultTreeNode(null, list));
					
					TreeitemRenderer render = new TreeitemRenderer() {
						public void render(Treeitem item, Object data) throws Exception {
							Treerow tr;
							if (item.getTreerow() == null) {
								tr = new Treerow();
								tr.setParent(item);
							} else {
								tr = item.getTreerow();
								tr.getChildren().clear();
							}
							tr.appendChild(new Treecell((String) ((DefaultTreeNode) data).getData()));
							item.setValue(data);
							
							item.addEventListener("onOpen",new org.zkoss.zk.ui.event.EventListener(){
								public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
									event.stopPropagation();
								}
							});
						}
    					public void render(Treeitem item, Object data, int index) throws Exception {
							Treerow tr;
							if (item.getTreerow() == null) {
								tr = new Treerow();
								tr.setParent(item);
							} else {
								tr = item.getTreerow();
								tr.getChildren().clear();
							}
							tr.appendChild(new Treecell((String) ((DefaultTreeNode) data).getData()));
							item.setValue(data);
							
							item.addEventListener("onOpen",new org.zkoss.zk.ui.event.EventListener(){
								public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
									event.stopPropagation();
								}
							});
						}
					};
				]]></zscript>
				
				<tree id="tree" model="${model}" itemRenderer="${render }" mold="paging" pageSize="5">
					<attribute w:name="bind_"><![CDATA[
					function(){
						zk.log("tree:bind");
						this.$bind_.apply(this,arguments);
					}
					]]></attribute>
					<attribute w:name="unbind_"><![CDATA[	
					  function(){
						zk.log("tree:unbind");
						this.$unbind_.apply(this,arguments);
					  }
					]]></attribute>		
					<treecols>
						<treecol label="col"/>
					</treecols>
				</tree>
				<listbox id="lb"  mold="paging" pageSize="5">
					<attribute w:name="bind_"><![CDATA[
					function(){
						zk.log("listbox:bind");
						this.$bind_.apply(this,arguments);
					}
					]]></attribute>
					<attribute w:name="unbind_"><![CDATA[	
					  function(){
						zk.log("listbox:unbind");
						this.$unbind_.apply(this,arguments);
					  }
					]]></attribute>		
					<listhead sizable="true">
						<listheader label="1" />
						<listheader label="2" />
						<listheader label="3" />
						<listheader label="4" />
						<listheader label="5" />
						<listheader label="6" />
						<listheader label="7" />
						<listheader label="8" />
					</listhead>
					<listitem>
						<listcell label="1" />
						<listcell label="2" />
						<listcell label="3" />
						<listcell label="4" />
						<listcell label="5" />
						<listcell label="6" />
						<listcell label="7" />
						<listcell label="8" />
					</listitem>
					<listitem>
						<listcell label="1" />
						<listcell label="2" />
						<listcell label="3" />
						<listcell label="4" />
						<listcell label="5" />
						<listcell label="6" />
						<listcell label="7" />
						<listcell label="8" />
					</listitem>
					<listitem>
						<listcell label="1" />
						<listcell label="2" />
						<listcell label="3" />
						<listcell label="4" />
						<listcell label="5" />
						<listcell label="6" />
						<listcell label="7" />
						<listcell label="8" />
					</listitem>
				</listbox>
				<grid id="g" width="500px" mold="paging" pageSize="5">
					<attribute w:name="bind_"><![CDATA[
					function(){
						zk.log("grid:bind");
						this.$bind_.apply(this,arguments);
					}
					]]></attribute>
					<attribute w:name="unbind_"><![CDATA[	
					  function(){
						zk.log("grid:unbind");
						this.$unbind_.apply(this,arguments);
					  }
					]]></attribute>		
					<columns id="cs" sizable="true" onColSize='lab.value=new java.util.Date()+"col size change:"+event+",colindex:"+event.getColIndex()'>
						<column label="AA" id="col1" onClick='alert("Click on column 1")'/>
						<column label="BB" id="col2" onRightClick='alert("onRightClick on column 2")'/>
						<column label="CC" id="col3" onDoubleClick='alert("onDoubleClick on column 3")'/>
						<column label="DD" id="col4" onClick='alert("Click on column 4")' onRightClick='alert("onRightClick on column 4")' onDoubleClick='alert("onDoubleClick on column 4")'/>
						<column label="EE" id="col5" />
					</columns>
					<rows>
						<row>
							<label value="AA01" />
							<label value="BB01" />
							<label value="CC01" />
							<label value="DD01" />
							<label value="EE01" />
						</row>
						<row>
							<label value="AA02" />
							<label value="BB02" />
							<label value="CC02" />
							<label value="DD02" />
							<label value="EE02" />
						</row>
					</rows>
				</grid>	
			</zk>

    """

   runZTL(zscript,
        () => {
        var log: Element = jq("textarea").get(0);
        // wait the zk.log
        sleep(500);
        verifyTrue("Should only three log message",
            log.get("value").length() < 37);
    }
   );
  }
}