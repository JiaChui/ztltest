/* Z60_Radiogroup_ListModelArrayTest.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Jan 19 16:58:43 CST 2012 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.Z60

import org.zkoss.zstl.ZTL4ScalaTestCase
import scala.collection.JavaConversions._
import org.junit.Test;
import org.zkoss.ztl.Element;
import org.zkoss.ztl.JQuery;
import org.zkoss.ztl.Tags;
import org.zkoss.ztl.util.Scripts;
import org.zkoss.ztl.Widget;
import org.zkoss.ztl.ClientWidget;
import org.zkoss.ztl.ZK;
import org.zkoss.ztl.ZKClientTestCase;
import java.lang._

/**
 * A test class for bug Radiogroup-ListModelArray
 * @author benbai
 *
 */
@Tags(tags = "Z60-Radiogroup-ListModelArray.zul,")
class Z60_Radiogroup_ListModelArrayTest extends ZTL4ScalaTestCase {
	
  def testClick() = {
    val zscript = """
			<zk>
				<zscript><![CDATA[
					import org.zkoss.zktest.test2.select.models.*;
					
					ListModelArray model = ListModelArrays.getModel(ListModelArrays.DEFAULT,20);
					ListModelArray model2 = ListModelArrays.getModel(ListModelArrays.CLONEABLE,20);
					
					int cnt = 0;
					int elemcnt = 0;
				]]></zscript>
				<div>
					<div>1. There are 3 radiogroups below.</div>
					<div>2. For first two radiogroups, their select status will sync automatically after you select item.</div>
					<div>3. Select data 10 of third radiogroups.</div>
					<div>4. Click clone and 'clone by serialization', then two radiogroups should be created and also select data 10.</div>
					<div>5. Select data 11 of third radiogroups, data 12 of fourth and data 13 of fifth, the select status of last three radiogroups should not sync.</div>
					<div>6. Click clone and 'clone by serialization', you should see two radiogroups created and each radiogroups after fifth radiogroups select data 11.</div>
					<div>7. You can only select one of third/serialized radiogroup at the same time.</div>
				</div>
				<radiogroup id="cbxOne" model="${model}" onCheck="" />
				<div height="10px"></div>
				<radiogroup id="cbxTwo" model="${model}" onCheck=""/>
				<div height="10px"></div>
				<radiogroup id="cbxThree" model="${model2}" onCheck="" />
				<separator />
				<button id="clone" label="clone">
					<attribute name="onClick">
						Radiogroup cbx = cbxThree.clone();
						cbx.setId("cbxThree_clone" + cnt++);
						cbx.setParent(cloneThreeArea);
					</attribute>
				</button>
				<button id="serialize" label="Clone by Serialization">
					<attribute name="onClick"><![CDATA[{
						import java.io.*;
						ByteArrayOutputStream boa = new ByteArrayOutputStream();
						new ObjectOutputStream(boa).writeObject(cbxThree);
						byte[] bs = boa.toByteArray();
						Object n = new ObjectInputStream(new ByteArrayInputStream(bs)).readObject();
						n.setId("cbxThree_serialize" + cnt++);
						((Component)n).setParent(cloneThreeArea);
					}]]></attribute>
				</button>
				<vlayout id="cloneThreeArea" />
			</zk>

    """
runZTL(zscript,
        () => {
        var clone: Widget = engine.$f("clone");
        var serialize: Widget = engine.$f("serialize");
        var radioClass: String = "z-radio";

        def select(id: String, num: Int) = {
          var cbx: Widget = engine.$f(id);
          clickAndWait(jq(jq(cbx.$n()).find("."+radioClass).get(num)).find("input").get(0), 0);
        }
        def isSelect(id: String, num: Int): Boolean = {
          var cbx: Widget = engine.$f(id);
          return "true".equals(jq(jq(cbx.$n()).find("."+radioClass).get(num)).find("input").get(0).get("checked"));
        }
        def getCheckedItem (id: String): String = {
          var cbx: Widget = engine.$f(id);
          var radios: JQuery = jq(cbx.$n()).find("."+radioClass);
          var total: Int = radios.length();
          var radio: JQuery = null;

          for (i <- 0 to total-1) {
            radio = jq(radios.get(i));
            if ("true".equals(radio.find("input").get(0).get("checked")))
              return radio.find("label").get(0).get("innerHTML");
          }
          return null;
        }
        def clickAndWait(target: ClientWidget, time: Int) {
          click(target);
          if (time > 0)
            sleep(time);
          else
            waitResponse();
        }
        select("cbxOne", 0);
        verifyTrue("The select of first two radiogroup should sync",
            getCheckedItem("cbxOne").equals(getCheckedItem("cbxTwo")));
        select("cbxTwo", 15);
        verifyTrue("The select of first two radiogroup should sync",
            getCheckedItem("cbxOne").equals(getCheckedItem("cbxTwo")));

        select("cbxThree", 10);
        clickAndWait(clone, 0);
        clickAndWait(serialize, 0);

        verifyTrue("cloned radiogroup should select data 10",
            isSelect("cbxThree_clone0", 10));
        verifyTrue("serialized radiogroup should select data 10",
            isSelect("cbxThree_serialize1", 10));

        select("cbxThree", 11);
        verifyTrue("The select of third and cloned radiogroup should not sync",
            isSelect("cbxThree_clone0", 10));
        verifyTrue("serialized radiogroup should be cleared after third radiogroup selected",
            getCheckedItem("cbxThree_serialize1") == null);

        select("cbxThree_clone0", 12);
        verifyTrue("The select of third and cloned radiogroup should not sync",
            isSelect("cbxThree", 11));

        select("cbxThree_serialize1", 13);
        verifyTrue("The select of cloned and serialized radiogroup should not sync",
            isSelect("cbxThree_clone0", 12));
        verifyTrue("third radiogroup should be cleared after serialized radiogroup selected",
            getCheckedItem("cbxThree") == null);

        clickAndWait(clone, 0);
        clickAndWait(serialize, 0);
        verifyTrue("cloned radiogroup should select data 11",
            isSelect("cbxThree_clone2", 11));
        verifyTrue("serialized radiogroup should select data 11",
            isSelect("cbxThree_serialize3", 11));
    }
   );
  }
}