/* B30_2129992Test.java

	Purpose:
		
	Description:
		
	History:
		May, 30, 2018 18:42:01 PM

Copyright (C) 2018 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.test2.B30

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Widget


class B30_2129992Test extends ZTL4ScalaTestCase {
  @Test
  def testBinding() = {
    var zscript =
      """
			<?init class="org.zkoss.zkplus.databind.AnnotateDataBinderInit"?>
			
			<window width="500px">
			<html>
			<attribute name="content">
			If you see a listbox with four rows showing Tom0 Hanks0 ~ Tom3 Hanks3 then it is correct.
			</attribute>
			</html>
			<zscript>
			<![CDATA[
			  //@IMPORT
			  import org.zkoss.zkplus.databind.TypeConverter;
			]]>
			<![CDATA[ //@DECLARATION
			     class Person {
			   	    private String _firstName="";
			   	    private String _lastName="";
			   	    
			   	    //getter and setters
			   	    public void setFirstName(String firstName) {
			   	        _firstName = firstName;
			   	    }
			   	    public String getFirstName() {
			   	        return _firstName;
			   	    }
			   	    public void setLastName(String lastName) {
			   	        _lastName = lastName;
			   	    }
			   	    public String getLastName() {
			   	        return _lastName;
			   	    }
			   	    public void setFullName(String f) {
			   	        //do nothing.
			   	    }
			   	    public String getFullName() {
			   	        return _firstName + " " + _lastName;
			   	    }
			   	}
			
			   public class myTypeConverter implements TypeConverter{
			       	public Object coerceToBean(java.lang.Object val, org.zkoss.zk.ui.Component comp){
						return ((ListModelList)val).getInnerList();
					}
					public Object coerceToUi(java.lang.Object val, org.zkoss.zk.ui.Component comp) {
						return new ListModelList((List)val, true);
					}		
				}
			]]>
			</zscript>
				<zscript><![CDATA[
				//prepare the example persons List
				int count = 30;
				List persons = new ArrayList();
				for(int j= 0; j < count; ++j) {
			      Person personx = new Person();
				  personx.setFirstName("Tom"+j);
			      personx.setLastName("Hanks"+j);      
			      persons.add(personx);
			    }
				]]>
			</zscript>
				<listbox rows="4" model="@{persons, converter=myTypeConverter}">
					<listhead>
						<listheader label="First Name" width="100px" />
						<listheader label="Last Name" width="100px" />
					</listhead>
					<listitem self="@{each=person}">
						<listcell label="@{person.firstName}"/>
						<listcell label="@{person.lastName}"/>
					</listitem>
				</listbox>
			</window>
		"""
    val ztl$engine = new Widget(new StringBuffer("zk.Desktop._dt"))
    runZTL(zscript, () => {
      sleep(1000); //for DataBinding
      for (i <- 0 until 3) {
        verifyEquals("Tom" + i + "Hanks" + i, jq(".z-listitem:eq(" + i + ")").text())
      }
    })
  }
}


